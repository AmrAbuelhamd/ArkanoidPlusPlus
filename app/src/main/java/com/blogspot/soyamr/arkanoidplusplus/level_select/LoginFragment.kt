package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R


class LoginFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var letsGoButton: Button?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        letsGoButton = view.findViewById(R.id.buttonLetsGo)
        letsGoButton!!.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_noInternetFragment)
        }

        return view
    }

}