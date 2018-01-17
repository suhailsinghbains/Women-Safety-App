package com.women_safety;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
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
        int x=0;
        @Override
        protected JSONObject doInBackground(JSONObject... params) {
            return postData();
        }
        public JSONObject postData(){
            String Start_String = "10.0.2.2:300";    //Real Url here
            String Device_Details_Yellow = new Get_Info_of_Device().getStringLocation();
            //Comments
            EditText editText = (EditText) findViewById(R.id.Comments_Red);
            String EVENTSURL = Start_String + Device_Details_Yellow + "/" + editText.getText();
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(EVENTSURL);
            JSONObject obj = null;
            try {
                // Add your data
                //List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(0);
                //httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httpget);
                try {
                    String json = EntityUtils.toString(response.getEntity());
                    obj = new JSONObject(json);
                    //textViewResponseFind.setText(obj.toString());
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
}
