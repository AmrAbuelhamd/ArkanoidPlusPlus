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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.arkanoidplusplus.R

import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.IconsAdapter
import com.blogspot.soyamr.arkanoidplusplus.recycle_score.ScoreAdapter
import com.blogspot.soyamr.arkanoidplusplus.recycle_score.ScoreInfo


class ScoreFragment : Fragment() {

    var goBackButton: Button?=null

    private lateinit var scoreRecyclerView: RecyclerView
    private lateinit var scoreAdapter: ScoreAdapter

    // animated background
    var animationDrawable: AnimationDrawable? = null
    var frameLayout: FrameLayout? = null
    // animated background

    // hardcode
    var scores = listOf(
        ScoreInfo("blogpost", true, 1337, R.drawable.avatar1),
        ScoreInfo("zaria", false, 228, R.drawable.avatar3),
        ScoreInfo("l33t", true, 5000, R.drawable.avatar21),
        ScoreInfo("oni.", false, 1000, R.drawable.avatar11),
        ScoreInfo("done", false, 1263, R.drawable.avatar4),
        ScoreInfo("played", false, 1842, R.drawable.avatar10),
        ScoreInfo("yeeeeeeep", false, 1056, R.drawable.avatar12),
        ScoreInfo("gotcha", false, 1947, R.drawable.avatar5),
        ScoreInfo("blogpost", true, 1337, R.drawable.avatar1),
        ScoreInfo("zaria", false, 228, R.drawable.avatar3),
        ScoreInfo("l33t", true, 5000, R.drawable.avatar21),
        ScoreInfo("oni.", false, 1000, R.drawable.avatar11),
        ScoreInfo("done", false, 1263, R.drawable.avatar4),
        ScoreInfo("played", false, 1842, R.drawable.avatar10),
        ScoreInfo("yeeeeeeep", false, 1056, R.drawable.avatar12),
        ScoreInfo("gotcha", false, 1947, R.drawable.avatar5)
    )

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

        goBackButton = view.findViewById(R.id.buttonGoBack)

        goBackButton!!.setOnClickListener{
            findNavController().navigate(R.id.action_scoreFragment_to_mainFragment)
        }



        // recycler init
        scoreRecyclerView = view.findViewById(R.id.scoreRecycleView)
        scoreRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        scoreAdapter = ScoreAdapter()
        scoreRecyclerView.adapter = scoreAdapter

        // hardcode
        scoreAdapter.setAllScore(scores)




        //view.findViewById<TextView>(R.id.currScoreTextView).apply {
       //     text = "${requireActivity().intent.getIntExtra(ScoreActivity.SCORE, 0)}"
        //}

        return view
    }

    companion object {

    }
}