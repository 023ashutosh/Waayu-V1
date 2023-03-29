package com.example.dumy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Screen extends AppCompatActivity {
    String name;

    DatabaseReference db;

    ImageView menu,home;

    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);


        menu=findViewById(R.id.ivmenu);
        home=findViewById(R.id.home);
        navigationView=findViewById(R.id.nav_view);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("clicked","Home");
            }
        });


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("clicked","yes");


            }
        });
        db=FirebaseDatabase.getInstance().getReference();
        db.child("User").child("Name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e("name",snapshot.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}