package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.IconsAdapter
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.OnIconIListener

class IconPickFragment : Fragment(), OnIconIListener {

    private lateinit var iconRecyclerView: RecyclerView
    private lateinit var iconAdapter: IconsAdapter


    // hardcode
    var icons = listOf(
        R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3,
        R.drawable.avatar4, R.drawable.avatar5, R.drawable.avatar6,
        R.drawable.avatar7, R.drawable.avatar8, R.drawable.avatar9,
        R.drawable.avatar10, R.drawable.avatar11, R.drawable.avatar12,
        R.drawable.avatar13, R.drawable.avatar14, R.drawable.avatar15,
        R.drawable.avatar16, R.drawable.avatar17, R.drawable.avatar18,
        R.drawable.avatar19, R.drawable.avatar20, R.drawable.avatar21
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
        TODO("Not yet implemented")
    }

    // hardcode

}