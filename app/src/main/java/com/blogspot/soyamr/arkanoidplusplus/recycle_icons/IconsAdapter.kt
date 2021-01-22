package com.blogspot.soyamr.arkanoidplusplus.recycle_icons

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.arkanoidplusplus.R


class IconsAdapter(onIconIListener: OnIconIListener) :
    RecyclerView.Adapter<IconsAdapter.IconsViewHolder>() {

    private val iconsList: MutableList<Icon> = ArrayList()
    private val onIconIListener: OnIconIListener = onIconIListener

    class IconsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.icon)
        fun bind(icon: Icon, onIconIListener: OnIconIListener) {
            if (icon.activated) {
                image.setBackgroundColor(Color.DKGRAY)
            } else {
                image.setBackgroundColor(Color.BLACK)
            }

            image.setOnClickListener {
                onIconIListener.onIconClick(adapterPosition)
            }

            image.setImageResource(icon.imageID)
        }
    }


    fun setAllIcons(icons: MutableList<Icon>?) {
        try {
            if (icons != null)
                iconsList.addAll(icons)
        } catch (e: Exception) {
        }
        notifyDataSetChanged()
    }

    fun changeChosenIcon(prev: Int?, curr: Int) {
        if (prev != null) {
            iconsList[prev].activated = false
            notifyItemChanged(prev)
        }
        iconsList[curr].activated = true
        notifyItemChanged(curr)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_icon, parent, false)
        return IconsViewHolder(view)
    }


    override fun getItemCount(): Int {
        return iconsList.size
    }

    override fun onBindViewHolder(holder: IconsViewHolder, position: Int) {
        holder.bind(iconsList[position], onIconIListener);
    }

}