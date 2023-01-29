package com.example.audiobooks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rbcaccountproject.R
import com.rbc.rbcaccountlibrary.Account

class AccountAdapter : RecyclerView.Adapter<AccountAdapter.MyViewHolder>() {

    var accountList = emptyList<Account>()
    var onItemClick: ((Account) -> Unit)? = null

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvAccountName: TextView = view.findViewById(R.id.tvAccountName)
        var tvAccountNumber: TextView = view.findViewById(R.id.tvAccountNumber)
        var tvAccountBalance: TextView = view.findViewById(R.id.tvAccountBalance)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(accountList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_account_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = accountList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvAccountName.text = accountList[position].name
        holder.tvAccountNumber.text = accountList[position].number
        holder.tvAccountBalance.text = accountList[position].balance
    }

    fun setAccountListData(accounts: List<Account>) {
        accountList = accounts
    }
}

