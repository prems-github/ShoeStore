package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.viewmodel.LoginViewModel
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        //Associating LoginViewModel
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        //authenticating user
        binding.loginButton.setOnClickListener { view ->
            Timber.d("User Name is ${binding.userNameText.text} and pwd is ${binding.pwdText.text}")
            val isLoginSuccess = loginViewModel.isUserExist(
                binding.userNameText.text.toString(),
                binding.pwdText.text.toString()
            )
            if (isLoginSuccess)
                view.findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
            else
                Toast.makeText(context, "Please enter correct credentials", Toast.LENGTH_SHORT)
                    .show()
        }

        //creates new user login
        binding.newUserLogin.setOnClickListener { view ->
            if (binding.userNameText.text.toString() == "" || binding.pwdText.text.toString() == "")
                Toast.makeText(context, "Please enter correct credentials", Toast.LENGTH_SHORT)
                    .show()
            else {

                loginViewModel.newUser(
                    binding.userNameText.text.toString(),
                    binding.pwdText.text.toString()
                )
                view.findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
            }
        }
    }

}