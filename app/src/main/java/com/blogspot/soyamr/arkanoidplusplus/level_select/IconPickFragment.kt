package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.Icon
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.IconsAdapter
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.OnIconIListener

class IconPickFragment : Fragment(), OnIconIListener {

    // repository
    private lateinit var repository: Repository

    private lateinit var iconRecyclerView: RecyclerView
    private lateinit var iconAdapter: IconsAdapter

    private var chosenIconNumber: Int = 1

    var letTheJourneyBeginButton: Button?=null

    // hardcode
    private lateinit var icons: List<Icon>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = Repository(requireContext())
        icons = repository.Icons
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_icon_pick, container, false)

        letTheJourneyBeginButton = view.findViewById(R.id.buttonLetTheJourneyBegin)
        letTheJourneyBeginButton!!.setOnClickListener{
            findNavController().navigate(R.id.action_iconPickFragment_to_storyFragment)
        }

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


}