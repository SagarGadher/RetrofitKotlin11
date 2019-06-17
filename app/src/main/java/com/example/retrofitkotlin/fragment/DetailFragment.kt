package com.example.retrofitkotlin.fragment


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.databinding.LayoutFragmentDetailBindingImpl
import com.example.retrofitkotlin.viewmodel.DetailViewModel
import com.example.retrofitkotlin.viewmodel.DetailViewModelFactory

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding:LayoutFragmentDetailBindingImpl =DataBindingUtil.inflate(inflater,R.layout.layout_fragment_detail, container, false)
        binding.setLifecycleOwner(this)
        val  args = DetailFragmentArgs.fromBundle(arguments!!)
        val application = requireNotNull(this.activity).application
        val viewModelFactory = DetailViewModelFactory(args.selectedProperty,application)
        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(DetailViewModel::class.java)
        binding.viewModel = viewModel
        return  binding.root
    }


}
