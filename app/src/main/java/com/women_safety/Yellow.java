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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
            String Start_String = "http://10.0.2.2:300";    //Real Url here
            //String Device_Details_Yellow = new Get_Info_of_Device().getStringLocation();
            String Device_Details_Yellow = "/09/34/34/January 8 04:05:06 1999 PST";
            //Comments
            EditText editText = (EditText) findViewById(R.id.Comments_Yellow);
            String EVENTSURL = Start_String + Device_Details_Yellow + "/" + editText.getText().toString();
            EVENTSURL = EVENTSURL.replaceAll(" ", "+");
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
            TextView textViewResponseFind_Yellow = (TextView) findViewById(R.id.textView_Yellow);
            //textViewResponseFind_Yellow.setText(result.toString()); Status Code Etc
            TextView API_Sent = (TextView) findViewById(R.id.Yellow_Calling);
            count = count + 1;
            API_Sent.setText(String.format("Location sent %s Times", String.valueOf(count)));
        }
    }
}
