package com.blogspot.soyamr.arkanoidplusplus

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.soyamr.arkanoidplusplus.databinding.ActivityCongratulationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CongratulationsActivity : AppCompatActivity() {
    var mediaPlayer: MediaPlayer? = null


    private val viewModel: CongratulationsViewModel by viewModels()

    lateinit var binding: ActivityCongratulationsBinding

    var isMusicOn = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCongratulationsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.lifecycleOwner = this


        viewModel.isMusicOn.observe(this, {
            if (it) {
                mediaPlayer = MediaPlayer.create(applicationContext, R.raw.for_credits);
                isMusicOn = true
            }
        })
    }

    override fun onPause() {
        super.onPause()
        if (isMusicOn) {
            mediaPlayer?.pause();
        }
    }

    override fun onResume() {
        super.onResume()
        if (isMusicOn) {
            mediaPlayer?.start();
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isMusicOn) {
            mediaPlayer?.release();
        }
    }
}