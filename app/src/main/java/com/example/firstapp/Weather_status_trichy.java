package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Weather_status_trichy extends AppCompatActivity {

    private TextView mTemp;
    private TextView mStatus;
    private TextView mWind;
    private TextView mPressure;
    private TextView mHumidity;

    private ImageView weather;

    private static final String TAG = "Weather_status_trichy_activity";

    public int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_status_trichy);

        Button btnStatus = findViewById(R.id.btn_check);

        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check for Internet Connection
                if (isConnected()) {
                    Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }

            private boolean isConnected() {
                boolean connected = false;
                try {
                    ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo nInfo = cm.getActiveNetworkInfo();
                    connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
                    //return connected;
                } catch (Exception e) {
                    Log.e("Connectivity Exception", Objects.requireNonNull(e.getMessage()));
                }
                return connected;
            }
        });

        mTemp = findViewById(R.id.txt_view_temp_status);
        mStatus = findViewById(R.id.txt_view_weather_descrption);
        mWind = findViewById(R.id.txt_view_wind);
        mPressure = findViewById(R.id.txt_view_pressure);
        mHumidity = findViewById(R.id.txt_view_humidity);

        TextView city = findViewById(R.id.txt_view_ws_1);

        weather = findViewById(R.id.img_view_ws_1);

        Toast.makeText(this,"Inside oncreate of weather",Toast.LENGTH_LONG).show();

        Intent i = getIntent();

        choice = i.getIntExtra("Value",0);

        if (choice == 1){
            city.setText(R.string.weather_status_city_1);
        }else if (choice == 2){
            city.setText(R.string.weather_status_city_2);
        }else if (choice == 3){
            city.setText(R.string.weather_status_city_3);
        }else if (choice == 4){
            city.setText(R.string.weather_status_city_4);
        }

        Button btnRefresh = findViewById(R.id.btn_get);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choice == 1){
                    updateWeather();
                }else if (choice == 2){
                    updateWeatherTrichy();
                }else if (choice == 3){
                    updateWeatherKovai();
                }else if (choice == 4){
                    updateWeatherMadurai();
                }
            }
        });
    }

    private void updateWeather() {

        Log.d(TAG,"inside update weather");

        Toast.makeText(this,"Accessing update weather",Toast.LENGTH_LONG).show();

        //first value lattitude and longitude  Geo coords chennai [13.0878, 80.2785],trichy Geo coords [10.8029, 78.6988]
        //kovai Geo coords [11, 76.9667] madurai Geo coords [9.9333, 78.1167] tanjore Geo coords [10.8, 79.15]
        //String key = "6091d7651a1cc6ab4b6e742d900dfb3d";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Chennai,in&appid=6091d7651a1cc6ab4b6e742d900dfb3d&units=metric";

        Log.d(TAG,"Before JSON request");

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG,"Before response value");
                Log.d(TAG,String.valueOf(response));

                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    JSONObject wind_object = response.getJSONObject("wind");

                    int temp = main_object.getInt("temp");
                    int humi = main_object.getInt("humidity");
                    int pressu = main_object.getInt("pressure");
                    String status = object.getString("description");
                    Double speed = wind_object.getDouble("speed");

                    //int temp_int = Integer.parseInt(temp);

                    mTemp.setText(String.valueOf(temp));
                    mHumidity.setText(String.valueOf(humi));
                    mPressure.setText(String.valueOf(pressu));
                    mStatus.setText(status);
                    mWind.setText(String.valueOf(speed));

                    String[] clear = {"clear sky"};
                    String[] clouds = {"few clouds","scattered clouds","broken clouds","overcast clouds"};
                    String[] atmo = {"mist","smoke","haze","sand/dust whirls","fog","sand","dust","volcanic ash","squalls","tornado"};
                    String[] snow = {"light snow","snow","heavy snow","sleet","light shower sleet","shower sleet","light rain and snow","rain and snow","light shower snow","shower snow","heavy shower snow"};
                    String[] rain = {"shower rain","light rain","moderate rain","heavy intensity rain","very heavy rain","extreme rain","freezing rain","light intensity shower rain","heavy intensity shower rain","ragged shower rain"};
                    String[] drizzle = {"drizzle","light intensity drizzle","heavy intensity drizzle","light intensity drizzle rain","drizzle rain","heavy intensity drizzle rain","shower rain and drizzle","heavy shower rain and drizzle","shower drizzle"};
                    String[] thunder = {"thunderstorm","thunderstorm with light rain","thunderstorm with rain","thunderstorm with heavy rain","light thunderstorm","heavy thunderstorm","ragged thunderstorm","thunderstorm with light drizzle","thunderstorm with drizzle","thunderstorm with heavy drizzle"};

                    //To check the condition

                    List one = Arrays.asList(clouds);
                    List two = Arrays.asList(atmo);
                    List three = Arrays.asList(snow);
                    List four = Arrays.asList(rain);
                    List five = Arrays.asList(drizzle);
                    List six = Arrays.asList(thunder);
                    List seven = Arrays.asList(clear);

                    String iconUrl = "";

                    if (one.contains(status)){

                        iconUrl = "http://openweathermap.org/img/wn/02d@2x.png";
                        Log.d(TAG,"Image dowmnloaded");
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (two.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/50d@2x.png";
                        Log.d(TAG,"Image dowmnloaded");
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (three.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/13d@2x.png";
                        Log.d(TAG,"Image dowmnloaded");
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (four.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/10d@2x.png";
                        Log.d(TAG,"Image dowmnloaded");
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (five.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/09d@2x.png";
                        Log.d(TAG,"Image dowmnloaded");
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (six.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/11d@2x.png";
                        Log.d(TAG,"Image dowmnloaded");
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (seven.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/01d@2x.png";
                        Log.d(TAG,"Image dowmnloaded");
                        Picasso.get().load(iconUrl).into(weather);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);
    }

    private void updateWeatherTrichy() {

        Log.d(TAG,"inside update weather");

        Toast.makeText(this,"Accessing update weather",Toast.LENGTH_LONG).show();

        //first value lattitude and longitude  Geo coords chennai [13.0878, 80.2785],trichy Geo coords [10.8029, 78.6988]
        //kovai Geo coords [11, 76.9667] madurai Geo coords [9.9333, 78.1167] tanjore Geo coords [10.8, 79.15]
        //String key = "6091d7651a1cc6ab4b6e742d900dfb3d";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Trichy,in&appid=6091d7651a1cc6ab4b6e742d900dfb3d&units=metric";

        Log.d(TAG,"Before JSON request");

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG,"Before response value");
                Log.d(TAG,String.valueOf(response));

                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    JSONObject wind_object = response.getJSONObject("wind");

                    int temp = main_object.getInt("temp");
                    int humi = main_object.getInt("humidity");
                    int pressu = main_object.getInt("pressure");
                    String status = object.getString("description");
                    Double speed = wind_object.getDouble("speed");

                    //int temp_int = Integer.parseInt(temp);

                    mTemp.setText(String.valueOf(temp));
                    mHumidity.setText(String.valueOf(humi));
                    mPressure.setText(String.valueOf(pressu));
                    mStatus.setText(status);
                    mWind.setText(String.valueOf(speed));

                    String[] clear = {"clear sky"};
                    String[] clouds = {"few clouds","scattered clouds","broken clouds","overcast clouds"};
                    String[] atmo = {"mist","smoke","haze","sand/dust whirls","fog","sand","dust","volcanic ash","squalls","tornado"};
                    String[] snow = {"light snow","snow","heavy snow","sleet","light shower sleet","shower sleet","light rain and snow","rain and snow","light shower snow","shower snow","heavy shower snow"};
                    String[] rain = {"shower rain","light rain","moderate rain","heavy intensity rain","very heavy rain","extreme rain","freezing rain","light intensity shower rain","heavy intensity shower rain","ragged shower rain"};
                    String[] drizzle = {"drizzle","light intensity drizzle","heavy intensity drizzle","light intensity drizzle rain","drizzle rain","heavy intensity drizzle rain","shower rain and drizzle","heavy shower rain and drizzle","shower drizzle"};
                    String[] thunder = {"thunderstorm","thunderstorm with light rain","thunderstorm with rain","thunderstorm with heavy rain","light thunderstorm","heavy thunderstorm","ragged thunderstorm","thunderstorm with light drizzle","thunderstorm with drizzle","thunderstorm with heavy drizzle"};

                    //To check the condition

                    List one = Arrays.asList(clouds);
                    List two = Arrays.asList(atmo);
                    List three = Arrays.asList(snow);
                    List four = Arrays.asList(rain);
                    List five = Arrays.asList(drizzle);
                    List six = Arrays.asList(thunder);
                    List seven = Arrays.asList(clear);

                    String iconUrl = "";

                    if (one.contains(status)){

                        iconUrl = "http://openweathermap.org/img/wn/02d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (two.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/50d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (three.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/13d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (four.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/10d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (five.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/09d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (six.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/11d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (seven.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/01d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);
    }

    private void updateWeatherKovai() {

        Log.d(TAG,"inside update weather");

        Toast.makeText(this,"Accessing update weather",Toast.LENGTH_LONG).show();

        //first value lattitude and longitude  Geo coords chennai [13.0878, 80.2785],trichy Geo coords [10.8029, 78.6988]
        //kovai Geo coords [11, 76.9667] madurai Geo coords [9.9333, 78.1167] tanjore Geo coords [10.8, 79.15]
        //String key = "6091d7651a1cc6ab4b6e742d900dfb3d";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Coimbatore,in&appid=6091d7651a1cc6ab4b6e742d900dfb3d&units=metric";

        Log.d(TAG,"Before JSON request");

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG,"Before response value");
                Log.d(TAG,String.valueOf(response));

                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    JSONObject wind_object = response.getJSONObject("wind");

                    int temp = main_object.getInt("temp");
                    int humi = main_object.getInt("humidity");
                    int pressu = main_object.getInt("pressure");
                    String status = object.getString("description");
                    Double speed = wind_object.getDouble("speed");

                    //int temp_int = Integer.parseInt(temp);

                    mTemp.setText(String.valueOf(temp));
                    mHumidity.setText(String.valueOf(humi));
                    mPressure.setText(String.valueOf(pressu));
                    mStatus.setText(status);
                    mWind.setText(String.valueOf(speed));

                    String[] clear = {"clear sky"};
                    String[] clouds = {"few clouds","scattered clouds","broken clouds","overcast clouds"};
                    String[] atmo = {"mist","smoke","haze","sand/dust whirls","fog","sand","dust","volcanic ash","squalls","tornado"};
                    String[] snow = {"light snow","snow","heavy snow","sleet","light shower sleet","shower sleet","light rain and snow","rain and snow","light shower snow","shower snow","heavy shower snow"};
                    String[] rain = {"shower rain","light rain","moderate rain","heavy intensity rain","very heavy rain","extreme rain","freezing rain","light intensity shower rain","heavy intensity shower rain","ragged shower rain"};
                    String[] drizzle = {"drizzle","light intensity drizzle","heavy intensity drizzle","light intensity drizzle rain","drizzle rain","heavy intensity drizzle rain","shower rain and drizzle","heavy shower rain and drizzle","shower drizzle"};
                    String[] thunder = {"thunderstorm","thunderstorm with light rain","thunderstorm with rain","thunderstorm with heavy rain","light thunderstorm","heavy thunderstorm","ragged thunderstorm","thunderstorm with light drizzle","thunderstorm with drizzle","thunderstorm with heavy drizzle"};

                    //To check the condition

                    List one = Arrays.asList(clouds);
                    List two = Arrays.asList(atmo);
                    List three = Arrays.asList(snow);
                    List four = Arrays.asList(rain);
                    List five = Arrays.asList(drizzle);
                    List six = Arrays.asList(thunder);
                    List seven = Arrays.asList(clear);

                    String iconUrl = "";

                    if (one.contains(status)){

                        iconUrl = "http://openweathermap.org/img/wn/02d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (two.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/50d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (three.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/13d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (four.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/10d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (five.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/09d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (six.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/11d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (seven.contains(status)){
                        iconUrl = "http://openweathermap.org/img/wn/01d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);
    }

    private void updateWeatherMadurai() {

        Log.d(TAG,"inside update weather");

        Toast.makeText(this,"Accessing update weather",Toast.LENGTH_LONG).show();

        //first value lattitude and longitude  Geo coords chennai [13.0878, 80.2785],trichy Geo coords [10.8029, 78.6988]
        //kovai Geo coords [11, 76.9667] madurai Geo coords [9.9333, 78.1167] tanjore Geo coords [10.8, 79.15]
        //String key = "6091d7651a1cc6ab4b6e742d900dfb3d";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Madurai,in&appid=6091d7651a1cc6ab4b6e742d900dfb3d&units=metric";

        Log.d(TAG,"Before JSON request");

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG,"Before response value");
                Log.d(TAG,String.valueOf(response));

                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    JSONObject wind_object = response.getJSONObject("wind");

                    int temp = main_object.getInt("temp");
                    int humi = main_object.getInt("humidity");
                    int pressu = main_object.getInt("pressure");
                    String status = object.getString("description");
                    Double speed = wind_object.getDouble("speed");

                    //int temp_int = Integer.parseInt(temp);

                    mTemp.setText(String.valueOf(temp));
                    mHumidity.setText(String.valueOf(humi));
                    mPressure.setText(String.valueOf(pressu));
                    mStatus.setText(status);
                    mWind.setText(String.valueOf(speed));

                    String[] clear = {"clear sky"};
                    String[] clouds = {"few clouds","scattered clouds","broken clouds","overcast clouds"};
                    String[] atmo = {"mist","smoke","haze","sand/dust whirls","fog","sand","dust","volcanic ash","squalls","tornado"};
                    String[] snow = {"light snow","snow","heavy snow","sleet","light shower sleet","shower sleet","light rain and snow","rain and snow","light shower snow","shower snow","heavy shower snow"};
                    String[] rain = {"shower rain","light rain","moderate rain","heavy intensity rain","very heavy rain","extreme rain","freezing rain","light intensity shower rain","heavy intensity shower rain","ragged shower rain"};
                    String[] drizzle = {"drizzle","light intensity drizzle","heavy intensity drizzle","light intensity drizzle rain","drizzle rain","heavy intensity drizzle rain","shower rain and drizzle","heavy shower rain and drizzle","shower drizzle"};
                    String[] thunder = {"thunderstorm","thunderstorm with light rain","thunderstorm with rain","thunderstorm with heavy rain","light thunderstorm","heavy thunderstorm","ragged thunderstorm","thunderstorm with light drizzle","thunderstorm with drizzle","thunderstorm with heavy drizzle"};

                    //To check the condition

                    List one = Arrays.asList(clouds);
                    List two = Arrays.asList(atmo);
                    List three = Arrays.asList(snow);
                    List four = Arrays.asList(rain);
                    List five = Arrays.asList(drizzle);
                    List six = Arrays.asList(thunder);
                    List seven = Arrays.asList(clear);

                    String iconUrl = "";

                    if (one.contains(status)){

                        iconUrl = "https://openweathermap.org/img/wn/02d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (two.contains(status)){
                        iconUrl = "https://openweathermap.org/img/wn/50d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (three.contains(status)){
                        iconUrl = "https://openweathermap.org/img/wn/13d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (four.contains(status)){
                        iconUrl = "https://openweathermap.org/img/wn/10d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (five.contains(status)){
                        iconUrl = "https://openweathermap.org/img/wn/09d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (six.contains(status)){
                        iconUrl = "https://openweathermap.org/img/wn/11d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }
                    if (seven.contains(status)){
                        iconUrl = "https://openweathermap.org/img/wn/01d@2x.png";
                        Picasso.get().load(iconUrl).into(weather);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);
    }
}