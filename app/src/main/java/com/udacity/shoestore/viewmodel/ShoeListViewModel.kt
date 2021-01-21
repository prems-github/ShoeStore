package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoeList

     lateinit var name:String
     lateinit var size:String
     lateinit var company:String
     lateinit var description:String

    //shoe inventory list
    init {
        emptyFields()
        _shoeList.postValue(
            arrayListOf(
                Shoe(
                    "The Walker",
                    "10.00",
                    "Nike",
                    "Walking shoe - a light comfortable shoe designed for vigorous walking."
                ),
                Shoe(
                    "Jogger",
                    "9.00",
                    "Reebok",
                    "Ultra light weight and super cushioning you can jog for long"
                ),
                Shoe(
                    "Racer",
                    "10.00",
                    "Nike",
                    "Footwear shaped to fit the foot (below the ankle) with a flexible upper of leather or plastic and a sole and heel of heavier material."
                ),
                Shoe(
                    "Cool Step",
                    "10.00",
                    "Nike",
                    "This shoe is specially designed to keep your foot cool even in sunny days"
                )
            )
        )
    }

    //adding new shoe to the list
    fun addToList() {
        shoeList.value?.add(Shoe(name,size,company,description))
        _shoeList.postValue(shoeList.value)
        emptyFields()

    }

    fun emptyFields(){
        name=""
        size=""
        company=""
        description=""
    }
}