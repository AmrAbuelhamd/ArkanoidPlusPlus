package com.blogspot.soyamr.arkanoidplusplus.menu

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R


class ScoreFragment : Fragment() {

    var resetButton: Button?=null
    var goBackButton: Button?=null

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
        val view = inflater.inflate(R.layout.fragment_score, container, false)


        // declare animation and frameLayout
        frameLayout=view.findViewById(R.id.scoreFrameLay)
        animationDrawable= frameLayout!!.background as AnimationDrawable?
        // add time changes
        animationDrawable!!.setEnterFadeDuration(5000)
        animationDrawable!!.setExitFadeDuration(2000)
        // start animation
        animationDrawable!!.start()

        resetButton = view.findViewById(R.id.buttonReset)
        goBackButton = view.findViewById(R.id.buttonGoBack)

        resetButton!!.setOnClickListener{
            //TODO
        }

        goBackButton!!.setOnClickListener{
            findNavController().navigate(R.id.action_scoreFragment_to_mainFragment)
        }



        //view.findViewById<TextView>(R.id.currScoreTextView).apply {
       //     text = "${requireActivity().intent.getIntExtra(ScoreActivity.SCORE, 0)}"
        //}

        return view
    }

    companion object {

    }
}