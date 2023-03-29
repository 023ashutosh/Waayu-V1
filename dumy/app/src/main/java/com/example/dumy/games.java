package com.example.dumy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dumy.databinding.ActivityGamesBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class games extends AppCompatActivity {
    DatabaseReference dr;
    ActivityGamesBinding binding;
    int answer;
    String game;
    int won=0;
    int active=0,flag=0;
    String winner;
    Dialog dialog;
    int gametic[]={2,2,2,2,2,2,2,2,2};
    int winpos[][]={{1,2,3},{1,5,9},{1,4,7},
            {2,5,8},{3,6,9},{3,5,7},
            {4,5,6},{7,8,9}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGamesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dr= FirebaseDatabase.getInstance().getReference();

        game=getIntent().getStringExtra("game");

        if(game.equals("kbc")) {
            binding.KBC.setVisibility(View.VISIBLE);
            binding.GuessCalorie.setVisibility(View.GONE);
            binding.grid.setVisibility(View.GONE);
            int q = (int) Math.floor(Math.random() * 5) + 1;

            if (q == 1) {
                answer = q;
                dr.child("KBC").child(String.valueOf(q)).child("ques").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvQues.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op1").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop1.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op2").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop2.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op3").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop3.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op4").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop4.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
            if (q == 2) {
                answer = q;
                dr.child("KBC").child(String.valueOf(q)).child("ques").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvQues.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op1").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop1.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op2").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop2.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op3").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop3.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op4").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop4.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
            if (q == 3) {
                answer = q;
                dr.child("KBC").child(String.valueOf(q)).child("ques").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvQues.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op1").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop1.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op2").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop2.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op3").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop3.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op4").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop4.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
            if (q == 4) {
                answer = q;
                dr.child("KBC").child(String.valueOf(q)).child("ques").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvQues.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op1").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop1.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op2").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop2.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op3").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop3.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op4").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop4.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
            if (q == 5) {
                answer = 1;
                dr.child("KBC").child(String.valueOf(q)).child("ques").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvQues.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op1").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop1.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op2").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop2.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op3").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop3.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dr.child("KBC").child(String.valueOf(q)).child("op4").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Log.e("Ques ", snapshot.getValue().toString());
                            binding.tvop4.setText(snapshot.getValue().toString());
                        } else
                            Log.e("Ques ", "Non exist");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            binding.tvop1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Integer.parseInt(binding.tvop1.getTag().toString()) == answer) {
                        {

                            Toast.makeText(games.this, "Correct", Toast.LENGTH_SHORT).show();
                            Log.e("Status", "Correct");
                        }
                    } else
                        Log.e("Status", "Wrong");


                }
            });
            binding.tvop2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Integer.parseInt(binding.tvop2.getTag().toString()) == answer) {
                        Toast.makeText(games.this, "Correct", Toast.LENGTH_SHORT).show();
                        Log.e("Status", "Correct");
                    } else
                        Log.e("Status", "Wrong");

                }
            });
            binding.tvop3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Integer.parseInt(binding.tvop3.getTag().toString()) == answer) {
                        Toast.makeText(games.this, "Correct", Toast.LENGTH_SHORT).show();
                        Log.e("Status", "Correct");
                    } else
                        Log.e("Status", "Wrong");
                }
            });
            binding.tvop4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Integer.parseInt(binding.tvop4.getTag().toString()) == answer) {
                        Toast.makeText(games.this, "Correct", Toast.LENGTH_SHORT).show();
                        Log.e("Status", "Correct");
                    } else
                        Log.e("Status", "Wrong");

                }
            });


        }

        else if(game.equals("guess")) {
            binding.KBC.setVisibility(View.GONE);
            binding.GuessCalorie.setVisibility(View.VISIBLE);
            binding.grid.setVisibility(View.GONE);

            binding.btnrest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.et.setText(null);
                    binding.btnrest.setText("restart");
                    int b=num();
                    binding.btnGuess.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            check(Integer.parseInt(binding.et.getText().toString()),b);
                        }
                    });

                }
            });

        }

        else if(game.equals("tictac")) {
            binding.KBC.setVisibility(View.GONE);
            binding.GuessCalorie.setVisibility(View.GONE);
            binding.grid.setVisibility(View.VISIBLE);

        }
    }



    public String dip(View view) {
        ImageView counter = (ImageView) view;
        for (int i = 0; i < 9; i++) {


            int tag = Integer.parseInt(counter.getTag().toString());
            if (gametic[tag - 1] == 2) {

                counter.setTranslationY(-1500);
                if (active == 0) {
                    gametic[tag - 1] = 1;
                    Toast.makeText(this, "O turn", Toast.LENGTH_SHORT).show();
                    counter.setImageResource(R.drawable.tr1);
                    active = 1;
                } else {
                    gametic[tag - 1] = 0;
                    Toast.makeText(this, "X turn", Toast.LENGTH_SHORT).show();
                    counter.setImageResource(R.drawable.o);
                    active = 0;
                }
                counter.animate().translationYBy(1500).setDuration(300);
                // checking winner
                for (int[] win : winpos) {
                    if (gametic[win[0] - 1] == gametic[win[1] - 1] && gametic[win[1] - 1] == gametic[win[2] - 1] && gametic[win[0] - 1] != 2) {
                        if (gametic[win[0] - 1] == 0) {
                            Toast.makeText(this, "0 won", Toast.LENGTH_SHORT).show();
                            Log.e("Winner", "O");
                            flag = 1;
                            winner="O";
                            break;
                        } else {
                            Toast.makeText(this, "X won", Toast.LENGTH_SHORT).show();
                            Log.e("Winner", "X");
                            flag = 1;
                            winner="X";
                            break;
                        }
                    }

                }


                if (flag == 1) {
                    break;
                }

            }
        }
        return winner;
    }

    public int num()
    {
        int c=(int)Math.floor(Math.random()*10)+1;
        return c;

    }

    public void check(int a,int b)
    {

        if(a>b)
            Toast.makeText(this, "Smaller", Toast.LENGTH_SHORT).show();
        else if(a<b)
            Toast.makeText(this, "Greater", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Matched", Toast.LENGTH_SHORT).show();
    }



}
