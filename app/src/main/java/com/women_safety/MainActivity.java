package com.women_safety;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Yellow = (Button) findViewById(R.id.button2);
        Yellow.setBackgroundColor(Color.YELLOW);
        Button Red = (Button) findViewById(R.id.button3);
        Red.setBackgroundColor(Color.RED);
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
