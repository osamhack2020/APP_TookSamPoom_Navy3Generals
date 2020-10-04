package kr.co.softcampus.tooksampoom;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.appFragmentManager;
import android.support.v7.app.AppCompatActivity
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView
import android.widget.Button;
import android.widget.Chronometer;
import android.os.SystemClock;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.app.Dialog;
import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment

public class RunningActivity extends AppCompatActivity{
    Chronometer chronometer;  
    LocationManager locationManager;
    GoogleMap map;
    ArrayList<Location> location_storage = new ArraryList<Locaiton>();
    int idx=0 ;
    float distance = 0;
    int isButtonClicked = -1
    setContentView(R.layout.activity_running);
    startBtn = (Button)findViewById(R.id.startbtn);
    distance_text = (TextViw)findViewById(R.id.distance_text);
    speed_text = (TextViw)findViewById(R.id.speed_text);

    chronometer = (Chronometer)findViewById(R.id.chronometer);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(permission_list,0);
        } else{

                init();
        }
    }

    public void onClickbtn(View view){  
        chronometer.setBase(SystemClock.elapsedRealtime());  
        chronometer.start();  
        startBtn.setVisibility(View.GONE);
        chronometer.setVisibility(View.VISIBLE);
        speed_text.setVisibility(View.VISIBLE);
        isButtonClicked = 1;
    };

    @Override
    public void onRequestPermissionResult(int requestcode, @NonNull String[], @NonNull int[]grantResult){
        super.onRequestPermission~~;
        for(int result : grantResult){
                if(result == PackageManager.PERMISSION_DENIED){
                        return;
                }
        }
        init();
    })

    public void init(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)fragmentManager.findFragmentById(R.id.map);
        MapReadyCallback callback1 = new MapReadyCallback();
        mapFragment.getMapAsync(callback1);
    }

    class MapReadyCallback implements OnMapReadyCallback{
            @Override  
            public void onMapReady(GoogleMap googleMap){
                map = googleMap;
                getMyLocation();
            }
    }

    public void getMyLocation(){
        locationManager (LocationManager)getSystemService(LOCATION_SERVICE);
        if(Builder.VERSION.SDK_INT>=Builder.VerSIONCODES.M){
            if(checkSelfPermission(Manifest.permission.ACCES_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
                return;
            }
        }
        //새롭게 측정하기
        GetMyLocationListener listener = new GetMyLocationListener();
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)==true){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("실행 오류");
            builder.setMessage("GPS를 허용해주세요");

            DialogListener listener = new DialogListener();
            builder.setPositiveButton("확인",listener);

            builder.show();
        }
    }

    class DialogListener implements DialogInterface.OnclickListener{
        public void onClick(DialogInterface dialog, int which){
            finish();
        }
    }

    class GetMyLocationListener implements LocationListener{
        public void onLocationChanged(Location location){
                setMyLocation(location);
        }
    }

    public void setMyLocation(Location location){

        location_storage.add(location);
        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(position,15f);
        map.moveCamera(update);
        map.setMyLocationEnabled(true);

        if(isButtonClicked == 1){
            if(idx!=0){
                float[] distance_piece = new float[1];
                Location.distanceBetween(location_storage.get(idx-1).getLatitude(),location_storage.get(idx-1).getLongitude(), location_storage.get(idx).getLatitude(), location_storage.get(idx).getLongitude(),distance_piece);
                distance+=distance_piece[0];
            }

            long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
            distance_text.setText(distance.toString());
            speed_text.setText((distance*60/elapsedMillis).toString()+" km/minute");
        
            if(Builder.VERSION.SDK_INT>=Builder.VerSIONCODES.M){
                if(checkSelfPermission(Manifest.permission.ACCES_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
                    return;
                }
            }
        
            Polyline polyline = map.addPolyline(new PolylineOptions()
            .clickable(false)
            .add(position));
            idx++;
        }
    }
}