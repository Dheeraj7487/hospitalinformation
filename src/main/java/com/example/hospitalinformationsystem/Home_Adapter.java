package com.example.hospitalinformationsystem;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

class Home_Adapter extends BaseAdapter {

    Context context;
    int [] image;
    String [] names;
    LayoutInflater inflater;

    public Home_Adapter(Home homePage, String[] name, int[] imnage) {

        context=homePage;
        this.image=imnage;
        this.names=name;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view1= inflater.inflate(R.layout.home_grid_list,null);

        LinearLayout linearLayout=view1.findViewById(R.id.custom_linear);
        ImageView imageView=view1.findViewById(R.id.us_img);
        TextView textView=view1.findViewById(R.id.us_tt);

        imageView.setImageResource(image[i]);
        textView.setText(names[i]);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i == 0){
                    Intent intent = new Intent(context, Profile.class);
                    context.startActivity(intent);
                }
                if(i == 1){
                    Intent intent = new Intent(context, AreaSelect.class);
                    context.startActivity(intent);
                }
                if(i == 2){
                    Intent intent = new Intent(context, Request_Result.class);
                    context.startActivity(intent);
                }
                if(i == 3){
                    Intent intent = new Intent(context, ContactUs.class);
                    context.startActivity(intent);
                }
                if(i == 4){
                    Intent intent = new Intent(context, Feedback.class);
                    context.startActivity(intent);
                }
            }
        });

        return view1;
    }
}
