package kr.co.softcampus.tooksampoom;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;

public class RunningActivity extends AppCompatActivity {
    String[] permissions_list = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    LocationManager locationManager;
    GoogleMap map;
    ArrayList<Location> location_storage = new ArrayList<Location>();
    int idx = 0;
    float distance = 0;
    int isButtonClicked = -1;

    Button startBtn;
    TextView distance_text;
    TextView speed_text;

    Chronometer chronometer;
    ArrayList<LatLng> positions = new ArrayList<LatLng>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        startBtn = (Button) findViewById(R.id.startbtn);
        distance_text = (TextView) findViewById(R.id.distance_text);
        speed_text = (TextView) findViewById(R.id.speed_text);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        init();
    }

    public void onClickbtn(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        startBtn.setVisibility(View.GONE);
        chronometer.setVisibility(View.VISIBLE);
        speed_text.setVisibility(View.VISIBLE);
        distance_text.setVisibility(View.VISIBLE);
        isButtonClicked = 1;
    }

    public void init() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
        MapReadyCallback callback1 = new MapReadyCallback();
        mapFragment.getMapAsync(callback1);
    }

    class MapReadyCallback implements OnMapReadyCallback {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;
            getMyLocation();
        }
    }

    public void getMyLocation() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
                return;
            }
        }
        //새롭게 측정하기
        GetMyLocationListener listener = new GetMyLocationListener();
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, listener);
            Log.d("eror", "success");
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
        @Override
        public void onLocationChanged(@NonNull Location location) {
            setMyLocation(location);
        }
    }

    public void setMyLocation(Location location) {
        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(position, 15f);
        map.moveCamera(update);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            return;
        }
        map.setMyLocationEnabled(true);

        if(isButtonClicked == 1){
            location_storage.add(location);
            long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
            if(idx!=0){
                float[] distance_piece = new float[1];
                Location.distanceBetween(location_storage.get(idx-1).getLatitude(),location_storage.get(idx-1).getLongitude(), location_storage.get(idx).getLatitude(), location_storage.get(idx).getLongitude(),distance_piece);
                distance+=distance_piece[0];
                positions.add(new LatLng(location.getLatitude(),location.getLongitude()));

                Log.d("test", Long.toString(elapsedMillis));
                distance_text.setText(Double.toString(Math.round((distance/1000)*100)/100.0)+" km");
                speed_text.setText(Double.toString(Math.round((elapsedMillis/(distance*60))*100)/100.0)+" 분/km");

                if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
                    return;
                }

                Polyline polyline = map.addPolyline((new PolylineOptions())
                        .clickable(false)
                        .addAll(positions));
                Log.d("test","success");
            }

            idx++;
        }
    }
}