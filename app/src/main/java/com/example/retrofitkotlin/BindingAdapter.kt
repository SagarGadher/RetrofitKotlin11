package com.example.retrofitkotlin

import android.databinding.BindingAdapter
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.retrofitkotlin.viewmodel.MarsProperty
import com.example.retrofitkotlin.viewmodel.MarshApiStatus

@BindingAdapter("listData")
fun bindRecyclerView(rv:RecyclerView, data :List<MarsProperty>?){
    val adapter = rv.adapter as HomeAdapter
    adapter.submitList(data)
}

@BindingAdapter("ImageUrl")
fun ImageView.setImageUrl(imgUrl: String) {
    imgUrl.let {
        val imgUri = Uri.parse(it).buildUpon().scheme("https").build()
        Glide.with(this.context)
            .load(imgUri)
            .apply(RequestOptions.placeholderOf(R.drawable.loading_animation).error(R.drawable.ic_broken_image))
            .into(this)
    }
}

@BindingAdapter("MarsApiStatus")
fun bindStatus(ivStatus:ImageView,sataus:MarshApiStatus?){
    when(sataus){
        MarshApiStatus.DONE->{
            ivStatus.visibility = View.GONE
        }
        MarshApiStatus.LOADING->{
            ivStatus.visibility = View.VISIBLE
            ivStatus.setImageResource(R.drawable.loading_animation)
        }
        MarshApiStatus.ERROR->{
            ivStatus.visibility=View.VISIBLE
            ivStatus.setImageResource(R.drawable.ic_connection_error)
        }
    }
}