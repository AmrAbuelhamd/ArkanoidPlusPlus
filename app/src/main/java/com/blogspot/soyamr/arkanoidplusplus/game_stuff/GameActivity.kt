package com.blogspot.soyamr.arkanoidplusplus.game_stuff

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.View
import android.view.ViewGroup
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.Level
import com.blogspot.soyamr.arkanoidplusplus.menu.MainActivity
import com.blogspot.soyamr.arkanoidplusplus.net.UserData
import com.google.firebase.database.*


class GameActivity : Activity() {
    // repository
    private lateinit var repository: Repository

    // firebase
    private lateinit var myRef: DatabaseReference

    // username
    private lateinit var username: String

    // level number
    private var levelNumber: Int = 1

    lateinit var gameSurface: GameSurface
    var firstTime: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        username = intent.extras!!.getString("username").toString()
        levelNumber = intent.extras!!.getInt("level")

        repository = Repository(this)

        val height = getHeight(this)
        val width = getWidth(this)
        firstTime = true;


        gameSurface = GameSurface(this, height, width, repository.ReturnLevel(levelNumber))
        gameSurface.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        setContentView(gameSurface)
    }

    private fun getWidth(context: Context): Int {
        var width: Int = 0
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val displayMetrics = DisplayMetrics()
            val display: Display? = context.getDisplay()
            display!!.getRealMetrics(displayMetrics)
            displayMetrics.widthPixels
        } else {
            val displayMetrics = DisplayMetrics()
            this.windowManager.defaultDisplay.getMetrics(displayMetrics)
            width = displayMetrics.widthPixels
            width
        }
    }

    private fun getHeight(context: Context): Int {
        var height: Int = 0
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val displayMetrics = DisplayMetrics()
            val display = context.display
            display!!.getRealMetrics(displayMetrics)
            displayMetrics.heightPixels
        } else {
            val displayMetrics = DisplayMetrics()
            this.windowManager.defaultDisplay.getMetrics(displayMetrics)
            height = displayMetrics.heightPixels
            height
        }
    }

    override fun onPause() {
        super.onPause()
        gameSurface.pause()
    }


    fun showMainMenu() {
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
        val threadId = Thread.currentThread().id
        println("Thread # $threadId is doing this task")
    }

    fun saveUserScores(score: Int, levelNum: Int) {
        if (username != "not_connected") {
            myRef = FirebaseDatabase.getInstance().reference
            myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val userInfo: UserData? =
                        dataSnapshot.child("users").child(username).getValue(UserData::class.java)
                    if (score == -1 && levelNum == -1)
                    {
                        repository.APIChangeOrAddUser(userInfo!!.nickname, userInfo.score, false, userInfo.icon, userInfo.levels)
                    }
                    else if (levelNum - 1 == userInfo!!.levels) {
                        repository.APIChangeOrAddUser(userInfo.nickname, userInfo.score + score, userInfo.alive, userInfo.icon, levelNum)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                }
            })
        }
    }

}