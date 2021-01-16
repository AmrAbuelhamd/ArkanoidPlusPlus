package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class LoginFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var myRef: DatabaseReference

    private var isOnline = false

    var letsGoButton: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        auth.signInAnonymously()
            .addOnCompleteListener(requireActivity()){ task ->
                isOnline = task.isSuccessful
            }
        myRef = FirebaseDatabase.getInstance().reference
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        letsGoButton = view.findViewById(R.id.buttonLetsGo)
        letsGoButton!!.setOnClickListener{
            myRef.child("User")
            findNavController().navigate(R.id.action_loginFragment_to_iconPickFragment)
        }
        isOnline
        return view
    }

}