package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R



class StoryFragment : Fragment() {
    var readyButton: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments?.let { StoryFragmentArgs.fromBundle(it) }
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_story, container, false)

        readyButton = view.findViewById(R.id.buttonReady)
        readyButton!!.setOnClickListener{
            val action = StoryFragmentDirections.actionStoryFragmentToLevelSelectFragment(args!!.iconID,args!!.username)
            findNavController().navigate(action)
        }

        return view
    }

}