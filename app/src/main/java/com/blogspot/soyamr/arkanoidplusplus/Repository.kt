package com.blogspot.soyamr.arkanoidplusplus

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.Level
import com.blogspot.soyamr.arkanoidplusplus.net.UserData
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.Icon
import com.blogspot.soyamr.arkanoidplusplus.recycle_score.ScoreInfo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(@ApplicationContext val context: Context) {

    private lateinit var myRef: DatabaseReference

    private var mPreferences: SharedPreferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    private lateinit var mEditor: SharedPreferences.Editor

    init {
        mEditor = mPreferences.edit()
        myRef = FirebaseDatabase.getInstance().reference
    }

    fun settingsSetExitNotification(exit: Boolean) {
        mEditor.putBoolean("exitNotification", exit);
        mEditor.commit()
    }

    fun settingsGetExitNotification(): Boolean {
        return mPreferences.getBoolean("exitNotification", true)
    }

     fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
//        }
        return false
    }


    fun settingsSetSound(sound: Boolean) {
        mEditor.putBoolean("sound", sound);
        mEditor.commit()
    }

    fun settingsSetMusic(music: Boolean) {
        mEditor.putBoolean("music", music);
        mEditor.commit()
    }

    fun settingsSetTouch(touch: Boolean) {
        mEditor.putBoolean("touch", touch);
        mEditor.commit()
    }

    fun settingsGetSound(): Boolean {
        return mPreferences.getBoolean("sound", true)
    }

    fun settingsGetMusic(): Boolean {
        return mPreferences.getBoolean("music", true)
    }

    fun settingsGetTouch(): Boolean {
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

    fun apiChangeOrAddUser(nickname: String, score: Int, alive: Boolean, icon: Int, levels: Int) {
        val user = UserData(nickname, score, alive, icon, levels)
        myRef.child("users").child(nickname).setValue(user)
    }

    public fun convertUsersDataToScores(users: List<UserData>): List<ScoreInfo> {
        var scores: MutableList<ScoreInfo> = mutableListOf<ScoreInfo>()
        for (i in 0 until users.count()) {
            scores.add(convertUserDataToScore(users[i]))
        }
        return scores.toList()
    }

    private fun convertUserDataToScore(user: UserData): ScoreInfo {
        return ScoreInfo(user.nickname, user.levels == 7, user.score, getIconID(user.icon))
    }

    fun getIconID(number: Int): Int {
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

    fun getLevel(number: Int): Level {
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