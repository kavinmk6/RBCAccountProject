package com.example.rbcaccountproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rbcaccountproject.Accountadapter.AccountAdapter;
import com.example.rbcaccountproject.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.rbc.rbcaccountlibrary.Account;
import com.rbc.rbcaccountlibrary.AccountProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AccountAdapter.OnItemClickListener {

    ActivityMainBinding binding;
    AccountAdapter adapter;
    private List<Account> accountList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        adapter = new AccountAdapter(accountList, this);

        accountList = AccountProvider.INSTANCE.getAccountsList();

        binding.recyclerAccounts.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerAccounts.setAdapter(adapter);
        adapter.setAccountListData(accountList);
    }

    @Override
    public void onItemClick(Account selectedAccount) {
        Intent intent = new Intent(this, AccountDetailsActivity.class);
        String convertAccountToString = new Gson().toJson(selectedAccount);
        intent.putExtra("SelectedAccount", convertAccountToString);
        startActivity(intent);
    }
}