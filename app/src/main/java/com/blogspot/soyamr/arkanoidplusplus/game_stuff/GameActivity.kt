package com.blogspot.soyamr.arkanoidplusplus.game_stuff

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.ViewGroup
import com.blogspot.soyamr.arkanoidplusplus.CongratulationsActivity
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.menu.MainActivity
import com.blogspot.soyamr.arkanoidplusplus.net.UserData
import com.blogspot.soyamr.arkanoidplusplus.R
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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
    var mediaPlayer: MediaPlayer? = null

    private var isMusicOn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        username = intent.extras!!.getString("username").toString()
        levelNumber = intent.extras!!.getInt("level")

        repository = Repository(this)

        isMusicOn = repository.settingsGetMusic()
        val height = getHeight(this)
        val width = getWidth(this)
        firstTime = true;


        gameSurface = GameSurface(this, height, width, repository.getLevel(levelNumber))
        gameSurface.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        setContentView(gameSurface)
        if (isMusicOn)
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.for_level);
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
        if (isMusicOn)
            mediaPlayer?.pause();
    }


    override fun onResume() {
        super.onResume()
        if (isMusicOn)
            mediaPlayer?.start();
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isMusicOn)
            mediaPlayer?.release();
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
                    if (score == -1 && levelNum == -1) {
                        repository.apiChangeOrAddUser(
                            userInfo!!.nickname,
                            userInfo.score,
                            false,
                            userInfo.icon,
                            userInfo.levels
                        )
                    } else if (levelNum == userInfo!!.levels) {
                        if (levelNum == 6)
                            repository.apiChangeOrAddUser(userInfo.nickname, userInfo.score + score, false, userInfo.icon, levelNum + 1)
                        else
                            repository.apiChangeOrAddUser(userInfo.nickname, userInfo.score + score, userInfo.alive, userInfo.icon, levelNum + 1)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                }
            })
        }
    }

    fun showPrizeScreen() {
        val intent = Intent(this, CongratulationsActivity::class.java)
        finish()
        startActivity(intent)
    }

    fun playBonusLevelMusic() {
        GlobalScope.launch(Dispatchers.Main){
            if (isMusicOn) {
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer.create(applicationContext, R.raw.for_bonus_level);
                mediaPlayer?.start()
            }
        }

    }

}