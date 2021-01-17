package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.GameActivity
import kotlinx.android.synthetic.main.fragment_level_select.*
import kotlinx.android.synthetic.main.fragment_level_select.view.*


class LevelSelectFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_level_select, container, false)

        view.buttonLevel1.setOnClickListener{
            startActivity(Intent(requireContext(), GameActivity::class.java))
        }

        return view
    }

}