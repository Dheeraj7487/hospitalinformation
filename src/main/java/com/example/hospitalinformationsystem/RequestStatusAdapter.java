package com.example.hospitalinformationsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.dmoral.toasty.Toasty;


public class RequestStatusAdapter extends RecyclerView.Adapter<RequestStatusAdapter.ViewHolder> {
    private Request[] req;
    static Context context;
    RequestRes mydb;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminreq, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(req[position].getDate());
        holder.pname.setText(req[position].getPname());
        holder.req.setText(req[position].getReqtype());
        holder.status.setText(req[position].getStatus());
        holder.hname.setText(req[position].getHname());
        holder.accept.setOnClickListener(view -> {
            mydb=new RequestRes(context);
            boolean result=mydb.acceptstat(req[position]);


            if(result)
            {
                Toasty.success(context,"Accepted",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,admin_home.class);
                context.startActivity(intent);
            }
            else
            {Toasty.error(context,"failed",Toast.LENGTH_SHORT).show();}
        });

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb=new RequestRes(context);
                boolean result=mydb.rejectstat(req[position]);

                if(result)
                {Toasty.success(context,"Rejected",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context,admin_home.class);
                    context.startActivity(intent);
                }
                else
                {Toasty.error(context,"failed",Toast.LENGTH_SHORT).show();}

            }
        });
    }

    @Override
    public int getItemCount() {
        return req.length;
    }

    public void setItems(Request[] req) {
        this.req = req;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView date;
        private final TextView pname;
        private final TextView req;
        private final TextView status;
        private final Button accept;
        private final Button reject;
        private final TextView hname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.dattim);
            pname = itemView.findViewById(R.id.patname);
            req = itemView.findViewById(R.id.reqtype);
            status = itemView.findViewById(R.id.rstatus);
            accept=itemView.findViewById(R.id.accept);
            reject=itemView.findViewById(R.id.reject);
            hname=itemView.findViewById(R.id.hospname2);
        }
    }
}
