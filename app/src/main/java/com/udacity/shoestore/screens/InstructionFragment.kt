package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding


/**
 * A simple [Fragment] subclass.
 */
class InstructionFragment : Fragment() {

    private lateinit var binding: FragmentInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruction, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.gotItButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_instructionFragment_to_shoeListFragment)
        }
    }

}