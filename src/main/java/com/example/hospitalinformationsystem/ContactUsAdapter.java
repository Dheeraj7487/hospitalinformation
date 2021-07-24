package com.example.hospitalinformationsystem;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ContactUsAdapter extends BaseAdapter {

    Context context;
    int [] image;
    String [] names;
    LayoutInflater inflater;

    public ContactUsAdapter(ContactUs contactUs, int[] imnage, String[] name) {

        context=contactUs;
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
        View view1= inflater.inflate(R.layout.contact_us_list,null);

        LinearLayout linearLayout=view1.findViewById(R.id.contact_linear);
        ImageView imageView=view1.findViewById(R.id.contact_image);
        TextView textView=view1.findViewById(R.id.contact_textview);

        imageView.setImageResource(image[i]);
        textView.setText(names[i]);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i == 0){
                    Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1234567890°"));
                    context.startActivity(intent);
                }
                else if(i == 1){
                    Intent intent=new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/hospitalinformation/"));
                    context.startActivity(intent);
                }else if(i == 2){
                    Intent intent=new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/hospitalinformation/"));
                    context.startActivity(intent);
                }else if(i == 3){
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto","hospitalinformation12@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                    context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
                }
            }
        });

        return view1;
    }
}
