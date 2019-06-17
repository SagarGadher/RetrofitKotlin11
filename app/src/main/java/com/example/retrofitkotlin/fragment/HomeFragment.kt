package com.example.retrofitkotlin.fragment


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.retrofitkotlin.MyApiFilter
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.adapert.GenericAdapter
import com.example.retrofitkotlin.adapert.GenericViewHolderFactory
import com.example.retrofitkotlin.databinding.LayoutFragmentHomeBinding
import com.example.retrofitkotlin.databinding.ListHomeItemBinding
import com.example.retrofitkotlin.viewmodel.HomeViewModel
import com.example.retrofitkotlin.viewmodel.MarsProperty

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: LayoutFragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.layout_fragment_home, container, false)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = object : GenericAdapter<Any>() {
            override fun getBinding(view: View, viewType: Int): RecyclerView.ViewHolder {
                return GenericViewHolderFactory.create(
                    ListHomeItemBinding.inflate(
                        LayoutInflater.from(
                            view.context
                        )
                    ), viewType
                )
            }

            override fun getLayoutId(position: Int, obj: Any): Int {
                return when (obj) {
                    is MarsProperty -> R.layout.list_home_item
                    else -> R.layout.list_home_item
                }
            }

            override fun onClick(itemClicked: Any) {
                itemClicked as MarsProperty
                Toast.makeText(activity, "hello ${itemClicked.id}", Toast.LENGTH_LONG).show()
                this@HomeFragment.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(itemClicked))
            }

        }
        binding.rvHome.adapter = adapter
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        viewModel.updateFilter(
            when (item!!.itemId) {
                R.id.action_rent -> MyApiFilter.SHOW_RENT
                R.id.action_buy -> MyApiFilter.SHOW_BUY
                else -> MyApiFilter.SHOW_ALL
            }
        )
        return true
    }
}
