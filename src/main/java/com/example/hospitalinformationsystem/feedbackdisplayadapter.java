package com.example.hospitalinformationsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class feedbackdisplayadapter extends RecyclerView.Adapter<feedbackdisplayadapter.ViewHolder> {

    Context context;
    private feedbackdisplaymodel[] display_model;


    @NonNull
    @Override
    public feedbackdisplayadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_feeddback, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull feedbackdisplayadapter.ViewHolder holder, int position) {
        holder.name.setText(display_model[position].getName());
        holder.email.setText(display_model[position].getEmail());
        holder.mobile.setText(display_model[position].getMobile());
        holder.writefeedback.setText(display_model[position].getWritefeedback());

    }

    @Override
    public int getItemCount() {
        return display_model.length;
    }

    public void setItems(feedbackdisplaymodel[] display_model) {
        this.display_model = display_model;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView email;
        private final TextView mobile;
        private final TextView writefeedback;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_display);
            email = itemView.findViewById(R.id.email_display);
            mobile = itemView.findViewById(R.id.mobile_display);
            writefeedback = itemView.findViewById(R.id.write_display);

        }
    }
}
