package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.GameActivity
import kotlinx.android.synthetic.main.fragment_level_select.*
import kotlinx.android.synthetic.main.fragment_level_select.view.*


class LevelSelectFragment : Fragment() {

    // repository
    private lateinit var repository: Repository

    var iconID:Int?=-1
    private lateinit var imageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = Repository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val args = arguments?.let { LevelSelectFragmentArgs.fromBundle(it) }
        iconID = args?.iconID
        val view =  inflater.inflate(R.layout.fragment_level_select, container, false)
        if (iconID != -1) {
            imageView = view.findViewById(R.id.imageViewLevelSelect)
            imageView.setImageResource(repository.ReturnIconID(iconID!!))
        }

        view.buttonLevel1.setOnClickListener{
            startActivity(Intent(requireContext(), GameActivity::class.java))
        }

        return view
    }

}