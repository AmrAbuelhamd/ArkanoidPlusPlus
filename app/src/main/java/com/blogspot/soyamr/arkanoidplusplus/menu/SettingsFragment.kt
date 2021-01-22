package com.blogspot.soyamr.arkanoidplusplus.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.Repository


class SettingsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var repository: Repository

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
        saveButton = view.findViewById(R.id.buttonSave)
        goBackButton = view.findViewById(R.id.buttonGoBack3)

        switchMusic = view.findViewById(R.id.switchMusic)
        switchSound = view.findViewById(R.id.switchSound)
        radioButtonTouch = view.findViewById(R.id.radioButtonSettings1)
        radioButtonGyroscope = view.findViewById(R.id.radioButtonSettings2)
        radioGroupPlatformControl = view.findViewById(R.id.radioGroupPlatformControl)

        musicON = repository.SettingsGetMusic()
        soundON = repository.SettingsGetSound()
        touchON = repository.SettingsGetTouch()

        switchMusic!!.isChecked = musicON
        switchSound!!.isChecked = soundON
        if (touchON)
        {
            radioButtonTouch!!.isChecked = true
        }
        else
        {
            radioButtonGyroscope!!.isChecked = true
        }


        switchMusic!!.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            musicON = b
        }

        switchSound!!.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            soundON = b
        }

        radioGroupPlatformControl!!.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            // This will get the radiobutton that has changed in its check state
            touchON = checkedId == radioButtonTouch!!.id

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
            repository.SettingsSetMusic(musicON)
            repository.SettingsSetSound(soundON)
            repository.SettingsSetTouch(touchON)
            Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
        }

        goBackButton!!.setOnClickListener{
            findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
        }


        return view
    }


}