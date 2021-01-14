package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R


class NoInternetFragment : Fragment() {

    var letsGo2Button: Button?=null
    var tryAgainButton: Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val args = arguments?.let { NoInternetFragmentArgs.fromBundle(it) }
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_no_internet, container, false)

        letsGo2Button = view.findViewById(R.id.buttonLetsGo2)
        letsGo2Button!!.setOnClickListener{
            findNavController().navigate(R.id.action_noInternetFragment_to_storyFragment)
        }

        tryAgainButton = view.findViewById(R.id.buttonTryAgain)
        tryAgainButton!!.setOnClickListener{
            findNavController().navigate(R.id.action_noInternetFragment_to_loginFragment)
        }


        return view
    }

    companion object {

    }
}