package com.women_safety;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.JsonWriter;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Yellow_Button_Pressed(View view) {
        Intent intent = new Intent(this, Yellow.class);
        startActivity(intent);
    }

    public void Red_Button_Pressed(View view) {
        Intent intent = new Intent(this, Red.class);
        startActivity(intent);
    }
}
