package com.example.dumy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;


public class trackHealth extends AppCompatActivity {
    ViewPager viewPager;
    slideImage slideImage;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_health);
        ll=findViewById(R.id.llFrag);

        viewPager=findViewById(R.id.vpTrans);
        slideImage=new slideImage(this);
        viewPager.setAdapter(slideImage);



        if(openalready())
        {
            startActivity(new Intent(trackHealth.this,SignUp.class));
        }
        else
        {
            SharedPreferences.Editor editor=getSharedPreferences("Slide",MODE_PRIVATE).edit();
            editor.putBoolean("Slide",true);
            editor.commit();
        }
    }
    public boolean openalready()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("Slide",MODE_PRIVATE);
        boolean result=sharedPreferences.getBoolean("Slide",false);
        return result;
    }
}