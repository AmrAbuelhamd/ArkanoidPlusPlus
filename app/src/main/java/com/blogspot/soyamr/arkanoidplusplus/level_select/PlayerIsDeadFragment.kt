package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R


class PlayerIsDeadFragment : Fragment() {
    var nickname:String="kinda_error_i_guess"

    private lateinit var textViewNickname: TextView
    private lateinit var textViewScore: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    var goBackButton: Button?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments?.let { PlayerIsDeadFragmentArgs.fromBundle(it) }
        nickname = args!!.nickname
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player_is_dead, container, false)

        textViewNickname = view.findViewById(R.id.textViewPlayerName)
        textViewScore= view.findViewById(R.id.textViewPlayerScoreNumber)

        goBackButton = view.findViewById(R.id.buttonGoBack2)
        goBackButton!!.setOnClickListener{
            findNavController().navigate(R.id.action_playerIsDeadFragment_to_loginFragment)
        }

        return view
    }

}