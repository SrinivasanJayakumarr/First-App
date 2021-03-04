package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class City_one_places extends AppCompatActivity {

    private CardView mPlaceOne;
    //private CardView mPlaceTwo;
    //private CardView mPlaceThree;
    //private CardView mPlaceFour;
    private Button mWeather_btn;

    private static final String TAG = "City_one_places_activity";

    public int choice,val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_one_places);

        Log.d(TAG,"Inside oncreate");

        mPlaceOne = (CardView) findViewById(R.id.card_view_0);
        //mPlaceTwo = (CardView) findViewById(R.id.card_view_1);
        //mPlaceThree = (CardView) findViewById(R.id.card_view_2);
        //mPlaceFour = (CardView) findViewById(R.id.card_view_3);
        mWeather_btn = (Button) findViewById(R.id.chennai_weather_btn);

        TextView place_1 = (TextView) findViewById(R.id.city_place_1);
        TextView place_2 = (TextView) findViewById(R.id.city_place_2);
        TextView place_3 = (TextView) findViewById(R.id.city_place_3);
        TextView place_4 = (TextView) findViewById(R.id.city_place_4);
        TextView place_1_addr = (TextView) findViewById(R.id.city_place_1_addr);
        TextView place_2_addr = (TextView) findViewById(R.id.city_place_2_addr);
        TextView place_3_addr = (TextView) findViewById(R.id.city_place_3_addr);
        TextView place_4_addr = (TextView) findViewById(R.id.city_place_4_addr);

        ImageView image1 = (ImageView) findViewById(R.id.city_1_iv);
        ImageView image2 = (ImageView) findViewById(R.id.city_2_iv);
        ImageView image3 = (ImageView) findViewById(R.id.city_3_iv);
        ImageView image4 = (ImageView) findViewById(R.id.city_4_iv);


        Intent i = getIntent();

        choice = i.getIntExtra("Value",0);

        if (choice == 1){
            image1.setImageResource(R.drawable.chennai_small);
            image2.setImageResource(R.drawable.chennai_small);
            image3.setImageResource(R.drawable.chennai_small);
            image4.setImageResource(R.drawable.chennai_small);
            place_1.setText(R.string.chennai_place_1);
            place_2.setText(R.string.chennai_place_2);
            place_3.setText(R.string.chennai_place_3);
            place_4.setText(R.string.chennai_place_4);
            place_1_addr.setText(R.string.chennai_place_1);
            place_2_addr.setText(R.string.chennai_place_2);
            place_3_addr.setText(R.string.chennai_place_3);
            place_4_addr.setText(R.string.chennai_place_4);
            mWeather_btn.setText(R.string.weather_txt_1);
        }else if (choice == 2){
            image1.setImageResource(R.drawable.trichy_small);
            image2.setImageResource(R.drawable.trichy_small);
            image3.setImageResource(R.drawable.trichy_small);
            image4.setImageResource(R.drawable.trichy_small);
            place_1.setText(R.string.trichy_place_1);
            place_2.setText(R.string.trichy_place_2);
            place_3.setText(R.string.trichy_place_3);
            place_4.setText(R.string.trichy_place_4);
            place_1_addr.setText(R.string.trichy_place_1);
            place_2_addr.setText(R.string.trichy_place_2);
            place_3_addr.setText(R.string.trichy_place_3);
            place_4_addr.setText(R.string.trichy_place_4);
            mWeather_btn.setText(R.string.weather_txt_2);
        }else if (choice == 3){
            image1.setImageResource(R.drawable.coimbatore_small);
            image2.setImageResource(R.drawable.coimbatore_small);
            image3.setImageResource(R.drawable.coimbatore_small);
            image4.setImageResource(R.drawable.coimbatore_small);
            place_1.setText(R.string.kovai_place_1);
            place_2.setText(R.string.kovai_place_2);
            place_3.setText(R.string.kovai_place_3);
            place_4.setText(R.string.kovai_place_4);
            place_1_addr.setText(R.string.kovai_place_1);
            place_2_addr.setText(R.string.kovai_place_2);
            place_3_addr.setText(R.string.kovai_place_3);
            place_4_addr.setText(R.string.kovai_place_4);
            mWeather_btn.setText(R.string.weather_txt_3);
        }else if (choice == 4){
            image1.setImageResource(R.drawable.madurai_small);
            image2.setImageResource(R.drawable.madurai_small);
            image3.setImageResource(R.drawable.madurai_small);
            image4.setImageResource(R.drawable.madurai_small);
            place_1.setText(R.string.madurai_place_1);
            place_2.setText(R.string.madurai_place_2);
            place_3.setText(R.string.madurai_place_3);
            place_4.setText(R.string.madurai_place_4);
            place_1_addr.setText(R.string.madurai_place_1);
            place_2_addr.setText(R.string.madurai_place_2);
            place_3_addr.setText(R.string.madurai_place_3);
            place_4_addr.setText(R.string.madurai_place_4);
            mWeather_btn.setText(R.string.weather_txt_4);
        }

        openCardView();
        openWeather();

    }

    private void openWeather() {
        Log.d(TAG,"Inside openWeather");

        mWeather_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = choice;
                Intent weatherInt = new Intent(City_one_places.this,Weather_status_trichy.class);
                weatherInt.putExtra("Value",val);
                startActivity(weatherInt);
            }
        });
    }

    private void openCardView() {

        Log.d(TAG,"Inside openCardview-COP");

        mPlaceOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG,"Inside onclick");

                Intent crossInt = new Intent(City_one_places.this,Place.class);
                startActivity(crossInt);
            }
        });
    }

}