package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class City_two_overview extends AppCompatActivity {

    private Button mXplore_Btn;
    private static final String TAG = "City_two_review_activity";
    public int choice,val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_two_overview);

        Log.d(TAG,"Inside onCreate");

        mXplore_Btn = (Button) findViewById(R.id.trichy_btn);
        TextView title = (TextView) findViewById(R.id.trichy_title);
        TextView content = (TextView) findViewById(R.id.textView_trichy_content);
        ImageView image = (ImageView) findViewById(R.id.img_view_trichy);

        Intent i = getIntent();

        choice = i.getIntExtra("Value",0);

        Log.d(TAG,"Value Part "+choice);

        if (choice == 1){
            Toast.makeText(this,"case 1",Toast.LENGTH_SHORT).show();
            title.setText(R.string.city_1_OV_title_txt);
            content.setText(R.string.chennai_content);
            image.setImageResource(R.drawable.coimbatore);
        }else if (choice == 2){
            Toast.makeText(this,"case 2",Toast.LENGTH_SHORT).show();
        }else if (choice == 3){
            Toast.makeText(this,"case 3",Toast.LENGTH_SHORT).show();
            title.setText(R.string.city_3_OV_title_txt);
            content.setText(R.string.kovai_content);
            image.setImageResource(R.drawable.coimbatore);
        }else if(choice == 4){
            Toast.makeText(this,"case 4",Toast.LENGTH_SHORT).show();
            title.setText(R.string.city_4_OV_title_txt);
            content.setText(R.string.madurai_content);
            image.setImageResource(R.drawable.madurai);
        }

        openExplore();
    }

    private void openExplore() {

        Log.d(TAG,"Inside openExplore method-CTO");

        mXplore_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = choice ;
                Intent ctoIntent = new Intent(City_two_overview.this,City_one_places.class);
                ctoIntent.putExtra("Value",val);
                startActivity(ctoIntent);
            }
        });
    }
}