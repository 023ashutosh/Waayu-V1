package com.example.dumy;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


public class slideImage extends PagerAdapter {

        Context ctx;

        public slideImage(Context ctx) {
            this.ctx = ctx;
        }




        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.transition_slide, container, false);

            ImageView slide_1 = view.findViewById(R.id.slide1);


            ImageView ind1 = view.findViewById(R.id.ivInd1);
            ImageView ind2 = view.findViewById(R.id.ivInd2);
            ImageView ind3 = view.findViewById(R.id.ivInd3);


            Button btn= view.findViewById(R.id.btnStart);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("Clicked","yes");
                    ctx.startActivity(new Intent(ctx.getApplicationContext(),Language.class));



                }
            });

            switch (position) {
                case 0:
                    slide_1.setImageResource(R.drawable.one);
                    ind1.setImageResource(R.drawable.selected);
                    ind2.setImageResource(R.drawable.unselected);
                    ind3.setImageResource(R.drawable.unselected);
                    ind1.setVisibility(View.VISIBLE);
                    ind2.setVisibility(View.VISIBLE);
                    ind3.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.GONE);
                    break;
                case 1:
                    slide_1.setImageResource(R.drawable.two);
                    ind1.setImageResource(R.drawable.unselected);
                    ind2.setImageResource(R.drawable.selected);
                    ind3.setImageResource(R.drawable.unselected);
                    ind1.setVisibility(View.VISIBLE);
                    ind2.setVisibility(View.VISIBLE);
                    ind3.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.GONE);
                    break;
                case 2:
                    slide_1.setImageResource(R.drawable.three);
//                    ind1.setImageResource(R.drawable.unselected);
//                    ind2.setImageResource(R.drawable.unselected);
//                    ind3.setImageResource(R.drawable.selected);
                    ind1.setVisibility(View.GONE);
                    ind2.setVisibility(View.GONE);
                    ind3.setVisibility(View.GONE);
                    btn.setVisibility(View.VISIBLE);


                    break;
            }

            container.addView(view);
            return view;



        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }




