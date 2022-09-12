package com.yogesh.retrofitkotlin.listAdapter.model.adaptter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yogesh.retrofitkotlin.R
import com.yogesh.retrofitkotlin.databinding.QuotesListItemForListAdapterBinding
import com.yogesh.retrofitkotlin.listAdapter.model.Result

class QuotesListAdapter(val context: Context) :
    ListAdapter<Result, QuotesListAdapter.QuotesListViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesListViewHolder {
        val binding : QuotesListItemForListAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.quotes_list_item_for_list_adapter, parent, false)
        return QuotesListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuotesListViewHolder, position: Int) {
        holder.binding.quoteResult = getItem(position)
    }

    class QuotesListViewHolder(val binding: QuotesListItemForListAdapterBinding) : RecyclerView.ViewHolder(binding.root) {}


    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }
}
