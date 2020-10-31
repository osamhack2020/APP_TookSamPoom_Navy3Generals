package kr.co.softcampus.tooksampoom;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

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
    TextView finish;
    TextView result;
    Chronometer chronometer;
    LinearLayout display1;
    LinearLayout display2;
    LinearLayout display3;
    float pastDistance=0;
    long pastTime;
    int elapsedSec = 0;
    int idx=0;
    int isButtonClicked = -1;
    float distance = 0;
    long elapsedMillis;
    ArrayList<Location> location_storage = new ArrayList<Location>();
    ArrayList<LatLng> positions = new ArrayList<LatLng>();
    LatLngBounds area;
    boolean _check = true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_running);
        startBtn = findViewById(R.id.startbtn);
        successBtn = findViewById(R.id.successbtn);
        distance_text = findViewById(R.id.distance_text);
        speed_text = findViewById(R.id.speed_text);
        speed = findViewById(R.id.speed);
        time = findViewById(R.id.time);
        speed_result = (TextView) findViewById(R.id.speed_result);
        time_result = (TextView) findViewById(R.id.time_result);
        finish = (TextView) findViewById(R.id.finish);
        result = (TextView) findViewById(R.id.textView7);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        display1 = (LinearLayout) findViewById(R.id.display1);
        display2 = (LinearLayout) findViewById(R.id.display2);
        display3 = (LinearLayout) findViewById(R.id.display3);

        init();
    }

    //시작 버튼 누르면 chronometer, textview, button나타나고 chronometer 시작됨
    public void onClickStartbtn(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        startBtn.setVisibility(View.GONE);
        display1.setVisibility((View.VISIBLE));
        isButtonClicked = 1;
    }

    //측정 끝난 뒤 완료버튼 누르면 데이터 저장하고 현재 activity 종료
    public void onClickSuccessbtn(View view) {
        int id = 1;
        DBhelper.setRunningRecord(this, id, elapsedSec);
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
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    100,
                    0,
                    locationListener);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("실행 오류");
            builder.setMessage("GPS를 허용해주세요");
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.show();
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
        if(_check){
            //현재 위치로 줌인
            LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(position, 17f);
            //현재위치 따라 카메라 이동
            map.moveCamera(update);

            //현재위치 표시
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_DENIED){
                    return;
                }
            }
            map.setMyLocationEnabled(true);

        //시작 버튼이 클릭됐을 때 실행되는 코드
        if(isButtonClicked == 1){
            //동선 그릴때 필요한 location을 List에 저장하고 이전에 측정된 위치와 현재 위치 사이의 거리 계산 후 total distance에 저장
            location_storage.add(location);
            elapsedMillis = SystemClock.elapsedRealtime()-chronometer.getBase();
            if(idx!=0){
                float[] distance_piece = new float[1];
                Location.distanceBetween(location_storage.get(idx-1).getLatitude()
                        ,location_storage.get(idx-1).getLongitude()
                        ,location_storage.get(idx).getLatitude()
                        ,location_storage.get(idx).getLongitude()
                        ,distance_piece);
                distance+=distance_piece[0]*1.4;
                distance_text.setText(Double.toString(Math.round((distance/1000)*100)/100.0)+" km");
                elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                long nowTime = SystemClock.elapsedRealtime();

                    if(idx==1){
                        pastTime=SystemClock.elapsedRealtime();
                    }
                    if(idx%10 == 0){
                        float tookDistance = (distance - pastDistance)*(float)(1.4);
                        pastDistance = distance;
                        long tookTime = nowTime - pastTime;
                        pastTime = SystemClock.elapsedRealtime();
                        speed_text.setText(Double.toString(Math.round((tookTime/(tookDistance*60))*100)/100.0)+" 분/km");
                    }
                    //3000m 이상 달렸을 때 위치 갱신 멈추고, 맵 크게 바꾸고 걸린시간, 평균 속도 화면에 띄워주는 코드
                    if(distance >= 3000.0){
                        locationManager.removeUpdates(listener);
                        chronometer.stop();
                        area = area.including(positions.get(positions.size()-1));
                        int width = getResources().getDisplayMetrics().widthPixels;
                        int height = getResources().getDisplayMetrics().heightPixels;
                        map.moveCamera(CameraUpdateFactory.newLatLngBounds(area,width,height,80));
                        elapsedSec = (int)elapsedMillis/1000;
                        speed_result.setText(Double.toString(Math.round((elapsedMillis/(distance*60))*100)/100.0)+" 분/km");
                        time_result.setText(Integer.toString(elapsedSec/60)+"분 "+Integer.toString(elapsedSec%60)+"초");
                        result.setText(runningCalculator(elapsedSec));
                        display1.setVisibility(View.GONE);
                        successBtn.setVisibility(View.VISIBLE);
                        finish.setVisibility(View.VISIBLE);
                        result.setVisibility(View.VISIBLE);
                        display2.setVisibility(View.VISIBLE);
                        display3.setVisibility(View.VISIBLE);
                        _check = false;
                    }
                }
                if(idx==0){
                    positions.add(new LatLng(location.getLatitude(),location.getLongitude()));
                    area = new LatLngBounds(positions.get(0),new LatLng(positions.get(0).latitude+0.0001,positions.get(0).longitude+0.0001));
                    Log.d("center", area.getCenter().toString()+idx);
                }
                if(idx!=0&&(location.getLatitude()<positions.get(idx-1).latitude+0.01&&location.getLatitude()>positions.get(idx-1).latitude-0.01)){
                    if(idx!=0&&(location.getLongitude()<positions.get(idx-1).longitude+0.01&&location.getLongitude()>positions.get(idx-1).longitude-0.01)){
                        positions.add(new LatLng(location.getLatitude(),location.getLongitude()));
                    }
                }

                if(idx>=1){
                    area = area.including(positions.get(positions.size()-1));
                }

                Polyline polyline = map.addPolyline((new PolylineOptions())
                        .clickable(false)
                        .addAll(positions));

                polyline.setStartCap(new RoundCap());
                polyline.setEndCap((new RoundCap()));
                polyline.setColor(Color.argb(255,40,104,176));
                polyline.setJointType(JointType.ROUND);
                polyline.setWidth(30);
                idx++;
            }
        }
    }

    public String runningCalculator(int time){
        if(time>=937){
            return "미달";
        }
        if(time<=936&&time>=875){
            return "3급";
        }
        if(time<=874&&time>=813){
            return "2급";
        }
        if(time<=812&&time>=751){
            return "1급";
        }
        if(time<=750){
            return "특급";
        }
        return "";
    }
}