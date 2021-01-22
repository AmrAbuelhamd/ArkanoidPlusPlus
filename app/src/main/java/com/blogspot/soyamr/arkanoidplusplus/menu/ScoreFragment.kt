package com.blogspot.soyamr.arkanoidplusplus.menu

import android.content.ContentValues.TAG
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.databinding.FragmentScoreBinding
import com.blogspot.soyamr.arkanoidplusplus.net.UserData
import com.blogspot.soyamr.arkanoidplusplus.recycle_score.ScoreAdapter
import com.blogspot.soyamr.arkanoidplusplus.recycle_score.ScoreInfo
import com.google.firebase.database.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScoreFragment : Fragment() {

    private val viewModel: ScoreViewModel by viewModels()

    // repository
    private lateinit var repository: Repository

    var goBackButton: Button?=null

    private lateinit var scoreRecyclerView: RecyclerView
    private lateinit var scoreAdapter: ScoreAdapter




    // animated background
    var animationDrawable: AnimationDrawable? = null
    var frameLayout: FrameLayout? = null
    // animated background

    // hardcode
    var scores = listOf(
        ScoreInfo("NO INTERNET", false, 0, R.drawable.avatar21)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModelCalls()
    }

    private fun setUpViewModelCalls() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.usersDataa.observe(viewLifecycleOwner, { score ->
            scoreAdapter.setAllScore(score)
        })

    }

    lateinit var binding: FragmentScoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(inflater,R.layout.fragment_score, container, false)
        val view = binding.root
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