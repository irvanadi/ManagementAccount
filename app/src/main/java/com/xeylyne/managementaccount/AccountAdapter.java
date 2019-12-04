package com.xeylyne.managementaccount;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    Context context;
    ArrayList<Account> results;

    public AccountAdapter(Context context, ArrayList<Account> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Account account = results.get(position);
        holder.txtUsername.setText(account.getUsername());
        holder.txtPhoneNumber.setText(account.getPhonenumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateAndDeleteActivity.class);
                intent.putExtra("position", position);
                Log.d("onUpdate", String.valueOf(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtUsername, txtPhoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtPhoneNumber = itemView.findViewById(R.id.txtPhoneNumber);
        }
    }
}
