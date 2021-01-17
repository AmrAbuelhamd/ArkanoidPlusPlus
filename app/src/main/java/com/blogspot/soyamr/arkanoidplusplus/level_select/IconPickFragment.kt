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

    private var chosenIconNumber: Int = 0

    var letTheJourneyBeginButton: Button?=null

    var nickname:String="kinda_error_i_guess"

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
        val args = arguments?.let { IconPickFragmentArgs.fromBundle(it) }
        nickname = args?.nickname!!
        val view =  inflater.inflate(R.layout.fragment_icon_pick, container, false)

        letTheJourneyBeginButton = view.findViewById(R.id.buttonLetTheJourneyBegin)
        letTheJourneyBeginButton!!.setOnClickListener{
            repository.APIChangeOrAddUser(nickname, 0, true, chosenIconNumber + 1, 1)
            val action = IconPickFragmentDirections.actionIconPickFragmentToStoryFragment(chosenIconNumber + 1, nickname)
            findNavController().navigate(action)
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