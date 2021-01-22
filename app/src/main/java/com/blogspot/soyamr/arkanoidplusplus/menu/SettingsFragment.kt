package com.blogspot.soyamr.arkanoidplusplus.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SettingsFragment : Fragment() {


    private val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModelCalls()


        radioGroupPlatformControl!!.setOnCheckedChangeListener { group, checkedId ->
            // This will get the radiobutton that has changed in its check state
            touchON = checkedId == radioButtonTouch!!.id
        }

        binding.buttonSave.setOnClickListener {
            viewModel.save(touchON)
            Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
        }

        goBackButton!!.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
        }

    }


    private fun setUpViewModelCalls() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.musicON.observe(viewLifecycleOwner, { musica ->
            GlobalScope.launch(Dispatchers.Main) {
                if (!musica) {
                    (activity as MainActivity).stopMusic()
                } else {
                    (activity as MainActivity).startMusic()
                }
            }
        })

    }

    var touchON: Boolean = true

    var saveButton: Button? = null
    var goBackButton: Button? = null

    var radioGroupPlatformControl: RadioGroup? = null

    var radioButtonTouch: RadioButton? = null
    var radioButtonGyroscope: RadioButton? = null

    lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        val view = binding.root

        saveButton = view.findViewById(R.id.buttonSave)
        goBackButton = view.findViewById(R.id.buttonGoBack3)

        radioButtonTouch = view.findViewById(R.id.radioButtonSettings1)
        radioButtonGyroscope = view.findViewById(R.id.radioButtonSettings2)
        radioGroupPlatformControl = view.findViewById(R.id.radioGroupPlatformControl)


        return view
    }


}