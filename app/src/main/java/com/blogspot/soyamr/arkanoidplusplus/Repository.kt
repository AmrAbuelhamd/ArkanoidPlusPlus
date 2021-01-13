package com.blogspot.soyamr.arkanoidplusplus

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.blogspot.soyamr.arkanoidplusplus.net.UserData
import com.blogspot.soyamr.arkanoidplusplus.recycle_score.ScoreInfo
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import java.lang.Exception

object Repository {
    private var context: Context? = null

    private lateinit var myRef: DatabaseReference

    private lateinit var mPreferences: SharedPreferences
    private lateinit var mEditor: SharedPreferences.Editor

    operator fun invoke(gotContext: Context): Repository {
        context = gotContext
        mPreferences = context!!.getSharedPreferences("settings", Context.MODE_PRIVATE)
        mEditor = mPreferences.edit()
        myRef = FirebaseDatabase.getInstance().reference
        return this
    }

    private fun APIChangeOrAddUser(nickname: String, score: Int, alive: Boolean, icon: Int, levels: Int){
        val user = UserData(nickname, score, alive, icon, levels)
        myRef.child("users").child("nickname").setValue(user)
    }

    public fun convertUsersDataToScores(users: MutableList<UserData>) : List<ScoreInfo>{
        var scores: MutableList<ScoreInfo> = mutableListOf()
        for (i in 0 until users.count()) {
            scores.add(convertUserDataToScore(users[i]))
        }
        return scores.toList()
    }

    private fun convertUserDataToScore(user: UserData) : ScoreInfo{
        return ScoreInfo(user.nickname, user.levels == 6, user.icon, user.score)
    }


/*    private fun setTokenToNull() {
        mEditor.putString("token", "empty")
        mEditor.commit()
    }*/



}