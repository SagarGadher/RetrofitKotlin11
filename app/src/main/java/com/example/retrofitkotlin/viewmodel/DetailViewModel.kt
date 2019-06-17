package com.example.retrofitkotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.example.retrofitkotlin.R

class DetailViewModel(marsProperty: MarsProperty,application: Application) : AndroidViewModel(application) {
    private val _selectedProperty = MutableLiveData<MarsProperty>()

    val selectedProperty : LiveData<MarsProperty>
    get() = _selectedProperty

    init {
        _selectedProperty.value = marsProperty
    }

    val displayPropertyPrice = Transformations.map(selectedProperty){
        application.applicationContext.getString(when(it.isRental){
            true->  R.string.format_price_rent
            false -> R.string.format_price_sale
        },it.price)
    }

    val displayPropertyType = Transformations.map(selectedProperty){
        application.applicationContext.getString(R.string.display_type,application.applicationContext.getString(when(it.isRental){
            true->  R.string.type_rent
            false -> R.string.type_sale }))
    }
}