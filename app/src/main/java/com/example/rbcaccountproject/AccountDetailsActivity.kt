package com.example.rbcaccountproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rbcaccountproject.Accountadapter.TransactionAdapter
import com.example.rbcaccountproject.Model.Resource
import com.example.rbcaccountproject.ViewModel.AccountDetailsViewModel
import com.example.rbcaccountproject.databinding.ActivityAccountDetailsBinding
import com.google.gson.Gson
import com.rbc.rbcaccountlibrary.Account

class AccountDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: AccountDetailsViewModel
    private lateinit var binding: ActivityAccountDetailsBinding
    private lateinit var adapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Account Transactions"

        viewModel =
            ViewModelProvider(this@AccountDetailsActivity).get(AccountDetailsViewModel::class.java)

        val intent = getIntent()
        val account = Gson().fromJson(intent.getStringExtra("SelectedAccount"), Account::class.java)
        adapter = TransactionAdapter()

        binding.accountLayout.apply {
            tvAccountName.text = account.name
            tvAccountNumber.text = account.number
            tvAccountBalance.text = account.balance + " CAD"
        }

        binding.recyclerTransaction.layoutManager = LinearLayoutManager(this)
        binding.recyclerTransaction.adapter = adapter
        binding.recyclerTransaction.setHasFixedSize(true)
        binding.recyclerTransaction.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.getTransactionList(account.number)
        binding.btRetryApi.setOnClickListener {
            viewModel.getTransactionList(account.number)
        }

        viewModel.getTransactionList().observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.apply {
                        progressBar.visibility = View.GONE
                        tvApiError.visibility = View.GONE
                        recyclerTransaction.visibility = View.VISIBLE
                        btRetryApi.visibility = View.GONE
                        adapter.setTransactionListData(it.data)
                    }
                }
                is Resource.Error -> {
                    binding.apply {
                        progressBar.visibility = View.GONE
                        recyclerTransaction.visibility = View.GONE
                        tvApiError.text = it.message
                        tvApiError.visibility = View.VISIBLE
                        btRetryApi.visibility = View.VISIBLE
                    }
                }
                is Resource.Loading -> {
                    binding.apply {
                        tvApiError.visibility = View.GONE
                        recyclerTransaction.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                        btRetryApi.visibility = View.GONE

                    }
                }
            }

        }

    }


}