package com.example.retrofitkotlin.adapert

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

abstract class GenericAdapter<T> : ListAdapter<T, RecyclerView.ViewHolder>(object :
    DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem
}) {
    //RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /*companion object DiffCallback : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(p0: T, p1: T): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun areContentsTheSame(p0: T, p1: T): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }*/

    //complete
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getBinding(parent, viewType)

    //complete
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(getItem(position))
        holder.itemView.setOnClickListener { onClick(position) }
    }


    override fun getItemViewType(position: Int): Int = getLayoutId(position, getItem(position))


    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getBinding(view: View, viewType: Int): RecyclerView.ViewHolder

    abstract fun onClick(position: Int)

    internal interface Binder<T> {
        fun bind(data: T)
    }
}