package com.example.rbcaccountproject.Accountadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rbcaccountproject.R;
import com.rbc.rbcaccountlibrary.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    private List<Transaction> items = new ArrayList<>();

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_transaction_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTransactionDescription;
        private final TextView tvTransactionAmount;
        private final TextView tvTransactionDate;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTransactionDescription = (TextView) itemView.findViewById(R.id.tvTransactionDescription);
            tvTransactionAmount = (TextView) itemView.findViewById(R.id.tvTransactionAmount);
            tvTransactionDate = (TextView) itemView.findViewById(R.id.tvTransactionDate);
        }

        public void bind(final Transaction item) {
            tvTransactionDescription.setText(item.getDescription());
            tvTransactionAmount.setText(item.getAmount() +" CAD");
            tvTransactionDate.setText(item.getAmount());
        }
    }

    //Sorting list based on date descending order
    public void setTransactionListData(List<Transaction> accounts) {
        items = accounts;
        Collections.sort(items, (s1, s2) -> s2.getDate().compareTo(s1.getDate()));
    }
}
