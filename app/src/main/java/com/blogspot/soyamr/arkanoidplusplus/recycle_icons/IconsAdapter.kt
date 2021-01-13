package com.blogspot.soyamr.arkanoidplusplus.recycle_icons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.arkanoidplusplus.R


class IconsAdapter(onIconIListener: OnIconIListener) : RecyclerView.Adapter<IconsAdapter.IconsViewHolder>() {

    private val iconsList: MutableList<Int> = ArrayList()
    private val onIconIListener: OnIconIListener = onIconIListener

    class IconsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.icon)
        fun bind() {

        }
    }

    fun setAllIcons(icons: List<Int>){
        iconsList.addAll(icons)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_icon, parent, false)
        return IconsViewHolder(view)
    }


    override fun getItemCount(): Int {
        return iconsList.size
    }

    override fun onBindViewHolder(holder: IconsViewHolder, position: Int) {
        val imageID = iconsList[position]
        holder.image.setImageResource(imageID)
    }

}