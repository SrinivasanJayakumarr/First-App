package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Main_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton mChennai_img_btn = findViewById(R.id.city_1_btn);
        ImageButton mTrichy_img_btn = findViewById(R.id.city_2_btn);
        ImageButton mCoimbatore_img_btn = findViewById(R.id.city_3_btn);
        ImageButton mMadurai_img_btn = findViewById(R.id.city_4_btn);

        Log.d(TAG,"Inside oncreate main");
        Toast.makeText(this,"Inside main_actibity",Toast.LENGTH_LONG).show();

        //For transparent statusbar
        if(Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21){
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,true);
            if (Build.VERSION.SDK_INT >= 19){
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }
            //Fully transparent
            if (Build.VERSION.SDK_INT >= 21){
                setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }

        mChennai_img_btn.setOnClickListener(this);
        mTrichy_img_btn.setOnClickListener(this);
        mCoimbatore_img_btn.setOnClickListener(this);
        mMadurai_img_btn.setOnClickListener(this);

    }

    //For transparent statusbar
    public static void setWindowFlag(Activity activity, final int bits, boolean on){

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if(on){
            winParams.flags |= bits;
        }else {
            winParams.flags &= -bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.city_1_btn:
                int val = 1;
                Toast.makeText(this,"Inside switch case 1",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,City_two_overview.class);
                i.putExtra("Value",val);
                startActivity(i);
                break;
            case R.id.city_2_btn:
                val = 2;
                Toast.makeText(this,"Inside switch case 2",Toast.LENGTH_SHORT).show();
                 Intent i2 = new Intent(MainActivity.this,City_two_overview.class);
                 i2.putExtra("Value",val);
                 startActivity(i2);
                break;
            case R.id.city_3_btn:
                val = 3;
                Toast.makeText(this,"Inside switch case 3",Toast.LENGTH_SHORT).show();
                Intent i3 = new Intent(MainActivity.this,City_two_overview.class);
                i3.putExtra("Value",val);
                startActivity(i3);
                break;
            case R.id.city_4_btn:
                val = 4;
                Toast.makeText(this,"Inside switch case 4",Toast.LENGTH_SHORT).show();
                Intent i4 = new Intent(MainActivity.this,City_two_overview.class);
                i4.putExtra("Value",val);
                startActivity(i4);
                break;
        }

    }
}