package com.blogspot.soyamr.arkanoidplusplus.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
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

    var radioGroupPlatformControl: RadioGroup? = null

    var radioButtonTouch: RadioButton? = null
    var radioButtonGyroscope: RadioButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val intent = Intent(requireContext(), MainActivity::class.java)
        saveButton = view.findViewById(R.id.buttonSave)
        goBackButton = view.findViewById(R.id.buttonGoBack3)

        switchMusic = view.findViewById(R.id.switchMusic)
        switchSound = view.findViewById(R.id.switchSound)
        radioButtonTouch = view.findViewById(R.id.radioButtonSettings1)
        radioButtonGyroscope = view.findViewById(R.id.radioButtonSettings2)

        intent.putExtra("music", true)
        musicON = requireArguments().getBoolean("music", true)
        soundON = requireArguments().getBoolean("sound", true)
        touchON = requireArguments().getBoolean("touch", true)

        switchMusic!!.isChecked = musicON
        switchSound!!.isChecked = soundON
        if (touchON){
            radioButtonTouch!!.isChecked = true
        }
        else
        {
            radioButtonGyroscope!!.isChecked = true
        }

        switchMusic!!.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if (b)
            {
                intent.putExtra("music", true)
            }
            else
            {
                intent.putExtra("music", false)
            }
        }

        switchSound!!.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if (b)
            {
                intent.putExtra("sound", true)
            }
            else
            {
                intent.putExtra("sound", false)
            }
        }

        radioGroupPlatformControl!!.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            // This will get the radiobutton that has changed in its check state
            if (checkedId == radioButtonTouch!!.id)
            {
                intent.putExtra("touch", true)
            }
            else
            {
                intent.putExtra("touch", false)
            }

/*            val checkedRadioButton = group.findViewById<View>(checkedId) as RadioButton
            // This puts the value (true/false) into the variable
            val isChecked = checkedRadioButton.isChecked
            // If the radiobutton that has changed in check state is now checked...
            if (isChecked) {
                // Changes the textview's text to "Checked: example radiobutton text"
                tv.setText("Checked:" + checkedRadioButton.text)
            }*/
        })





        saveButton!!.setOnClickListener{
            //TODO
        }

        goBackButton!!.setOnClickListener{
            findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
        }


        return view
    }


}