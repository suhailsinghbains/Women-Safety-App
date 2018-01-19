package com.women_safety;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Red extends AppCompatActivity {
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                new Red.MyAsyncTask().execute();
            }
        }, 0, 500);
    }
    public class MyAsyncTask extends AsyncTask<JSONObject, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(JSONObject... params) {
            return postData();
        }
        public JSONObject postData(){
            String Start_String = "http://10.0.2.2:300";    //Url here
            String Device_Details_Red = Get_Info_of_Device();
            //Comments
            EditText editText = (EditText) findViewById(R.id.Comments_Red);
            String EVENTSURL = Start_String + Device_Details_Red + "/" + editText.getText().toString();
            EVENTSURL = EVENTSURL.replaceAll(" ", "%20");
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(EVENTSURL);
            JSONObject obj = null;
            try {
                HttpResponse response = httpclient.execute(httppost);
                try {
                    String json = EntityUtils.toString(response.getEntity());
                    obj = new JSONObject(json);
                }catch (Exception ignored){
                }
                return obj;
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                return obj;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                return obj;
            }
        }
        @Override
        protected void onPostExecute(JSONObject result) {
            TextView textViewResponseFind_Red = (TextView) findViewById(R.id.textView_Red);
            //textViewResponseFind_Red.setText(result.toString()); Status Code etc
            TextView API_Sent = (TextView) findViewById(R.id.Red_Calling);
            count = count + 1;
            API_Sent.setText(String.format("Location sent %s Times", String.valueOf(count)));
        }
    }
    public String Get_Info_of_Device() {
        String IMEI, Time, stringLocation = null;
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
