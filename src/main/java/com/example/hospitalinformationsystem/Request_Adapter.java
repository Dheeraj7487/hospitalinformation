package com.example.hospitalinformationsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.dmoral.toasty.Toasty;


public class Request_Adapter extends RecyclerView.Adapter<Request_Adapter.ViewHolder> {
    private Request[] req;
    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_status, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(req[position].getDate());
        holder.pname.setText(req[position].getPname());
        holder.req.setText(req[position].getReqtype());
        holder.status.setText(req[position].getStatus());
        holder.hname.setText(req[position].getHname());
        holder.cancel.setOnClickListener(v -> {
            RequestRes mydb=new RequestRes(context);
            if(mydb.cancelreq(req[position])){
                Toasty.success(context, "Request Cancelled", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,Home_Adapter.class);
                context.startActivity(intent);
            }
            else
            {
                Toasty.error(context,"Request Cancelling Failed",Toast.LENGTH_SHORT).show();
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
        private final ImageView cancel;
        private final TextView hname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.dattim);
            pname = itemView.findViewById(R.id.patname);
            req = itemView.findViewById(R.id.reqtype);
            status = itemView.findViewById(R.id.rstatus);
            cancel=itemView.findViewById(R.id.cancelreq);
            hname=itemView.findViewById(R.id.hospname2);
        }
    }
}
