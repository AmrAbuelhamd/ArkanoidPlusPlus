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
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.net.UserData
import com.blogspot.soyamr.arkanoidplusplus.recycle_score.ScoreAdapter
import com.blogspot.soyamr.arkanoidplusplus.recycle_score.ScoreInfo
import com.google.firebase.database.*


class ScoreFragment : Fragment() {

    private lateinit var repository: Repository

    var goBackButton: Button?=null

    private lateinit var scoreRecyclerView: RecyclerView
    private lateinit var scoreAdapter: ScoreAdapter


    // firebase
    private lateinit var myRef: DatabaseReference
    private lateinit var usersData: MutableList<UserData>

    // animated background
    var animationDrawable: AnimationDrawable? = null
    var frameLayout: FrameLayout? = null
    // animated background

    // hardcode
    var scores = listOf(
        ScoreInfo("NO INTERNET", false, 420, R.drawable.avatar21)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = Repository(requireContext())
        usersData = mutableListOf()
        myRef = FirebaseDatabase.getInstance().reference
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                usersData.clear()
                scoreAdapter.clearAllScore()
                val users = dataSnapshot.child("users").children
                for(user in users){
                    val userInfo: UserData? = user.getValue(UserData::class.java)
                    usersData.add(userInfo!!)
                }
                //for (DataSnapshot scoreSnapshot : dataSnapshot.getChildren)
                //val users: UserData? = dataSnapshot.getValue(UserData::class.java)
                scores = repository.convertUsersDataToScores(usersData)
                scoreAdapter.setAllScore(scores)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
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