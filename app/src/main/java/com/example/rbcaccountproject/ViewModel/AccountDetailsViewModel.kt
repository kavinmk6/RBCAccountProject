package com.example.rbcaccountproject.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rbcaccountproject.Model.Resource
import com.rbc.rbcaccountlibrary.AccountProvider
import com.rbc.rbcaccountlibrary.Transaction
import kotlinx.coroutines.*

class AccountDetailsViewModel() : ViewModel() {
    private val transactionLiveData = MutableLiveData<Resource<List<Transaction>>>()

    fun getTransactionList(accountNumber: String) {

        transactionLiveData.postValue(Resource.Loading(true))
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {

            val allTransactions = async {
                AccountProvider.getTransactions(accountNumber)
            }

            val creditCardTransactions = async {
                AccountProvider.getAdditionalCreditCardTransactions(accountNumber)
            }

            val allTransactionsDeferred = allTransactions.await()
            val creditCardTransactionDeferred = creditCardTransactions.await()

            val totalTransactionList = ArrayList<Transaction>()
            totalTransactionList.addAll(allTransactionsDeferred)
            totalTransactionList.addAll(creditCardTransactionDeferred)

            transactionLiveData.postValue(Resource.Loading(false))
            transactionLiveData.postValue(Resource.Success(totalTransactionList))

        }
    }

    fun getTransactionList(): MutableLiveData<Resource<List<Transaction>>> {
        return transactionLiveData
    }

    //
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        transactionLiveData.postValue(Resource.Loading(false))
        transactionLiveData.postValue(throwable.localizedMessage?.let { Resource.Error(it) })
    }

}