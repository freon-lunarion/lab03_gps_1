package com.example.antonio.lab03_gps_1;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.location.LocationManager.*;

public class Task2Activity extends AppCompatActivity {

    private LocationManager locationManager;
    private Button btn_enable, btn_loc;
    private TextView txt_status;
    private Location location;
    private LocationListener locationListener;
    private String locationProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationProvider = LocationManager.NETWORK_PROVIDER;
        txt_status = findViewById(R.id.txt_status);
        btn_enable = findViewById(R.id.btn_enable);

        btn_enable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (locationManager.isProviderEnabled(GPS_PROVIDER)){
                    txt_status.setText("GPS enable");
                } else {
                    txt_status.setText("GPS disable");
                    alertSetting();
                }
            }
        });

        btn_loc = findViewById(R.id.btn_loc);
        btn_loc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int permissionCheck = ContextCompat.checkSelfPermission(Task2Activity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Task2Activity.this,
                     new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            10);
                } else {

//                    LocationListener locationListener = new LocationListener() {
//                        public void onLocationChanged(Location location) {
//                            // Called when a new location is found by the network location provider.
//                            makeUseOfNewLocation(location);
//                        }
//
//                        public void onStatusChanged(String provider, int status, Bundle extras) {}
//
//                        public void onProviderEnabled(String provider) {}
//
//                        public void onProviderDisabled(String provider) {}
//                    };
//                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

                    location = locationManager.getLastKnownLocation(locationProvider);
                    long time = location.getTime();
                    Date date = new Date( time );
                    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                    String textofDate = sdf.format( date );
                    txt_status.setText(
                            "Date/ Time: " + textofDate +
                                    "\nProvider: " + location.getProvider() +
                                    "\nAccuracy: " + location.getAccuracy() +
                                    "\nAltitude: " + location.getAltitude() +
                                    "\nLatitude: " + location.getLatitude() +
                                    "\nSpeed: " + location.getSpeed() );

                }




            }
        });
    }

    private void makeUseOfNewLocation(Location location) {

        txt_status.setText(""+ location.getLatitude() );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 10: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    public void alertSetting() {
        final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Task2Activity.this).setTitle("GPS Setting").setMessage("Do you like to go to setting menu?");
        alertDialog.setPositiveButton("Setting", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alertDialog.show();
    }

}
