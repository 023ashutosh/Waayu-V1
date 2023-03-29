package com.example.dumy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
ImageView ivGender,ivBlood,ivvaccine,ivdisease,skip;
AlertDialog dialog;
View view;
RadioGroup rbgender,rgblood,rgvaccine;
LinearLayout llDisease,cycledays;
CheckBox airth,diab,LowImmune,highbp,lowbp,pcos,pcod;
TextView tvgender,tvblood,tvvaccine,tvairth,tvdiab,tvlm,tvhigh,tvlow,tvpcos,tvpcod;
String blood_group,user_gender,vaccinated;
Button btnRegister;
EditText name,age,weight,height;
int gender=0,blood=0,vaccine=0,dis=0;
DatabaseReference db;
String uid;
SharedPreferences registeruid;
    private static final String UID_SP="myuid";
    private static final String UID_KEY="uid";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ivGender = findViewById(R.id.selectGender);
        ivBlood = findViewById(R.id.selectBloodGroup);
        ivvaccine = findViewById(R.id.selectVaccine);
        ivdisease = findViewById(R.id.selectDisease);
        skip = findViewById(R.id.skipRegister);
        rbgender = findViewById(R.id.rgGender);
        rgblood = findViewById(R.id.rgBloodGroup);
        rgvaccine = findViewById(R.id.rgvaccine);


        llDisease = findViewById(R.id.ll_Disease);
        cycledays = findViewById(R.id.cycledays);

        tvgender = findViewById(R.id.etGender);
        tvvaccine = findViewById(R.id.tvVaccine);
        tvblood = findViewById(R.id.tvBlood);

        tvairth = findViewById(R.id.tvAirth);
        tvdiab = findViewById(R.id.tvDiab);
        tvlm = findViewById(R.id.tvlowImm);
        tvhigh = findViewById(R.id.tvHighbp);
        tvlow = findViewById(R.id.tvLowbp);
        tvpcod = findViewById(R.id.tvPCOD);
        tvpcos = findViewById(R.id.tvPCOS);

        name = findViewById(R.id.etName);
        age = findViewById(R.id.etAge);
        weight = findViewById(R.id.etWeight);
        height = findViewById(R.id.etHeight);


        airth = findViewById(R.id.cbArth);
        diab = findViewById(R.id.cbDiabetes);
        LowImmune = findViewById(R.id.cbLowImmune);
        highbp = findViewById(R.id.cbHighBp);
        lowbp = findViewById(R.id.cbLowBp);
        pcos = findViewById(R.id.cbPCOS);
        pcod = findViewById(R.id.cbPCOD);

        btnRegister = findViewById(R.id.btnRegister);

        db = FirebaseDatabase.getInstance().getReference();
        registeruid = getSharedPreferences(UID_SP, MODE_PRIVATE);


        if (getIntent().getExtras().getString("login_uid") != null)
        {
            Log.e("Uid from login",uid.toString());

            uid = getIntent().getExtras().getString("login_uid");
    }
        else if(getIntent().getExtras().getString("google_uid")!=null) {
            Log.e("Uid from google",uid.toString());
            uid = getIntent().getExtras().getString("google_uid");
        }
        else if(getIntent().getExtras().getString("signup")!=null) {

            uid = getIntent().getExtras().getString("signup");
            Log.e("Uid from signup",uid.toString());
        }
        else
        Log.e("Uid from signup","No uid");





        //Log.e("Register Sp",registeruid.getString("UID",null).toString());
//
//
//        if(registeruid.getString("UID",null)==(uid))
//        {
//            Log.e("BIOmettric uid","yes");
//            Intent intent=new Intent(Registration.this,NavDrawerActivity.class);
//            intent.putExtra("existinguid",uid);
//            startActivity(intent);
//        }
//        else
//            Log.e("BIOmettric uid","No" +registeruid.getString("UID",null));







        ivGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender++;
                rgblood.setVisibility(View.GONE);
                rgvaccine.setVisibility(View.GONE);
                llDisease.setVisibility(View.GONE);

                if(gender%2!=0)
                {
                    rbgender.setVisibility(View.VISIBLE);
                }
                else
                {
                    rbgender.setVisibility(View.GONE);
                }

                rbgender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i)
                        {
                            case R.id.rbMale:;
                            user_gender="Male";
                            Log.e("Gender","Male");
                            tvgender.setText("Male");
                            cycledays.setVisibility(View.GONE);
                            pcod.setVisibility(View.GONE);
                            pcos.setVisibility(View.GONE);
                            break;
                            case R.id.rbFemale:;
                                user_gender="Female";
                                Log.e("Gender","Female");
                                tvgender.setText("Female");
                                cycledays.setVisibility(View.VISIBLE);
                                pcod.setVisibility(View.VISIBLE);
                                pcos.setVisibility(View.VISIBLE);
                                break;
                            case R.id.rbPrefer:;
                                user_gender="Prefer not say";
                                Log.e("Gender","None");
                                tvgender.setText("Custom");
                                cycledays.setVisibility(View.GONE);
                                pcod.setVisibility(View.GONE);
                                pcos.setVisibility(View.GONE);
                                break;


                        }
                    }
                });



            }
        });

        ivBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blood++;
                rbgender.setVisibility(View.GONE);

                rgvaccine.setVisibility(View.GONE);
                llDisease.setVisibility(View.GONE);

                if(blood%2!=0)
                {
                    rgblood.setVisibility(View.VISIBLE);
                }
                else
                {
                    rgblood.setVisibility(View.GONE);
                }

                rgblood.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i)
                        {
                            case R.id.rbAp:;
                                blood_group="A+";
                                Log.e("Blood","A+");
                                tvblood.setText("A+");
                                break;
                            case R.id.rbAn:;
                                blood_group="A-";
                                Log.e("Blood","A-");
                                tvblood.setText("A-");
                                break;
                            case R.id.rbBp:;
                                blood_group="B+";
                                Log.e("Blood","B+");
                                tvblood.setText("B+");
                                break;
                            case R.id.rbBn:;
                                blood_group="B-";
                                Log.e("Blood","B-");
                                tvblood.setText("B-");
                                break;

                            case R.id.rbABp:;
                                blood_group="AB+";
                                Log.e("Blood","AB+");
                                tvblood.setText("AB+");
                                break;
                            case R.id.rbABn:;
                                blood_group="AB-";
                                Log.e("Blood","AB-");
                                tvblood.setText("AB-");
                                break;
                            case R.id.rbOn:;
                                blood_group="O-";
                                Log.e("Blood","O-");
                                tvblood.setText("O-");
                                break;
                            case R.id.rbOp:;
                                blood_group="O+";
                                Log.e("Blood","O+");
                                tvblood.setText("O+");
                                break;



                        }

                    }
                });




            }
        });



        ivdisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dis++;
                rgvaccine.setVisibility(View.GONE);
                rbgender.setVisibility(View.GONE);
                rgblood.setVisibility(View.GONE);
                if(dis%2!=0)
                {
                    llDisease.setVisibility(View.VISIBLE);
                }
                else
                {
                    llDisease.setVisibility(View.GONE);
                }

                airth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b)
                        {
                            Log.e("status", "checked");
                            tvairth.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Log.e("status","Unchecked");
                            tvairth.setVisibility(View.GONE);
                    }
                    }
                });

                diab.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b)
                        {
                            Log.e("status", "checked");
                            tvdiab.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Log.e("status","Unchecked");
                            tvdiab.setVisibility(View.GONE);
                        }
                    }
                });

                LowImmune.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b)
                        {
                            Log.e("status", "checked");
                            tvlm.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Log.e("status","Unchecked");
                            tvlm.setVisibility(View.GONE);
                        }
                    }
                });

                highbp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b)
                        {
                            Log.e("status", "checked");
                            tvhigh.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Log.e("status","Unchecked");
                            tvhigh.setVisibility(View.GONE);
                        }
                    }
                });

                lowbp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b)
                        {
                            Log.e("status", "checked");
                            tvlow.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Log.e("status","Unchecked");
                            tvlow.setVisibility(View.GONE);
                        }
                    }
                });

                pcos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b)
                        {
                            Log.e("status", "checked");
                            tvpcos.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Log.e("status","Unchecked");
                            tvpcos.setVisibility(View.GONE);
                        }
                    }
                });

                pcod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b)
                        {
                            Log.e("status", "checked");
                            tvpcod.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Log.e("status","Unchecked");
                            tvpcod.setVisibility(View.GONE);
                        }
                    }
                });




            }
        });

        ivvaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaccine++;
                llDisease.setVisibility(View.GONE);
                rbgender.setVisibility(View.GONE);
                rgblood.setVisibility(View.GONE);

                if(vaccine%2!=0)
                {
                    rgvaccine.setVisibility(View.VISIBLE);
                }
                else
                {
                    rgvaccine.setVisibility(View.GONE);
                }

                rgvaccine.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i)
                        {
                            case R.id.rbYes:;
                                Log.e("Vaccinated","Yes");
                                tvvaccine.setText("Yes");
                                break;
                            case R.id.rbNo:;
                                Log.e("Vaccinated","No");
                                tvvaccine.setText("No");
                                break;

                        }
                    }
                });


            }
        });

//        skip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                startActivity(new Intent(Registration.this,NavDrawerActivity.class));
//                finishAffinity();
//            }
//        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name!=null && age!=null && weight!=null && height!=null && user_gender!=null && blood_group!=null)
                {
                    Log.e("data","sent all");
                User user=new User(name.getText().toString(),Integer.parseInt(age.getText().toString()),
                        Double.parseDouble(weight.getText().toString()),Double.parseDouble(height.getText().toString()),user_gender,blood_group);
                    Log.e("USer ",user.toString());

                    db.child("Users").child(uid.toString()).child("Name").setValue(user.name);
                    db.child("Users").child(uid.toString()).child("Age").setValue(user.yrs);
                    db.child("Users").child(uid.toString()).child("Gender").setValue(user.gender);
                    db.child("Users").child(uid.toString()).child("Height").setValue(user.height);
                    db.child("Users").child(uid.toString()).child("Weight").setValue(user.weight);
                    db.child("Users").child(uid.toString()).child("UID").setValue(uid);


//                    SharedPreferences.Editor editor=registeruid.edit();
//                    editor.putString("UID",uid);
//                    editor.apply();



                Intent intent=new Intent(Registration.this,manual.class);
                    intent.putExtra("uid",uid.toString());
                    Log.e("uid sent manual",uid.toString());
                    startActivity(intent);
                    finishAffinity();
            }
            }
        });

//        if(openalready())
//        {
//            startActivity(new Intent(Registration.this,Screen.class));
//        }
//        else
//        {
//            SharedPreferences.Editor editor=getSharedPreferences("Slide",MODE_PRIVATE).edit();
//            editor.putBoolean("Slide",true);
//            editor.commit();
//        }

    }

//    public boolean openalready()
//    {
//        SharedPreferences sharedPreferences=getSharedPreferences("Slide",MODE_PRIVATE);
//        boolean result=sharedPreferences.getBoolean("Slide",false);
//        return result;
//    }
}