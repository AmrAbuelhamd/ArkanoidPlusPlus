package com.blogspot.soyamr.arkanoidplusplus

import android.content.Context
import android.content.SharedPreferences
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.Level
import com.blogspot.soyamr.arkanoidplusplus.net.UserData
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.Icon
import com.blogspot.soyamr.arkanoidplusplus.recycle_score.ScoreInfo
import com.google.firebase.database.*

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

    fun SettingsSetExitNotification(exit:Boolean){
        mEditor.putBoolean("exitNotification", exit);
        mEditor.commit()
    }

    fun SettingsGetExitNotification() : Boolean {
        return mPreferences.getBoolean("exitNotification", true)
    }


    fun SettingsSetSound(sound:Boolean){
        mEditor.putBoolean("sound", sound);
        mEditor.commit()
    }

    fun SettingsSetMusic(music:Boolean){
        mEditor.putBoolean("music", music);
        mEditor.commit()
    }

    fun SettingsSetTouch(touch:Boolean){
        mEditor.putBoolean("touch", touch);
        mEditor.commit()
    }

    fun SettingsGetSound() : Boolean {
        return mPreferences.getBoolean("sound", true)
    }

    fun SettingsGetMusic() : Boolean {
        return mPreferences.getBoolean("music", true)
    }

    fun SettingsGetTouch() : Boolean {
        return mPreferences.getBoolean("touch", true)
    }




    var Icons = listOf(
        Icon(R.drawable.avatar1), Icon(R.drawable.avatar2), Icon(R.drawable.avatar3),
        Icon(R.drawable.avatar4), Icon(R.drawable.avatar5), Icon(R.drawable.avatar6),
        Icon(R.drawable.avatar7), Icon(R.drawable.avatar8), Icon(R.drawable.avatar9),
        Icon(R.drawable.avatar10), Icon(R.drawable.avatar11), Icon(R.drawable.avatar12),
        Icon(R.drawable.avatar13), Icon(R.drawable.avatar14), Icon(R.drawable.avatar15),
        Icon(R.drawable.avatar16), Icon(R.drawable.avatar17), Icon(R.drawable.avatar18),
        Icon(R.drawable.avatar19), Icon(R.drawable.avatar20), Icon(R.drawable.avatar21)
    )

    fun APIChangeOrAddUser(nickname: String, score: Int, alive: Boolean, icon: Int, levels: Int){
        val user = UserData(nickname, score, alive, icon, levels)
        myRef.child("users").child(nickname).setValue(user)
    }

    public fun convertUsersDataToScores(users: List<UserData>) : List<ScoreInfo>{
        var scores: MutableList<ScoreInfo> = mutableListOf<ScoreInfo>()
        for (i in 0 until users.count()) {
            scores.add(convertUserDataToScore(users[i]))
        }
        return scores.toList()
    }

    private fun convertUserDataToScore(user: UserData) : ScoreInfo {
        return ScoreInfo(user.nickname, user.levels == 6, user.score, ReturnIconID(user.icon))
    }

    fun ReturnIconID(number: Int) : Int{
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

    fun ReturnLevel(number: Int) : Level {
        return when (number) {
            1 -> Level.FIRST
            2 -> Level.SECOND
            3 -> Level.THIRD
            4 -> Level.FOURTH
            5 -> Level.FIFTH
            6 -> Level.BONUS
            else -> Level.BONUS
        }
    }

/*    private fun setTokenToNull() {
        mEditor.putString("token", "empty")
        mEditor.commit()
    }*/



}