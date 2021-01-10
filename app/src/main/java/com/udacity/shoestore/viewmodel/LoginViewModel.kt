package com.udacity.shoestore.viewmodel

import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Login
import timber.log.Timber

class LoginViewModel : ViewModel() {

    init {
        Timber.i("Login view model created")
    }

    private val loginDetails = mutableListOf(
        Login("a", "a"),
        Login("ramesh@gmail.com", "jackie"),
        Login("suresh@gmail.com", "sparrow"),
        Login("dinesh@gmail.com", "express"),
        Login("ganesh@gmail.com", "briyani")
    )

    fun isUserExist(uName: String, pwd: String): Boolean {
        if (loginDetails.contains(Login(uName, pwd)))
            return true
        else
            return false
    }

    fun newUser(uName: String, pwd: String) {
        loginDetails.add(Login(uName, pwd))
        Timber.i("New list size is ${loginDetails.size}")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("Login view model destroyed")
    }
}