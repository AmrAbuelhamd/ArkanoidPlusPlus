package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.net.UserData
import com.google.firebase.database.*


class PlayerIsDeadFragment : Fragment() {
    var nickname:String="kinda_error_i_guess"

    private lateinit var textViewNickname: TextView
    private lateinit var textViewScore: TextView

    // firebase
    private lateinit var myRef: DatabaseReference
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

        myRef = FirebaseDatabase.getInstance().reference
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userInfo: UserData? = dataSnapshot.child("users").child(nickname).getValue(UserData::class.java)
                textViewNickname.text = userInfo!!.nickname + "!"
                textViewScore.text = userInfo!!.score.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        goBackButton = view.findViewById(R.id.buttonGoBack2)
        goBackButton!!.setOnClickListener{
            findNavController().navigate(R.id.action_playerIsDeadFragment_to_loginFragment)
        }

        return view
    }

}