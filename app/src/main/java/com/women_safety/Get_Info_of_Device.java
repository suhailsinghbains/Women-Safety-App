package com.women_safety;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Suhail on 2018-01-17.
 */

public class Get_Info_of_Device extends AppCompatActivity {
    String IMEI, Time, stringLocation;

    public String getStringLocation() {
        //Get IMEI
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        IMEI = telephonyManager.getDeviceId();
        //Get Current Date and Time
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Time = currentDateTimeString;
        //Get Location
        String bestProvider;
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        bestProvider = lm.getBestProvider(criteria, false);
        Location location;
        location = lm.getLastKnownLocation(bestProvider);
        if (location == null){
            Toast.makeText(this,"Location Not found",Toast.LENGTH_LONG).show();
        }else{
            stringLocation = location.getLatitude()+"/"+location.getLongitude();
        }
        String final_URL = "/" + IMEI + "/" + stringLocation + "/" + Time;  //Comments not implemented here
        return final_URL;
    }
}
