package com.example.retrofitkotlin.adapert

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.retrofitkotlin.databinding.ListHomeItemBinding
import com.example.retrofitkotlin.viewmodel.MarsProperty

class HomeAdapter: ListAdapter<MarsProperty,HomeAdapter.MarsViewHolder>(DiffCallback) {

    class MarsViewHolder(private val binding:ListHomeItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(property:MarsProperty){
            binding.property = property
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(p0: MarsProperty, p1: MarsProperty): Boolean {
            return p0.id == p1.id
        }

        override fun areContentsTheSame(p0: MarsProperty, p1: MarsProperty): Boolean {
            return p0==p1
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.MarsViewHolder {
        return MarsViewHolder(ListHomeItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HomeAdapter.MarsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}