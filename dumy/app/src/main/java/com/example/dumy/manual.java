package com.example.dumy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dumy.databinding.ActivityManualBinding;

public class manual extends AppCompatActivity {
    ActivityManualBinding binding;
    String uid;
    int c=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityManualBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        uid=getIntent().getExtras().getString("uid");

        binding.app1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.app1.setVisibility(View.GONE);
                binding.app2.setVisibility(View.VISIBLE);
                binding.app2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.app2.setVisibility(View.GONE);
                        binding.app3.setVisibility(View.VISIBLE);
                        binding.app3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                binding.app3.setVisibility(View.GONE);
                                binding.app4.setVisibility(View.VISIBLE);
                                binding.app4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Log.e("Manual","complete");
                                        Intent intent =new Intent(manual.this,NavDrawerActivity.class);
                                        intent.putExtra("newUID",uid);
                                        startActivity(intent);
                                        finishAffinity();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });




    }
}