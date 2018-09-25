package com.example.antonio.lab03_gps_1;


import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_task1,btn_task2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_task1 = findViewById(R.id.btn_task1);
        btn_task1.setOnClickListener( MainActivity.this);

        btn_task2 = findViewById(R.id.btn_task2);
        btn_task2.setOnClickListener(MainActivity.this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.btn_task1: {
                switchTask1(v);
                break;
            }

            case R.id.btn_task2: {
                switchTask2(v);
                break;
            }

        }
    }

    private void switchTask1(View view) {
        Intent intent = new Intent(this, Task1Activity.class);
        startActivity(intent);

    }

    private void switchTask2(View view) {
        Intent intent = new Intent(this, Task2Activity.class);
        startActivity(intent);

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
