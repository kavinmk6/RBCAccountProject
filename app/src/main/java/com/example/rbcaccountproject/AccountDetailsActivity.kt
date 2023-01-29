package com.example.rbcaccountproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.rbcaccountproject.Model.Resource
import com.example.rbcaccountproject.ViewModel.AccountDetailsViewModel
import com.example.rbcaccountproject.databinding.ActivityAccountDetailsBinding
import com.google.gson.Gson
import com.rbc.rbcaccountlibrary.Account

class AccountDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: AccountDetailsViewModel
    private lateinit var binding: ActivityAccountDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel =
//            ViewModelProvider(this@AccountDetailsActivity, AccountDetailsViewModelFactory(repository)).get(
//                AccountDetailsViewModel::class.java
//            )

        viewModel =
            ViewModelProvider(this@AccountDetailsActivity).get(AccountDetailsViewModel::class.java)

        val intent = getIntent()
        val account = Gson().fromJson(intent.getStringExtra("SelectedAccount"), Account::class.java)

        viewModel.getTransactionList(account.number)

        viewModel.getTransactionList().observe(this) {

            when (it) {
                is Resource.Success -> {
                    binding.apply {
                        progressBar.visibility = View.GONE
                        tvApiError.visibility = View.GONE
                        recyclerTransaction.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    binding.apply {
                        progressBar.visibility = View.GONE
                        recyclerTransaction.visibility = View.GONE
                        tvApiError.text = it.message
                        tvApiError.visibility = View.VISIBLE
                    }
                }
                is Resource.Loading -> {
                    binding.apply {
                        tvApiError.visibility = View.GONE
                        recyclerTransaction.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }

        }


    }
}