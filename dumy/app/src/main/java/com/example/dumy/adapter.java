package com.example.dumy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class adapter extends ArrayAdapter {

    Context context;
    List<list> list;
    int resource;
    public adapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context=context;
        this.list=objects;
        this.resource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        list students=list.get(position);

        View v= LayoutInflater.from(context).inflate(resource,null);

        ImageView imageView=v.findViewById(R.id.ivaccountlist1);
        TextView tvitem=v.findViewById(R.id.tvitem);

        imageView.setImageResource(students.img);

        tvitem.setText(students.name);


        return v;
    }
}
