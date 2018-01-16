package com.women_safety;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import static java.lang.String.copyValueOf;
import static java.lang.String.format;

public class Yellow extends AppCompatActivity {
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yellow);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                new MyAsyncTask().execute();
            }
        }, 0, 5000);
    }
    public class MyAsyncTask extends AsyncTask<JSONObject, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(JSONObject... params) {
            return postData();
        }
        public JSONObject postData(){
            String EVENTSURL = "https://reqres.in/api/users?page=2";    //Real Url here
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
            TextView textViewResponseFind_Yellow = (TextView) findViewById(R.id.textView_Yellow);
            //textViewResponseFind_Yellow.setText(result.toString()); Status Code Etc
            TextView API_Sent = (TextView) findViewById(R.id.Yellow_Calling);
            count = count + 1;
            API_Sent.setText(String.format("Location sent %s Times", String.valueOf(count)));
        }
    }
}
