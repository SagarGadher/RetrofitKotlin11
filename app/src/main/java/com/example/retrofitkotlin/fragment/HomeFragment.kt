package com.example.retrofitkotlin.fragment


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.adapert.GenericAdapter
import com.example.retrofitkotlin.adapert.GenericViewHolderFactory
import com.example.retrofitkotlin.databinding.LayoutFragmentHomeBinding
import com.example.retrofitkotlin.databinding.ListHomeItemBinding
import com.example.retrofitkotlin.viewmodel.HomeViewModel
import com.example.retrofitkotlin.viewmodel.MarsProperty

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding :LayoutFragmentHomeBinding =  DataBindingUtil.inflate(inflater,R.layout.layout_fragment_home, container, false)
        val viewModel:HomeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val  adapter= object :GenericAdapter<Any>(){
            override fun getBinding(view: View, viewType: Int): RecyclerView.ViewHolder {
                return GenericViewHolderFactory.create(ListHomeItemBinding.inflate(LayoutInflater.from(view.context)),viewType)
            }

            override fun getLayoutId(position: Int, obj: Any): Int {
                return when (obj) {
                    is MarsProperty -> R.layout.list_home_item
                    else -> R.layout.list_home_item
                }
            }

            override fun onClick(position: Int) {
                Toast.makeText(activity, "hello $position", Toast.LENGTH_LONG).show()
            }

        }
        binding.rvHome.adapter = adapter
        return binding.root
    }
}
