package com.blogspot.soyamr.arkanoidplusplus.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Switch
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R


class SettingsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    var musicON: Boolean = true
    var soundON: Boolean = true
    var touchON: Boolean = true

    var saveButton: Button? = null
    var goBackButton: Button? = null

    var switchSound: Switch? = null
    var switchMusic: Switch? = null

    var radioButtonTouch: RadioButton? = null
    var radioButtonGyroscope: RadioButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        saveButton = view.findViewById(R.id.buttonSave)
        goBackButton = view.findViewById(R.id.buttonGoBack3)

        switchMusic = view.findViewById(R.id.switchMusic)
        switchSound = view.findViewById(R.id.switchSound)
        radioButtonTouch = view.findViewById(R.id.radioButtonSettings1)
        radioButtonGyroscope = view.findViewById(R.id.radioButtonSettings2)

        musicON = this.requireArguments().getBoolean("music", true)
        soundON = this.requireArguments().getBoolean("sound", true)
        touchON = this.requireArguments().getBoolean("touch", true)
        
        saveButton!!.setOnClickListener{
            //TODO
        }

        goBackButton!!.setOnClickListener{
            findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
        }


        return view
    }


}