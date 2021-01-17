package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.GameActivity
import kotlinx.android.synthetic.main.fragment_level_select.view.*


class LevelSelectFragment : Fragment() {

    // repository
    private lateinit var repository: Repository

    var iconID:Int = -1
    var username: String = "error"

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    // buttons
    private lateinit var buttonLevel1: Button
    private lateinit var buttonLevel2: Button
    private lateinit var buttonLevel3: Button
    private lateinit var buttonLevel4: Button
    private lateinit var buttonLevel5: Button
    private lateinit var buttonLevel6: Button


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
        iconID = args!!.iconID
        username = args!!.username
        val view =  inflater.inflate(R.layout.fragment_level_select, container, false)
        imageView = view.findViewById(R.id.imageViewLevelSelect)
        textView = view.findViewById(R.id.textViewLSUsername)

        textView.text = username
        if (iconID != -1)
        {
            imageView.setImageResource(repository.ReturnIconID(iconID!!))
        }
        else
        {
            imageView.setImageResource(R.drawable.no_internet)
        }

        view.buttonLevel1.setOnClickListener{
            startActivity(Intent(requireContext(), GameActivity::class.java))
        }

        return view
    }

}