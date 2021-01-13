package com.blogspot.soyamr.arkanoidplusplus.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R


class SettingsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    var saveButton: Button?=null
    var goBackButton: Button?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        saveButton = view.findViewById(R.id.buttonSave)
        goBackButton = view.findViewById(R.id.buttonGoBack3)

        saveButton!!.setOnClickListener{
            //TODO
        }

        goBackButton!!.setOnClickListener{
            findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
        }


        return view
    }


}