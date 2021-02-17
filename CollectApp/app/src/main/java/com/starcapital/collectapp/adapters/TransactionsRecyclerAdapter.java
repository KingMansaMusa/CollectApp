package com.starcapital.collectapp.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.starcapital.collectapp.R;
import com.starcapital.collectapp.models.Transaction;
import java.util.ArrayList;

public class TransactionsRecyclerAdapter extends RecyclerView.Adapter<TransactionsRecyclerAdapter.ViewHolder> {

    ArrayList<Transaction> transactions;

    public TransactionsRecyclerAdapter(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.transaction_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Transaction transaction = transactions.get(i);
        viewHolder.textViewAccountName.setText(transaction.getAccountName());
        viewHolder.textViewAccountNumber.setText(transaction.getAccountNumber());
        viewHolder.textViewAmount.setText(String.valueOf(transaction.getAmount()));

    }

    public void add(int position, Transaction transaction) {
        transactions.add(position, transaction);
        notifyItemInserted(position);
    }

    public void addAll(ArrayList<Transaction> newTransactions) {
        transactions.addAll(newTransactions);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        transactions.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewAccountNumber;
        TextView textViewAccountName;
        TextView textViewAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAccountName = itemView.findViewById(R.id.transition_list_account_name);
            textViewAccountNumber = itemView.findViewById(R.id.transition_list_account_number);
            textViewAmount = itemView.findViewById(R.id.transition_list_amount);
        }
    }
}
