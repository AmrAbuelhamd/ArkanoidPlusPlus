package com.blogspot.soyamr.arkanoidplusplus.recycle_score

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.Icon

class ScoreAdapter: RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    private val scoreInfoList: MutableList<ScoreInfo> = ArrayList()

    fun setAllScore(scores: List<ScoreInfo>){
        scoreInfoList.addAll(scores)
        notifyDataSetChanged()
    }

    fun clearAllScore(){
        scoreInfoList.clear()
        notifyDataSetChanged()
    }

    class ScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.score_icon)
        val nickname: TextView = itemView.findViewById(R.id.nickname)
        val score: TextView = itemView.findViewById(R.id.score)
        fun bind(scoreInfo: ScoreInfo) {
            image.setImageResource(scoreInfo.icon)
            nickname.text = scoreInfo.nickname
            score.text = scoreInfo.score.toString()

            if (scoreInfo.golden)
            {
                nickname.setTextColor(Color.YELLOW)
                score.setTextColor(Color.YELLOW)
            }
            else
            {
                nickname.setTextColor(Color.WHITE)
                score.setTextColor(Color.WHITE)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_score, parent, false)
        return ScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.bind(scoreInfoList[position]);
    }

    override fun getItemCount(): Int {
        return scoreInfoList.size
    }
}