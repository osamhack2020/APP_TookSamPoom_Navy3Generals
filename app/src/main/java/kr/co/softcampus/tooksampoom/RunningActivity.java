package kr.co.softcampus.tooksampoom;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.lang.Math;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import org.w3c.dom.Text;
import java.util.ArrayList;

public class RunningActivity extends AppCompatActivity {

    LocationManager locationManager;
    GoogleMap map;
    Button startBtn;
    Button successBtn;
    TextView distance_text;
    TextView speed_text;
    TextView speed;
    TextView time;
    TextView speed_result;
    TextView time_result;
    Chronometer chronometer;

    boolean isVisited = false;
    int isButtonClicked = -1;
    float distance = 0;
    long elapsedMillis;
    ArrayList<Location> location_storage = new ArrayList<Location>();
    ArrayList<LatLng> positions = new ArrayList<LatLng>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_running);
        startBtn = (Button) findViewById(R.id.startbtn);
        successBtn = (Button) findViewById(R.id.successbtn);
        distance_text = (TextView) findViewById(R.id.distance_text);
        speed_text = (TextView) findViewById(R.id.speed_text);
        speed = (TextView) findViewById(R.id.speed);
        time = (TextView) findViewById(R.id.time);
        speed_result = (TextView) findViewById(R.id.speed_result);
        time_result = (TextView) findViewById(R.id.time_result);
        chronometer = (Chronometer) findViewById(R.id.chronometer);

        init();
    }

    //시작 버튼 누르면 chronometer, textview, button나타나고 chronometer 시작됨
    public void onClickStartbtn(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        startBtn.setVisibility(View.GONE);
        chronometer.setVisibility(View.VISIBLE);
        speed_text.setVisibility(View.VISIBLE);
        distance_text.setVisibility(View.VISIBLE);
        isButtonClicked = 1;
    }

    //측정 끝난 뒤 완료버튼 누르면 이전 activity에 데이터 전달하고 현재 activity 종료
    public void onClickSuccessbtn(View view) {
        Intent intent =new Intent();
        intent.putExtra("time", elapsedSec);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void init() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment=(SupportMapFragment)fragmentManager.findFragmentById(R.id.map);
        //Map callback 등록
        MapReadyCallback mapCallback = new MapReadyCallback();
        mapFragment.getMapAsync(mapCallback);
    }


    class MapReadyCallback implements OnMapReadyCallback {
        //GoogleMap이 준비되면 실행되는 method
        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;
            getMyLocation();
        }
    }

    //GoogleMap이 준비되면 실행되는 함수
    public void getMyLocation() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_DENIED){
                return;
            }
        }

        //GPS 사용가능하면 1초마다 위치 갱신하고 불가능하면 Dialog 띄워서 확인 누르면 finish() 실행
        GetMyLocationListener locationListener = new GetMyLocationListener();
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("실행 오류");
            builder.setMessage("GPS를 허용해주세요");
            DialogListener dialogListener = new DialogListener();
            builder.setPositiveButton("확인", dialogListener);
            builder.show();
        }
    }
    
    class DialogListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            finish();
        }
    }

    class GetMyLocationListener implements LocationListener {
        //위치 갱신될 때 마다 call되는 method
        @Override
        public void onLocationChanged(@NonNull Location location) {
            setMyLocation(location,this);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

    public void setMyLocation(Location location,LocationListener listener) {
        //현재 위치로 줌인
        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(position, 16f);
        //현재위치 따라 카메라 이동
        map.moveCamera(update);
        //현재위치 표시
        map.setMyLocationEnabled(true);

        //시작 버튼이 클릭됐을 때 실행되는 코드
        if(isButtonClicked == 1){
            //동선 그릴때 필요한 location을 List에 저장하고 이전에 측정된 위치와 현재 위치 사이의 거리 계산 후 total distance에 저장
            location_storage.add(location);
            elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
            if(isVisited){
                float[] distance_piece = new float[1];
                Location.distanceBetween(location_storage.get(idx-1).getLatitude()
                                        ,location_storage.get(idx-1).getLongitude()
                                        ,location_storage.get(idx).getLatitude()
                                        ,location_storage.get(idx).getLongitude()
                                        ,distance_piece);
                distance+=distance_piece[0];
            }

            //distance가 0이 아닐때 지금까지 이동거리와 평균속도 화면에 띄우고 동선 drawing
            if(distance!=0){
                distance_text.setText(Double.toString(Math.round((distance/1000)*100)/100.0)+" km");
                speed_text.setText(Double.toString(Math.round((elapsedMillis/(distance*60))*100)/100.0)+" 분/km");
            }
            positions.add(new LatLng(location.getLatitude(),location.getLongitude()));
            Polyline polyline = map.addPolyline((new PolylineOptions())
                    .clickable(false)
                    .addAll(positions));
            isVisited = true;

            //3000m 이상 달렸을 때 위치 갱신 멈추고 걸린시간, 평균 속도 화면에 띄워주는 코드
            if(distance >= 3000){
                locationManager.removeUpdates(listener);
                chronometer.stop();
                //database로 시간(초) 보내기
                int elapsedSec = (int)elapsedMillis/1000;
                DBhelper.setRunningRecord(this, id, elapsedSec);
                
                time_result.setText(Integer.toString(elapsedSec/60)+"분 "+Integer.toString(elapsedSec%60)+"초");
                speed_result.setText(Double.toString(Math.round((elapsedMillis/(distance*60))*100)/100.0)+" 분/km");
                chronometer.setVisibility(View.GONE);
                speed_text.setVisibility(View.GONE);
                distance_text.setVisibility(View.GONE);
                successBtn.setVisibility(View.VISIBLE);
                time.setVisibility(View.VISIBLE);
                speed.setVisibility(View.VISIBLE);
                time_result.setVisibility(View.VISIBLE);
                speed_result.setVisibility(View.VISIBLE);
            }
        }
    }
}