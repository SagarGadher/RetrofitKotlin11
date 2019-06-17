package com.example.retrofitkotlin.adapert

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.databinding.ListHomeItemBinding
import com.example.retrofitkotlin.viewmodel.MarsProperty

object GenericViewHolderFactory {
    fun create(binding: ViewDataBinding, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            //R.layout.list_basic_item -> ViewHolder(view)
            R.layout.list_home_item -> UserViewHolder(binding)
            else -> UserViewHolder(binding)
        }
    }

    //without data binding generic view Holder
    /*class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericAdapter.Binder<Shlok> {
        var tvListItem: TextView
        var tvListItemCount: TextView

        init {
            tvListItemCount = itemView.findViewById(R.id.tvListItemCount)
            tvListItem = itemView.findViewById(R.id.tvListItem)
        }

        override fun bind(data: Shlok) {
            tvListItem.text = data.shlok
            tvListItemCount.text = data.index.toString()
        }
    }*/

    // with data binding View Holder
    class UserViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root), GenericAdapter.Binder<MarsProperty> {
        override fun bind(data: MarsProperty) {
            binding as ListHomeItemBinding
            binding.property = data
            binding.executePendingBindings()
        }
    }
}