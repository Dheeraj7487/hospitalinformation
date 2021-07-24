package com.example.hospitalinformationsystem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class logoAdapter extends ArrayAdapter<LogoItem> {

    public logoAdapter(Context context, ArrayList<LogoItem> logoList) {
        super(context, 0,logoList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.singlerow, parent, false
            );
        }

        ImageView imageViewFlag = convertView.findViewById(R.id.logo1);
        TextView textViewName = convertView.findViewById(R.id.hospname);

        LogoItem currentItem = getItem(position);

        if (currentItem != null) {
            imageViewFlag.setImageResource(currentItem.getLogo());
            textViewName.setText(currentItem.getLogoname());
        }

        return convertView;
    }
}
