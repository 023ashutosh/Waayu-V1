package com.example.dumy;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class weather extends AppCompatActivity {

    String url;
    weather weather;
    ImageView ivIcon,rem;
    StorageReference sr;
    VideoView vw;



    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        TextView textView=findViewById(R.id.tvweather);
        ivIcon=findViewById(R.id.ivweather);
        rem=findViewById(R.id.ivRemedies);
        TextView textView5=findViewById(R.id.tvdesciption);

        vw=findViewById(R.id.vvweather);

        sr= FirebaseStorage.getInstance().getReference();

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            Toast.makeText(this, "Open Location", Toast.LENGTH_SHORT).show();
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();

            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        vw.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.buffer));
        vw.start();
        vw.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
                Log.e("VIDEO","STARTED");
            }
        });
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                //Toast.makeText(weather.this, "Lat"+location.getLatitude()+" Long "+location.getLongitude(), Toast.LENGTH_SHORT).show();

                try {
                    Geocoder geocoder=new Geocoder(weather.this);
                    List<Address> adress=geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    for (Address address : adress) {
                        Log.e("Adress ", address.getAddressLine(0));
                    }
                    Retrofit retrofit=Retrofitweather.getInstance();
                    API api=retrofit.create(API.class);
                    //vw.stopPlayback();
                    Log.e("Latitude",String.valueOf(location.getLatitude()));
                    api.gettemp(location.getLatitude(),location.getLongitude(),"878e9d69d2f2ab0f08ee9ecfc19ed7f5").enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                            JsonObject object=response.body();
                            if(object!=null)
                            {
                                Log.e("Data","Gotin");
                                JsonArray weather=object.getAsJsonArray("weather");
                                Log.e("Json",weather.get(0).toString());
                                JsonObject data=object.getAsJsonObject("main");
                                Log.e("Json",data.toString());
                                Log.e("Description",weather.get(0).getAsJsonObject().get("main").toString());
                                Log.e("Data",data.get("temp").toString());
                                String a1=data.get("temp").toString();
                                Double a=Double.parseDouble(a1)-273.15;

                                String f=data.get("humidity").toString();
                                String w=weather.get(0).getAsJsonObject().get("main").toString();
                                if(w.substring(1,w.length()-1).equalsIgnoreCase("rain") ||w.substring(1,w.length()-1).equalsIgnoreCase("mist")) {
                                    rem.setVisibility(View.VISIBLE);
                                    rem.setImageResource(R.drawable.rainyrem);
                                    Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/rain.png?alt=media&token=cb083508-03b4-4a0e-9e08-d85a887a6afa").into(ivIcon);
                                }
                                else if(w.substring(1,w.length()-1).equalsIgnoreCase("sunny")) {
                                    rem.setVisibility(View.VISIBLE);
                                    rem.setImageResource(R.drawable.summerrem);
                                    Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/sunny.png?alt=media&token=5c6f910d-a344-4ffc-a0eb-3552b09f9362").into(ivIcon);
                                }
                                else if(w.substring(1,w.length()-1).equalsIgnoreCase("thunderstorm")) {
                                    rem.setVisibility(View.VISIBLE);
                                    rem.setImageResource(R.drawable.rainyrem);
                                    Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/thunderstorm.png?alt=media&token=4e1cfd4b-5702-405c-a79b-9e56e7eecb5a").into(ivIcon);
                                }
                                else if(w.substring(1,w.length()-1).equalsIgnoreCase("clouds")) {
                                    rem.setVisibility(View.VISIBLE);
                                    rem.setImageResource(R.drawable.rainyrem);
                                    Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/cloudy.png?alt=media&token=d3270a99-ed3a-4558-8d60-afe0da292530").into(ivIcon);
                                }
                                else {
                                    rem.setVisibility(View.VISIBLE);
                                    Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/back.png?alt=media&token=344132f7-5d31-48c8-a961-d0d588a0824f").into(ivIcon);
                                }
                                textView.setText(a.toString().substring(0,5)+"°C");

                                textView5.setText(w.substring(1,w.length()-1));


                                vw.setVisibility(View.GONE);


                                Log.e("Image",object.getAsJsonArray("weather").get(0).getAsJsonObject().get("id").toString());
//                    int id=Integer.parseInt(object.getAsJsonArray("weather").get(0).getAsJsonObject().get("id").toString());
//                    Glide.with(getApplicationContext()).load("https://api.openweathermap.org/"+id).into(ivIcon);

                                //display();
                            }

                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Log.e("Data","Gotout");

                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }
                //url="https://api.openweathermap.org/data/2.5/weather?q=Lucknow&appid=878e9d69d2f2ab0f08ee9ecfc19ed7f5";
            }
        });













    }
}