package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.content.ContentValues
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.net.UserData
import com.blogspot.soyamr.arkanoidplusplus.recycle_score.ScoreInfo
import com.google.firebase.database.*

class LogInViewModel @ViewModelInject constructor(val repository: Repository) : ViewModel() {

    private val _usersData: MutableLiveData<UserData> = MutableLiveData()
    val usersData: LiveData<UserData> = _usersData

    private val _online: MutableLiveData<Boolean> = MutableLiveData(true)
    val online: LiveData<Boolean> = _online


    // firebase
    private lateinit var myRef: DatabaseReference
    private lateinit var usersDataa: MutableList<UserData>



    fun checkInternet() {
        usersDataa = mutableListOf()
        myRef = FirebaseDatabase.getInstance().reference
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                usersDataa.clear()
                //scoreAdapter.clearAllScore()
                val users = dataSnapshot.child("users").children
                for(user in users){
                    val userInfo: UserData? = user.getValue(UserData::class.java)
                    usersDataa.add(userInfo!!)
                }
                //for (DataSnapshot scoreSnapshot : dataSnapshot.getChildren)
                //val users: UserData? = dataSnapshot.getValue(UserData::class.java)
                _online.value = usersDataa.size != 0
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }

    fun logIn(input: String) {
        _online.value = repository.isOnline();
        checkInternet()
        val user: UserData? = usersDataa.find { it.nickname == input }
        _usersData.value = user
    }
}