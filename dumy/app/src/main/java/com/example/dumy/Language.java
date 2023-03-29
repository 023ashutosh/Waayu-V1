package com.example.dumy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class Language extends AppCompatActivity {
    int counter=0,prevPosition=0,currentPosition=0;
    Button btn;

    String lang[]={"English","हिन्दी","ગુજરાતી","ಕನ್ನಡ","தமிழ்","मराठी","മലയാളം","বাংলা","ਪੰਜਾਬੀ"};






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_language);
        btn=findViewById(R.id.btnNext);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.language_custom,R.id.tvCustom,lang);
        ListView lv=findViewById(R.id.lvLanguage);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                lv.setBackgroundResource(R.color.white);
//                view.setBackgroundResource(R.color.back);

                if(counter==0) {
                    prevPosition = i;
                }
                else if (counter ==1) {
                    currentPosition = i;
                }
                else {
                    prevPosition = currentPosition;
                    currentPosition = i;
                }
                lv.getChildAt(prevPosition).setBackgroundResource(R.color.white);
                view.setBackgroundResource(R.color.back);
                counter++;


            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Language.this,SignUp.class));
            }
        });


        }



}