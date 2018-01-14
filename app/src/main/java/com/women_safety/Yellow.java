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

public class Yellow extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yellow);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(MyAsyncTask.Status.values().toString());
    }
    public class MyAsyncTask extends AsyncTask<JSONObject, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(JSONObject... params) {
            return postData();
        }
        JSONObject postData(){
            TextView textViewResponseFind = (TextView) findViewById(R.id.textView);
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
                    textViewResponseFind.setText(obj.toString());
                }catch (Exception e){
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
    }
}
