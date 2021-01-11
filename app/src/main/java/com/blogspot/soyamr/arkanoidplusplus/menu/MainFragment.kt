package com.blogspot.soyamr.arkanoidplusplus.menu

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.level_select.LevelSelectActivity
import com.blogspot.soyamr.arkanoidplusplus.R


class MainFragment : Fragment() {

    var startButton: Button?=null
    var settingsButton: Button?=null
    var scoreButton: Button?=null


    // animated background
    var animationDrawable: AnimationDrawable? = null
    var frameLayout: FrameLayout? = null
    // animated background

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // declare animation and frameLayout
        frameLayout=view.findViewById(R.id.mainFrameLay)
        animationDrawable= frameLayout!!.background as AnimationDrawable?
        // add time changes
        animationDrawable!!.setEnterFadeDuration(5000)
        animationDrawable!!.setExitFadeDuration(2000)
        // start animation
        animationDrawable!!.start()

        startButton = view.findViewById(R.id.buttonLevel1)
        settingsButton = view.findViewById(R.id.buttonSettings)
        scoreButton = view.findViewById(R.id.buttonScore)

        startButton!!.setOnClickListener{
            startActivity(Intent(activity, LevelSelectActivity::class.java))
        }

        settingsButton!!.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
            //startActivity(Intent(activity, SettingsActivity::class.java))
        }

        scoreButton!!.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_scoreFragment)
            //startActivity(Intent(activity, ScoreActivity::class.java))
        }

        return view
    }

    companion object {

    }
}