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

    public fun convertUsersDataToScores(users: List<UserData>) : List<ScoreInfo>{
        var scores: MutableList<ScoreInfo> = mutableListOf<ScoreInfo>()
        for (i in 0 until users.count()) {
            scores.add(convertUserDataToScore(users[i]))
        }
        return scores.toList()
    }

    private fun convertUserDataToScore(user: UserData) : ScoreInfo {
        return ScoreInfo(user.nickname, user.levels == 6, user.score, returnIconID(user.icon))
    }

    private fun returnIconID(number: Int) : Int{
        when (number) {
            1 -> return R.drawable.avatar1
            2 -> return R.drawable.avatar2
            3 -> return R.drawable.avatar3
            4 -> return R.drawable.avatar4
            5 -> return R.drawable.avatar5
            6 -> return R.drawable.avatar6
            7 -> return R.drawable.avatar7
            8 -> return R.drawable.avatar8
            9 -> return R.drawable.avatar9
            10 -> return R.drawable.avatar10
            11 -> return R.drawable.avatar11
            12 -> return R.drawable.avatar12
            13 -> return R.drawable.avatar13
            14 -> return R.drawable.avatar14
            15 -> return R.drawable.avatar15
            16 -> return R.drawable.avatar16
            17 -> return R.drawable.avatar17
            18 -> return R.drawable.avatar18
            19 -> return R.drawable.avatar19
            20 -> return R.drawable.avatar20
            else -> return R.drawable.avatar21
        }

    }

/*    private fun setTokenToNull() {
        mEditor.putString("token", "empty")
        mEditor.commit()
    }*/



}