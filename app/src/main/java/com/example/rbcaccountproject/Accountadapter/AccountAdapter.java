package com.example.rbcaccountproject.Accountadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rbcaccountproject.R;
import com.rbc.rbcaccountlibrary.Account;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Account item);
    }

    private List<Account> items;
    private OnItemClickListener listener;

    public AccountAdapter(List<Account> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_account_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAccountName;
        private TextView tvAccountNumber;
        private TextView tvAccountBalance;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvAccountName = (TextView) itemView.findViewById(R.id.tvAccountName);
            tvAccountNumber = (TextView) itemView.findViewById(R.id.tvAccountNumber);
            tvAccountBalance = (TextView) itemView.findViewById(R.id.tvAccountBalance);
        }

        public void bind(final Account item, final OnItemClickListener listener) {
            tvAccountName.setText(item.getName());
            tvAccountNumber.setText(item.getNumber());
            tvAccountBalance.setText(item.getBalance() + " CAD");
            setTextColorAccountType(item.getName().toUpperCase(Locale.ROOT), tvAccountName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

        public void setTextColorAccountType(String name, TextView view) {

            switch (name) {
                case "VISA":
                    view.setTextColor(getColor(R.color.green, view));
                    break;

                case "MASTERCARD":
                    view.setTextColor(getColor(R.color.red, view));
                    break;

                case "CHEQUING":
                    view.setTextColor(getColor(R.color.blue, view));
                    break;

                case "BUSINESS LOAN":
                    view.setTextColor(getColor(R.color.yellow, view));
                    break;

                case "PERSONAL LOAN":
                    view.setTextColor(getColor(R.color.gray, view));
                    break;

                case "CREDIT LINE":
                    view.setTextColor(getColor(R.color.purple_200, view));
                    break;

                default:
                    view.setTextColor(getColor(R.color.teal_700, view));

            }
        }

        public int getColor(int color, View view) {
            return ContextCompat.getColor(view.getContext(), color);
        }
    }

    //Sorting list to make it group by similar account types
    public void setAccountListData(List<Account> accounts) {
        items = accounts;
        Collections.sort(items, (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
    }
}
