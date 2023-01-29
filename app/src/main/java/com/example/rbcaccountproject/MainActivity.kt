package com.example.rbcaccountproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.audiobooks.adapter.AccountAdapter
import com.example.rbcaccountproject.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.rbc.rbcaccountlibrary.AccountProvider


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val accounts = AccountProvider.getAccountsList()

        val adapter = AccountAdapter()
        binding.recyclerAccounts.layoutManager = LinearLayoutManager(this)
        binding.recyclerAccounts.adapter = adapter


        adapter.setAccountListData(accounts)

        adapter.onItemClick = { selectedAccount ->
            val intent = Intent(this, AccountDetailsActivity::class.java)
            val convertJsonString = Gson().toJson(selectedAccount)
            intent.putExtra("SelectedAccount", convertJsonString)
            startActivity(intent)
        }


    }
}