package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R


class PlayerIsDeadFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    var gaBackButton: Button?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player_is_dead, container, false)

        gaBackButton = view.findViewById(R.id.buttonGoBack)
        gaBackButton!!.setOnClickListener{
            findNavController().navigate(R.id.action_playerIsDeadFragment_to_loginFragment)
        }

        return view
    }

    companion object {
    }
}