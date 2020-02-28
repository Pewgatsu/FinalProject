package com.example.mobileprogfinalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private LayoutInflater inflater;
    private List<Passwords> passwordsList;


    public Adapter(Context context, List<Passwords> passwordsList){
        this.inflater = LayoutInflater.from(context);
        this.passwordsList = passwordsList;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title = passwordsList.get(position).getTitle();
        String account = passwordsList.get(position).getAccount();

        holder.detailsTitle.setText(title);
        holder.detailsAccount.setText(account);

    }

    @Override
    public int getItemCount() {
        return passwordsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView detailsTitle, detailsAccount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            detailsTitle = itemView.findViewById(R.id.detailsTitle);
            detailsAccount = itemView.findViewById(R.id.detailsAccount);
        }
    }
}
