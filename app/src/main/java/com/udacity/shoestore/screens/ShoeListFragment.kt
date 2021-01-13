package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.viewmodel.ShoeListViewModel
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    val shoeListViewModel by lazy {
        activity?.run {
            ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Timber.i("Shoe List View model created $shoeListViewModel")

        //observing livedata  and creating views dynamically
        shoeListViewModel?.shoeList?.observe(viewLifecycleOwner, Observer {
            for (i in 0..it.size - 1) {
                val textView = TextView(requireContext())
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                textView.layoutParams = params
                textView.setText("${it[i].name}")
                textView.textSize = 26.0F
                binding.linearLayout.addView(textView)
            }
        })

        //navigates to shoe detail screen
        binding.actionButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }
    }
}