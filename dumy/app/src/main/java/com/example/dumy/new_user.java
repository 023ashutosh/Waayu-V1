package com.example.dumy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dumy.databinding.ActivityNewUserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class new_user extends AppCompatActivity {
    ActivityNewUserBinding binding;
    private FirebaseAuth auth;
    final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SharedPreferences preferences;
    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PASS="pass";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        preferences=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);



        binding.etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.etEmail.getText().toString().trim().matches(emailPattern) && editable.length() > 0) {
                    //Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                    // or
                    binding.check.setImageResource(R.drawable.tick);
                } else {
                    binding.check.setImageResource(R.drawable.cross);
                    binding.check.setVisibility(View.VISIBLE);
                    //Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    //or
                    //textView.setText("invalid email");
                }


            }
        });

        binding.etPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    binding.etPass.setHint(null);
                    binding.tilPass.setHint(null);
                }
            }
        });

        binding.etConfirmPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    binding.etPass.setHint(null);
                    binding.tilPass.setHint(null);
                }
            }
        });

        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etEmail.getText().toString()!= null && binding.etPass.getText().toString().equals(binding.etConfirmPass.getText().toString())) {
                    auth.createUserWithEmailAndPassword(binding.etEmail.getText().toString(), binding.etPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.e("Register","Yes");
                                FirebaseUser user=auth.getCurrentUser();
                                String uid=user.getUid();
                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putString("Email",binding.etEmail.getText().toString());
                                editor.putString("Password",binding.etPass.getText().toString());
                                editor.putString("UID",uid);
                                editor.apply();
                                Log.e("Register",uid);
                                Intent intent = new Intent(new_user.this, Registration.class);
                                intent.putExtra("signup", uid);
                                startActivity(intent);

                            } else {
                                Toast.makeText(new_user.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }


                    });


                }
                else
                {
                    Toast.makeText(new_user.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}