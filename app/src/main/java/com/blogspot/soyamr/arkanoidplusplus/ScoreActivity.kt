package com.blogspot.soyamr.arkanoidplusplus

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class ScoreActivity : Activity() {
    companion object {
        val SCORE = "score"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra(SCORE, 0)

        findViewById<TextView>(R.id.scoreTextView).apply {
            text = "$score"
        }

    }
}