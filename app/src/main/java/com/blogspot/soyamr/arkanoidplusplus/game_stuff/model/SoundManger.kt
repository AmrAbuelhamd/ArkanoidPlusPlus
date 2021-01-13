package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model

import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.util.Log
import com.blogspot.soyamr.arkanoidplusplus.R
import java.io.IOException

class SoundManger(private val context: Context) {
    // For sound FX
    private val soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
    private var beep1ID = -1
    private var beep2ID = -1
    private var beep3ID = -1
    private var loseLifeID = -1
    private var explodeID = -1

    init {
        try {
            // Load our fx in memory ready for use
            beep1ID = soundPool.load(context, R.raw.beep1, 0)
            beep2ID = soundPool.load(context, R.raw.beep2, 0)
            beep3ID = soundPool.load(context, R.raw.beep3, 0)
            loseLifeID = soundPool.load(context, R.raw.lose_life, 0)
            explodeID = soundPool.load(context, R.raw.explode, 0)
        } catch (e: IOException) {
            // Print an error message to the console
            Log.e("error", "failed to load sound files")
        }
    }

    fun ballCollideWithPaddle(){
        soundPool.play(beep1ID, 1F, 1F, 0, 0, 1F);
    }
    fun ballCollideWithLeftWall(){
        soundPool.play(beep3ID, 1F, 1F, 0, 0, 1F);
    }
    fun ballCollideWithRightWall(){
        soundPool.play(beep3ID, 1F, 1F, 0, 0, 1F);
    }
    fun ballCollideWithBottomGround(){
        soundPool.play(loseLifeID, 1F, 1F, 0, 0, 1F);
    }
    fun ballCollideWithTopRoof(){
        soundPool.play(beep2ID, 1F, 1F, 0, 0, 1F);
    }
    fun ballCollideWithBrick(){
        soundPool.play(explodeID, 1f, 1f, 0, 0, 1f)
    }
    fun release(){
        soundPool.release()
    }
}