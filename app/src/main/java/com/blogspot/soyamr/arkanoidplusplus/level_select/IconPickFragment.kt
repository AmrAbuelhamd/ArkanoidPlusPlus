package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.Icon
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.IconsAdapter
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.OnIconIListener

class IconPickFragment : Fragment(), OnIconIListener {

    private lateinit var iconRecyclerView: RecyclerView
    private lateinit var iconAdapter: IconsAdapter

    private var chosenIconNumber: Int? = null


    // hardcode
    var icons = listOf(
        Icon(R.drawable.avatar1), Icon(R.drawable.avatar2), Icon(R.drawable.avatar3),
        Icon(R.drawable.avatar4), Icon(R.drawable.avatar5), Icon(R.drawable.avatar6),
        Icon(R.drawable.avatar7), Icon(R.drawable.avatar8), Icon(R.drawable.avatar9),
        Icon(R.drawable.avatar10), Icon(R.drawable.avatar11), Icon(R.drawable.avatar12),
        Icon(R.drawable.avatar13), Icon(R.drawable.avatar14), Icon(R.drawable.avatar15),
        Icon(R.drawable.avatar16), Icon(R.drawable.avatar17), Icon(R.drawable.avatar18),
        Icon(R.drawable.avatar19), Icon(R.drawable.avatar20), Icon(R.drawable.avatar21)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_icon_pick, container, false)

        // recycler init
        iconRecyclerView = view.findViewById(R.id.iconsRecycleView)
        iconRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        iconAdapter = IconsAdapter(this)
        iconRecyclerView.adapter = iconAdapter


        // hardcode
        iconAdapter.setAllIcons(icons)

        return view
    }

    override fun onIconClick(position: Int) {
       iconAdapter.changeChosenIcon(chosenIconNumber, position)
        if (chosenIconNumber != null)
        {
            icons[chosenIconNumber!!].activated = false
        }
        chosenIconNumber = position
        icons[chosenIconNumber!!].activated = true
    }

    // hardcode

}