package com.example.dumy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dumy.databinding.ActivityHealthBinding;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Health extends AppCompatActivity {
ActivityHealthBinding binding;
ProgressDialog pd;
    Gson gson=new Gson();
    sapi sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHealthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pd=new ProgressDialog(Health.this);
        pd.setMessage("Loading health status");
        pd.show();
        String s=getIntent().getExtras().getString("gender");
        pd.dismiss();
        String s1[]=getIntent().getExtras().getStringArray("Metrics");

        if(s.equals("female"))
            binding.ivmenstruation.setVisibility(View.VISIBLE);


        binding.ivmenstruation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.PcosPcod.setVisibility(View.VISIBLE);
            }
        });
        Retrofit retrofit=RetrofitInstance.getInstance();
        APIService service=retrofit.create(APIService.class);
        service.getsapi("100","88","0.95").enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject object=response.body();
                if(object!=null)
                {
                    sp=gson.fromJson(object,sapi.class);
                    if(sp.getFever().toString().equals("1.0")&&sp.getHypoxia().toString().equals("0")&&sp.getStress().toString().equals("0"))
                    {
                        binding.fever.setVisibility(View.VISIBLE);
                        pd.dismiss();
                    }

                    else if(sp.getFever().toString().equals("0.0")&&sp.getHypoxia().toString().equals("1")&&sp.getStress().toString().equals("0"))
                    {
                        binding.hypoxia.setVisibility(View.VISIBLE);
                        pd.dismiss();
                    }
                    else if(sp.getFever().toString().equals("0.0")&&sp.getHypoxia().toString().equals("0")&&sp.getStress().toString().equals("1"))
                    {
                        binding.stress.setVisibility(View.VISIBLE);
                        pd.dismiss();
                    }

                }

                Log.e("response",response.toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("failed",t.getMessage());

            }
        });



    }
}