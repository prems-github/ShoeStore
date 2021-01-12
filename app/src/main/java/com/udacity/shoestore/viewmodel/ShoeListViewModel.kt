package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {

    private val _shoeList=MutableLiveData<List<Shoe>>()
    val shoeList:LiveData<List<Shoe>>
        get() = _shoeList

    init {
        _shoeList.postValue(mutableListOf(Shoe("The Walker", 10.00, "Nike", R.string.nike),
            Shoe("Jogger", 9.00, "Reebok", R.string.reebok),
            Shoe("Racer", 10.00, "Nike", R.string.reebok),
            Shoe("Cool Step", 10.00, "Nike", R.string.nike)))
    }


}