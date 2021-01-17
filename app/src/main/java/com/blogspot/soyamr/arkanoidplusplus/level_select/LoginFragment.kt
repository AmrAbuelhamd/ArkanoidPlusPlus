package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.net.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class LoginFragment : Fragment() {

    // repository
    private lateinit var repository: Repository

    // firebase
    private lateinit var myRef: DatabaseReference
    private lateinit var usersData: MutableList<UserData>

    private var isOnline = false

    var letsGoButton: Button?=null
    var editText: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = Repository(requireContext())
        usersData = mutableListOf()
        myRef = FirebaseDatabase.getInstance().reference
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                usersData.clear()
                //scoreAdapter.clearAllScore()
                val users = dataSnapshot.child("users").children
                for(user in users){
                    val userInfo: UserData? = user.getValue(UserData::class.java)
                    usersData.add(userInfo!!)
                }
                //for (DataSnapshot scoreSnapshot : dataSnapshot.getChildren)
                //val users: UserData? = dataSnapshot.getValue(UserData::class.java)
                isOnline = usersData.size != 0
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        letsGoButton = view.findViewById(R.id.buttonLetsGo)
        letsGoButton!!.setOnClickListener{
            val input = editText!!.text.toString()
            val user: UserData? = usersData.find{ it.nickname == input }
            if (isOnline)
            {
                if (user != null)
                {

                }
                else
                {

                }

            }
            else
            {

            }
            findNavController().navigate(R.id.action_loginFragment_to_iconPickFragment)
        }
        return view
    }

}