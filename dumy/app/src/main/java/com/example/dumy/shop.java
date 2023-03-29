package com.example.dumy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.dumy.databinding.ActivityShopBinding;

public class shop extends AppCompatActivity {
    ActivityShopBinding binding;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityShopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pd=new ProgressDialog(shop.this);

pd.setMessage("Loading stocks");
pd.show();



        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop1.png?alt=media&token=d35d83b6-0074-4ab8-a8cc-537c96eaf24b").into(binding.iv1);
        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop2.png?alt=media&token=7fa3dc49-79f1-4a93-9809-8c21c2ecf7ca").into(binding.iv2);
        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop3.png?alt=media&token=47b08239-a32a-470c-814c-5a64a01df6a7").into(binding.iv3);
        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop4.png?alt=media&token=fcacae49-f465-45ca-956b-a5f76570ac11").into(binding.iv4);
        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop5.png?alt=media&token=cb5c5491-7074-4a6c-82a4-4f9c6c6bb016").into(binding.iv5);

        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop6.png?alt=media&token=90cc7f9e-c160-4e23-b565-dffaf9b8f4b2").into(binding.iv6);

        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop7.png?alt=media&token=24a8b1e4-f41c-4e2f-849a-2b98d595c8c5").into(binding.iv7);

        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop8.png?alt=media&token=3e39806d-5a00-42df-a5d7-f01ea705af6a").into(binding.iv8);


        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop9.png?alt=media&token=1e1f9269-3efb-4ec3-9b47-9a78dd61daf2").into(binding.iv9);

        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop10.png?alt=media&token=2f120943-78c8-4ff6-818f-8a16bf713c9d").into(binding.iv10);

        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop11.png?alt=media&token=af9451d0-c5e4-49f3-a021-3be62a582c1a").into(binding.iv11);

        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop12.png?alt=media&token=18fa6011-52b0-4d04-8923-f15b30bfe841").into(binding.iv12);

        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop13.png?alt=media&token=84bb21c4-e8a3-406f-b7ac-530082fd0cb3").into(binding.iv13);

        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop14.png?alt=media&token=0ac679b6-c1eb-4c67-9750-9482f3f0ea82").into(binding.iv14);

        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop15.png?alt=media&token=89e8dfa8-021d-426a-b4f6-f194bf243c08").into(binding.iv15);

        Glide.with(shop.this).load("https://firebasestorage.googleapis.com/v0/b/dumy-4ee0d.appspot.com/o/shop2.png?alt=media&token=7fa3dc49-79f1-4a93-9809-8c21c2ecf7ca").into(binding.iv16);

    pd.dismiss();

    }
}