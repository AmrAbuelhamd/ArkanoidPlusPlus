package com.blogspot.soyamr.arkanoidplusplus.menu

import android.content.ContentValues
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.net.UserData
import com.blogspot.soyamr.arkanoidplusplus.recycle_score.ScoreInfo
import com.google.firebase.database.*

class ScoreViewModel @ViewModelInject constructor(val repository: Repository) : ViewModel() {

    private val _usersData: MutableLiveData<List<ScoreInfo>> = MutableLiveData()
    val usersDataa: LiveData<List<ScoreInfo>> = _usersData
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading
    // firebase
    private lateinit var myRef: DatabaseReference
    private lateinit var usersData: MutableList<UserData>

    // hardcode
    var scores = listOf(
        ScoreInfo("NO INTERNET", false, 0, R.drawable.avatar21)
    )

    init {
        usersData = mutableListOf()
        myRef = FirebaseDatabase.getInstance().reference
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                _isLoading.value = true
                val users = dataSnapshot.child("users").children
                usersData.clear()
                for(user in users){
                    val userInfo: UserData? = user.getValue(UserData::class.java)
                    usersData.add(userInfo!!)
                }
                usersData.sortBy { obj -> obj.score }
                usersData.reverse()

                if (usersData.size > 200)
                    usersData = usersData.subList(0,200)

                //for (DataSnapshot scoreSnapshot : dataSnapshot.getChildren)
                //val users: UserData? = dataSnapshot.getValue(UserData::class.java)
                scores = repository.convertUsersDataToScores(usersData)
                _usersData.value = scores
                _isLoading.value = false
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }
}