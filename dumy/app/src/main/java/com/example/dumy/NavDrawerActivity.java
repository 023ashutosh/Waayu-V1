package com.example.dumy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dumy.databinding.ActivityNavDrawer2Binding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class NavDrawerActivity extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavDrawer2Binding binding;
    int cups=0;
    int flag=1,count=1;

   String name,gender;
   String age;
           private static String uid="";
   SharedPreferences sphome;
   DatabaseReference databaseReference;
   StorageReference sr;
   ProgressDialog pd;
   Double ht,wt;
   int bmi;
    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PASS="pass";
    String s[];





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavDrawer2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        Glide.with(NavDrawerActivity.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/editmetric.png?alt=media&token=b625b732-a9a2-4e6b-8bb2-358f52fc4048").into(binding.appBarNavDrawer2.screen.ivcounter1);
//
//        Glide.with(NavDrawerActivity.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/appversion.png?alt=media&token=8ada92e2-143f-4977-864c-7b72bc61575c").into(binding.appBarNavDrawer2.screen.ivcounter2);
//
//        Glide.with(NavDrawerActivity.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/contactus.png?alt=media&token=cf114f95-9e9a-46c6-8a90-644d4c1fcfd1").into(binding.appBarNavDrawer2.screen.ivcounter4);
//
//        Glide.with(NavDrawerActivity.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/info.png?alt=media&token=fed7b713-f5ba-4c1f-ba4f-a6ebb94031a2").into(binding.appBarNavDrawer2.screen.ivcounter3);


        ImageView iv1=findViewById(R.id.ivcounter1);
        ImageView iv2=findViewById(R.id.ivcounter2);
        ImageView iv3=findViewById(R.id.ivcounter3);
        ImageView iv4=findViewById(R.id.ivcounter4);
        ImageView iv5=findViewById(R.id.ivcounter5);


        ArrayList<list> arrayList=new ArrayList<list>();
        arrayList.add(new list("Edit Profile",R.drawable.editmetric));
        arrayList.add(new list("Update app",R.drawable.appversion));
        arrayList.add(new list("About us",R.drawable.info));
        arrayList.add(new list("Contact",R.drawable.contactus));
        arrayList.add(new list("Logout",R.drawable.lgout));


        //Log.e("ResourceID",String.valueOf(binding.appBarNavDrawer2.screen.ivcounter4.getId()));


        pd=new ProgressDialog(NavDrawerActivity.this);
        pd.setMessage("Fetching data");
        pd.show();

        if(getIntent().getExtras().getString("newUID")!=null)
        {
            flag=0;
            uid = getIntent().getExtras().getString("newUID");
            Log.e("New UID",uid);
        }
        else if(getIntent().getExtras().getString("spuid")!=null)
        {
            flag=1;
            uid = getIntent().getExtras().getString("spuid");
            Log.e("uid through sp",uid);
        }
        else if(getIntent().getExtras().getStringArray("HealthMetrics")!=null)
        {
            flag=1;
             s= getIntent().getExtras().getStringArray("HealthMetrics");
             Log.e("Value Came",s[6]);
            binding.appBarNavDrawer2.screen.tvTemp.setText(s[4]);
            binding.appBarNavDrawer2.screen.StepProgress.setProgress(Integer.parseInt(s[0]));
            binding.appBarNavDrawer2.screen.Progress5.setProgress(1);
        }


        sr= FirebaseStorage.getInstance().getReference();
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/news1.png?alt=media&token=8f66223d-96b0-4a99-8909-4a321f603bd6").into(binding.appBarNavDrawer2.screen.ivnews1);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/news2.png?alt=media&token=b88ac5b5-26ea-4d87-86d6-0469def4ca70").into(binding.appBarNavDrawer2.screen.ivnews2);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/news3.png?alt=media&token=c59c7730-a79b-4292-b573-3aeb2a569f6a").into(binding.appBarNavDrawer2.screen.ivnews3);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/news4.png?alt=media&token=dcf48389-a4fa-454a-897b-0797eec54a8b").into(binding.appBarNavDrawer2.screen.ivnews4);


        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/tic-tac-toe.png?alt=media&token=46f5d8fa-fa98-462a-8249-4d28988c02b1").into(binding.appBarNavDrawer2.screen.ivtictac);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/runner.png?alt=media&token=c09d3157-1a7f-4872-9dae-2aedc45e8636").into(binding.appBarNavDrawer2.screen.Guess);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/reflex.png?alt=media&token=032c4798-d446-410d-b14b-a80163c3b3d8").into(binding.appBarNavDrawer2.screen.ivkbc);


        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Users").child(uid).child("Name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    name= snapshot.getValue().toString();
                    String fname[]=name.split(" ");
                    Log.e("fname",fname[0]);
                    binding.appBarNavDrawer2.screen.tvHomeName.setText("Hi "+ fname[0]+",");
                    binding.appBarNavDrawer2.screen.tvAccountName.setText(name);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        databaseReference.child("Users").child(uid).child("Age").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    age= snapshot.getValue().toString();
                    binding.appBarNavDrawer2.screen.tvAccountAge.setText(age+" , ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.child("Users").child(uid).child("Gender").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    gender= snapshot.getValue().toString();
                    binding.appBarNavDrawer2.screen.tvAccountGender.setText(gender);
                    binding.appBarNavDrawer2.screen.share.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(NavDrawerActivity.this,Health.class);
                            intent.putExtra("gender",gender);
                            intent.putExtra("Metrics",s);
                            startActivity(intent);


                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.child("Users").child(uid).child("Height").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    Log.e("height",snapshot.getValue().toString());
                    ht=Double.parseDouble(snapshot.getValue().toString());
                    ht=ht/100;
                    ht=ht*ht;
                    ht=1/ht;
                    Log.e("height",String.valueOf(ht));
                    databaseReference.child("Users").child(uid).child("Weight").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists())
                            {
                                Log.e("weight",snapshot.getValue().toString());
                                wt=Double.parseDouble(snapshot.getValue().toString());

                                Log.e("weight",String.valueOf(wt/ht));
                                bmi=(int)(wt*ht);
                                pd.setMessage("Loading Aasans");

                                if(bmi>=18.5 && bmi<=24.9)
                                {
                                    Glide.with(NavDrawerActivity.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/Normal1.png?alt=media&token=044ca84e-8fc3-4a3a-bebb-dfe69e074ba7").into(binding.appBarNavDrawer2.screen.aasan1);
                                    Glide.with(NavDrawerActivity.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/Normal%202.png?alt=media&token=0f12e402-85b7-4563-9e31-d1c467cf1b48").into(binding.appBarNavDrawer2.screen.aasan2);
                                    binding.appBarNavDrawer2.screen.tvBMI.setText("BMI: "+String.valueOf(bmi)+" - Normal");
                                    pd.dismiss();
                                }
                                else if(bmi<18.5)
                                {

                                    Glide.with(NavDrawerActivity.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/Skinny1.png?alt=media&token=91ca461b-c382-4b07-9d05-ebed4ae29a85").into(binding.appBarNavDrawer2.screen.aasan1);
                                    Glide.with(NavDrawerActivity.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/Skinny%202.png?alt=media&token=ef9a41bb-fb08-414a-b5fd-77b5861e756c").into(binding.appBarNavDrawer2.screen.aasan2);



                                    binding.appBarNavDrawer2.screen.tvBMI.setText("BMI: "+String.valueOf(bmi)+"-Underweight");
                                    pd.dismiss();


                                }

                                else if (bmi>=25.0 && bmi<=29.9)
                                {
                                    Glide.with(NavDrawerActivity.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/Obese1.png?alt=media&token=4f4b90e5-10c6-4203-a3c2-663ae8e97134").into(binding.appBarNavDrawer2.screen.aasan1);
                                    Glide.with(NavDrawerActivity.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/Obese%202.png?alt=media&token=ac51750b-81c2-4b0c-b3bb-4d0d6cbefbba").into(binding.appBarNavDrawer2.screen.aasan2);

                                    binding.appBarNavDrawer2.screen.tvBMI.setText("BMI: "+String.valueOf(bmi)+"-Overweight");
                                    pd.dismiss();
                            }
                                else {
                                    binding.appBarNavDrawer2.screen.tvBMI.setText("BMI: " + String.valueOf(bmi) + "Obesity");
                                    pd.dismiss();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });














        adapter arrayAdapter=new adapter(NavDrawerActivity.this,R.layout.list_view_account,arrayList);


        binding.appBarNavDrawer2.screen.lvAccount.setAdapter(arrayAdapter);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        binding.appBarNavDrawer2.screen.cloud.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cloud));
        binding.appBarNavDrawer2.screen.cloud.start();

        binding.appBarNavDrawer2.screen.cloud.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        binding.appBarNavDrawer2.screen.ivmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAppBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                        .setOpenableLayout(drawer)
                        .build();
                binding.drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        binding.appBarNavDrawer2.screen.ivgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("games","clicked");
                binding.appBarNavDrawer2.screen.ivgame.setImageResource(R.drawable.games_dark);
                binding.appBarNavDrawer2.screen.ivhome.setImageResource(R.drawable.home);
                binding.appBarNavDrawer2.screen.ivaccount.setImageResource(R.drawable.account);
                binding.appBarNavDrawer2.screen.ivwifi.setImageResource(R.drawable.wifi_light);
                binding.appBarNavDrawer2.screen.ivnews.setImageResource(R.drawable.lnews);
                binding.appBarNavDrawer2.screen.wifi.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.svnews.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.displayaccount.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.svGames.setVisibility(View.VISIBLE);
                binding.appBarNavDrawer2.screen.svHome.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.cloud.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                    }
                });


            }
        });

        binding.appBarNavDrawer2.screen.ivnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.appBarNavDrawer2.screen.ivgame.setImageResource(R.drawable.game);
                binding.appBarNavDrawer2.screen.ivhome.setImageResource(R.drawable.home);
                binding.appBarNavDrawer2.screen.ivaccount.setImageResource(R.drawable.account);
                binding.appBarNavDrawer2.screen.ivwifi.setImageResource(R.drawable.wifi_light);
                binding.appBarNavDrawer2.screen.ivnews.setImageResource(R.drawable.news_dark);

                binding.appBarNavDrawer2.screen.wifi.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.displayaccount.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.svGames.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.svHome.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.svnews.setVisibility(View.VISIBLE);

                binding.appBarNavDrawer2.screen.cloud.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                    }
                });
            }
        });

        binding.appBarNavDrawer2.screen.ivwifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.appBarNavDrawer2.screen.cloud.start();
                binding.appBarNavDrawer2.screen.cloud.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                    }
                });
                binding.appBarNavDrawer2.screen.ivgame.setImageResource(R.drawable.game);
                binding.appBarNavDrawer2.screen.ivhome.setImageResource(R.drawable.home);
                binding.appBarNavDrawer2.screen.ivaccount.setImageResource(R.drawable.account);
                binding.appBarNavDrawer2.screen.ivwifi.setImageResource(R.drawable.wifi_dark);
                binding.appBarNavDrawer2.screen.ivnews.setImageResource(R.drawable.lnews);

                binding.appBarNavDrawer2.screen.svHome.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.svnews.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.displayaccount.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.svGames.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.wifi.setVisibility(View.VISIBLE);



                binding.appBarNavDrawer2.screen.btnConnect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(NavDrawerActivity.this,Bluetooth.class));
                    }
                });


            }
        });

        binding.appBarNavDrawer2.screen.ivhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.appBarNavDrawer2.screen.ivgame.setImageResource(R.drawable.game);
                binding.appBarNavDrawer2.screen.ivhome.setImageResource(R.drawable.home_dark);
                binding.appBarNavDrawer2.screen.ivaccount.setImageResource(R.drawable.account);
                binding.appBarNavDrawer2.screen.ivwifi.setImageResource(R.drawable.wifi_light);
                binding.appBarNavDrawer2.screen.ivnews.setImageResource(R.drawable.lnews);

                binding.appBarNavDrawer2.screen.svGames.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.wifi.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.svnews.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.displayaccount.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.svHome.setVisibility(View.VISIBLE);

            }
        });

        binding.appBarNavDrawer2.screen.ivaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.appBarNavDrawer2.screen.ivgame.setImageResource(R.drawable.game);
                binding.appBarNavDrawer2.screen.ivhome.setImageResource(R.drawable.home);
                binding.appBarNavDrawer2.screen.ivaccount.setImageResource(R.drawable.account_dark);
                binding.appBarNavDrawer2.screen.ivwifi.setImageResource(R.drawable.wifi_light);
                binding.appBarNavDrawer2.screen.ivnews.setImageResource(R.drawable.lnews);

                binding.appBarNavDrawer2.screen.svGames.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.svnews.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.wifi.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.svHome.setVisibility(View.GONE);
                binding.appBarNavDrawer2.screen.displayaccount.setVisibility(View.VISIBLE);

            }
        });


        binding.appBarNavDrawer2.screen.lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("Item clicked",String.valueOf(i));
                if(i==4)
                {


                    sphome=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
                    SharedPreferences.Editor editor=sphome.edit();
                    editor.clear();
                    editor.commit();

                    Intent intent=new Intent(NavDrawerActivity.this,SignUp.class);
                    finish();
                    startActivity(intent);
                }
            }
        });



        binding.appBarNavDrawer2.screen.ivaddcups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cups<8)
                {

                    cups++;
                    //String pos="binding.appBarNavDrawer2.screen.ivcup"+cups;
                    //int res=NavDrawerActivity.this.getResources().getIdentifier(pos,"string",NavDrawerActivity.this.getPackageName());
                    //binding.appBarNavDrawer2.screen.cupContainer.getTag(cups).toString();
                    //Log.e("Cup",pos);
                    //Log.e("Res",String.valueOf(res));

                //Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/glass_dark.png?alt=media&token=fde7d156-5552-4375-9f52-08dab0ff4d6a").into("binding.appBarNavDrawer2.screen.ivcup"+cups);
                binding.appBarNavDrawer2.screen.tvcups.setText(String.valueOf(cups)+"/8 cups");
            }
            }
        });


        binding.appBarNavDrawer2.screen.lltictac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NavDrawerActivity.this,games.class);
                intent.putExtra("game","tictac");
                startActivity(intent);

            }
        });

        binding.appBarNavDrawer2.screen.llguess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(NavDrawerActivity.this,games.class);
                intent.putExtra("game","guess");
                startActivity(intent);

            }
        });

        binding.appBarNavDrawer2.screen.llkbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NavDrawerActivity.this,games.class);
                intent.putExtra("game","kbc");
                startActivity(intent);

            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_helpline)
                {
                    String phone = "1075";
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    startActivity(intent);
                }
                else if(item.getItemId()==R.id.dincharya)
                {
                    startActivity(new Intent(NavDrawerActivity.this,Dhincharya.class));
                }
                else if(item.getItemId()==R.id.Ayushop)
                {
                    startActivity(new Intent(NavDrawerActivity.this,shop.class));
                }
                else if(item.getItemId()==R.id.bar)
                {
                    Intent intent=new Intent(NavDrawerActivity.this,weather.class);
                    startActivity(intent);

                }


                return false;
            }
        });


        binding.appBarNavDrawer2.screen.Progress.setProgress(50);
        binding.appBarNavDrawer2.screen.Progress2.setProgress(50);

        binding.appBarNavDrawer2.screen.Progress3.setProgress(100);
        binding.appBarNavDrawer2.screen.Progress4.setProgress(100);

        binding.appBarNavDrawer2.screen.Progress6.setProgress(100);


//        binding.appBarNavDrawer2.screen.tvBMI.setText();




    }

    @Override
    protected void onStart() {
        binding.appBarNavDrawer2.screen.cloud.start();
        binding.appBarNavDrawer2.screen.cloud.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        Log.e("start","yes");

        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.e("paused","yes");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.e("resume","yes");
        binding.appBarNavDrawer2.screen.cloud.start();
        binding.appBarNavDrawer2.screen.cloud.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_drawer2);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}