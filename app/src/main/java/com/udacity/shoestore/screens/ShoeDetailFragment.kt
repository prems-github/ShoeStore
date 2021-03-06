package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodel.ShoeListViewModel
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

    //activity level view model
    val shoeListViewModel by lazy {
        activity?.run {
            ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.mShoe=shoeListViewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Timber.i("Shoe List View model $shoeListViewModel")
        //returns to shoelist screen
        binding.cancelButton.setOnClickListener { view->
            view.findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }

        //Adds a new shoe to the list and returns to shoe list screen
        binding.addButton.setOnClickListener { view->
            shoeListViewModel?.addToList()
            view.findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }
    }

}