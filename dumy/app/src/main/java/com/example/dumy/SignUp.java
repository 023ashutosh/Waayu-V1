package com.example.dumy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
TextView tv,tvnew;
private ImageView signInButton,skip;
private FirebaseAuth auth;
private GoogleSignInClient signInClient;
private int RC_SIGN_IN=1;
ProgressDialog progressDialog;
EditText etEmail,etPass;
Button btnLogin;
TextInputLayout til;
ImageView check;
FirebaseDatabase db;
SharedPreferences sp;
 String uid;
private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PASS="pass";

    final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog=new ProgressDialog(SignUp.this);
        progressDialog.setMessage("Signing in");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_login);
        tv=findViewById(R.id.textView6);
        tvnew=findViewById(R.id.tvNewAccount);

        signInButton=findViewById(R.id.GoogleSign);

        etEmail=findViewById(R.id.etEmail);
        etPass=findViewById(R.id.etPass);
        til=findViewById(R.id.tilPass);
        check=findViewById(R.id.check);
        skip=findViewById(R.id.guest);
        btnLogin=findViewById(R.id.btnLogin);

        db=FirebaseDatabase.getInstance();

        auth=FirebaseAuth.getInstance();

        sp=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String email=sp.getString("Email",null);
        if(email!=null)
        {
            String id=sp.getString("UID",null);
            Log.e("uid id",id);
            Intent intent=new Intent(SignUp.this,NavDrawerActivity.class);

            intent.putExtra("spuid",id);

            startActivity(intent);
            finishAffinity();
        }

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etEmail.getText().toString().trim().matches(emailPattern) && editable.length() > 0) {
                    //Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                    // or
                    check.setImageResource(R.drawable.tick);
                } else {
                    check.setImageResource(R.drawable.cross);
                    check.setVisibility(View.VISIBLE);
                    //Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    //or
                    //textView.setText("invalid email");
                }


            }
        });

        etPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    etPass.setHint(null);
                til.setHint(null);
            }}
        });


        //Adding User Down
//        auth.createUserWithEmailAndPassword("SIH_Dk736@gmail.com","Dk@736").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//            if(task.isSuccessful())
//            {
//                Log.e("status", "user created");
//            }
//            else
//                Log.e("status","user not created");
//            }
//        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,Registration.class));
            }
        });

        tvnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,new_user.class));
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                if(etEmail!=null && etPass!=null)
                {
                    Log.e("status",etEmail.getText().toString());
                    auth.signInWithEmailAndPassword(etEmail.getText().toString(),etPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                uid=auth.getCurrentUser().getUid();
                                SharedPreferences.Editor editor=sp.edit();
                                editor.putString("Email",etEmail.getText().toString());
                                editor.putString("Password",etPass.getText().toString());
                                editor.putString("UID",uid);
                                editor.apply();



                                Log.e("EmailSignIn ",uid);
                                progressDialog.dismiss();
                                Intent intent=new Intent(SignUp.this,Registration.class);
                                intent.putExtra("login_uid",uid);
                                startActivity(intent);

                                finishAffinity();
                                Log.e("status","User signed in "+uid);

                            }
                            else
                            {
                                Log.e("status","User Failed");
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), task.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });





        GoogleSignInOptions googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();

        signInClient= GoogleSignIn.getClient(this,googleSignInOptions);



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                progressDialog.show();
                signin();
            }
        });


    }
    private void signin()
    {
        Intent signIntent=signInClient.getSignInIntent();
        startActivityForResult(signIntent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN)
        {
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            handlesignin(task);
        }
    }

    private void handlesignin(Task<GoogleSignInAccount> completedtask)
    {
        try {
            GoogleSignInAccount account=completedtask.getResult(ApiException.class);
            firebaseauth(account);
            Toast.makeText(this,"Signed in",Toast.LENGTH_SHORT).show();
            Log.e("status","got in");
        }
        catch (ApiException e)
        {
            progressDialog.dismiss();
            Log.e("status","got out");
            Toast.makeText(this,e.getMessage()+"",Toast.LENGTH_SHORT).show();
        }


    }

    private void firebaseauth(GoogleSignInAccount sign)

    {
        AuthCredential authCredential= GoogleAuthProvider.getCredential(sign.getIdToken(),null);
        auth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    uid=auth.getCurrentUser().getUid();
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("SignIn","Yes");
                    editor.putString("UID",uid);
                    editor.apply();
                    progressDialog.dismiss();
                    Toast.makeText(SignUp.this,"Sign succefull",Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseUser=auth.getCurrentUser();

                    Log.e("GoogleSignIn ",uid);
                    updateui(firebaseUser);
                    Intent intent=new Intent(SignUp.this,Registration.class);
                    intent.putExtra("google_uid",uid);
                    startActivity(intent);

                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(SignUp.this,"Failed",Toast.LENGTH_SHORT).show();
                    updateui(null);

                }
            }
        });
    }

    private void updateui(FirebaseUser User)
    {
        GoogleSignInAccount gsoc=GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(gsoc!=null)
        {

            startActivity(new Intent(SignUp.this,Screen.class));
            Toast.makeText(getApplicationContext(),gsoc.getDisplayName(),Toast.LENGTH_SHORT).show();
        }
    }
}