package com.starcapital.collectapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.starcapital.collectapp.R;
import com.starcapital.collectapp.activities.AccountDetailsActivity;
import com.starcapital.collectapp.models.Account;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AccountsRecyclerAdapter extends RecyclerView.Adapter<AccountsRecyclerAdapter.ViewHolder> {

    private ArrayList<Account> accounts;
    Context context;

    public AccountsRecyclerAdapter(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.account_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = viewGroup.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        Account account = accounts.get(i);
        viewHolder.textViewAccountNumber.setText(account.getAccountNumber());
        viewHolder.textViewAccountName.setText(account.getAccountName());
        viewHolder.textViewPhoneNumber.setText(account.getContact());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AccountDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public void add(int position, Account account) {
        accounts.add(position, account);
        notifyItemInserted(position);
    }

    public void addAll(ArrayList<Account> newAccounts) {
        accounts.addAll(newAccounts);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        accounts.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewAccountNumber;
        TextView textViewAccountName;
        TextView textViewPhoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewAccountName = itemView.findViewById(R.id.accounts_list_account_name);
            textViewAccountNumber = itemView.findViewById(R.id.accounts_list_account_number);
            textViewPhoneNumber = itemView.findViewById(R.id.accounts_list_phone_number);

        }
    }
}
