package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.content.ContentValues
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
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
import com.blogspot.soyamr.arkanoidplusplus.net.UserData
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_level_select.view.*


class LevelSelectFragment : Fragment() {

    // firebase
    private lateinit var myRef: DatabaseReference
    private lateinit var usersData: MutableList<UserData>

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
    //var buttons = listOf(buttonLevel1, buttonLevel2, buttonLevel3, buttonLevel4, buttonLevel5, buttonLevel6)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = Repository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        usersData = mutableListOf()

        val args = arguments?.let { LevelSelectFragmentArgs.fromBundle(it) }
        iconID = args!!.iconID
        username = args!!.username
        val view =  inflater.inflate(R.layout.fragment_level_select, container, false)
        imageView = view.findViewById(R.id.imageViewLevelSelect)
        textView = view.findViewById(R.id.textViewLSUsername)

        buttonLevel1 = view.findViewById(R.id.buttonLevel1)
        buttonLevel2 = view.findViewById(R.id.buttonLevel2)
        buttonLevel3 = view.findViewById(R.id.buttonLevel3)
        buttonLevel4 = view.findViewById(R.id.buttonLevel4)
        buttonLevel5 = view.findViewById(R.id.buttonLevel5)
        buttonLevel6 = view.findViewById(R.id.buttonLevel6)
        var buttons = listOf(buttonLevel1, buttonLevel2, buttonLevel3, buttonLevel4, buttonLevel5, buttonLevel6)
        textView.text = username

        if (iconID != -1)
        {
            imageView.setImageResource(repository.ReturnIconID(iconID!!))
            myRef = FirebaseDatabase.getInstance().reference
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    usersData.clear()
                    val userInfo: UserData? = dataSnapshot.child("users").child(username).getValue(UserData::class.java)
                    for (i in 0 until userInfo!!.levels)
                    {
                        buttons[i].visibility = View.VISIBLE
                    }


                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                }
            })
        }
        else
        {
            imageView.setImageResource(R.drawable.no_internet)

        }

/*        view.buttonLevel1.setOnClickListener{
            startActivity(Intent(requireContext(), GameActivity::class.java))
        }*/

        return view
    }

}