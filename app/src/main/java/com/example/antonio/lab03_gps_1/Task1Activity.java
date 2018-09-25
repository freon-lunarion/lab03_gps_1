package com.example.antonio.lab03_gps_1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Task1Activity extends AppCompatActivity {

    private LocationManager locationManager;
    private Button btn_enable;
    private TextView txt_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        txt_status = findViewById(R.id.txt_status);
        btn_enable = findViewById(R.id.btn_enable);

        btn_enable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    txt_status.setText("GPS enable");
                } else {
                    txt_status.setText("GPS disable");
                    alertSetting();
                }
            }
        });

    }

    public void alertSetting() {
        final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Task1Activity.this).setTitle("GPS Setting").setMessage("Do you like to go to setting menu?");
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
