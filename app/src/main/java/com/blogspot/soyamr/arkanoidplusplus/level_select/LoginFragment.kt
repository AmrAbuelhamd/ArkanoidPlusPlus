package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.databinding.FragmentLoginBinding
import com.blogspot.soyamr.arkanoidplusplus.net.UserData
import com.google.firebase.database.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {


    private val viewModel: LogInViewModel by viewModels()
    lateinit var binding: FragmentLoginBinding


    // repository
    private lateinit var repository: Repository

    // firebase
    private lateinit var myRef: DatabaseReference
    private lateinit var usersData: MutableList<UserData>

    private var isOnline = false

    var letsGoButton: Button? = null
    var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repository = Repository(requireContext())
        usersData = mutableListOf()
        myRef = FirebaseDatabase.getInstance().reference
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                usersData.clear()
                //scoreAdapter.clearAllScore()
                val users = dataSnapshot.child("users").children
                for (user in users) {
                    val userInfo: UserData? = user.getValue(UserData::class.java)
                    usersData.add(userInfo!!)
                }
                //for (DataSnapshot scoreSnapshot : dataSnapshot.getChildren)
                //val users: UserData? = dataSnapshot.getValue(UserData::class.java)
                isOnline = usersData.size != 0
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val view = binding.root
        editText = view.findViewById(R.id.editTextUsername)
        letsGoButton = view.findViewById(R.id.buttonLetsGo)
        letsGoButton!!.setOnClickListener {
            val input = editText!!.text.toString()
            if (input == "") {
                Toast.makeText(requireContext(), "Please enter your name!", Toast.LENGTH_SHORT)
                    .show()
            } else if (input.length > 8) {
                Toast.makeText(
                    requireContext(),
                    "Name cannot contain more than 8 characters!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val user: UserData? = usersData.find { it.nickname == input }
                if (isOnline) {
                    if (user != null) {
                        if (user.alive) {
                            val action =
                                LoginFragmentDirections.actionLoginFragmentToLevelSelectFragment(
                                    user.icon, user.nickname
                                )
                            findNavController().navigate(action)
                        } else {
                            val action =
                                LoginFragmentDirections.actionLoginFragmentToPlayerIsDeadFragment(
                                    user.nickname
                                )
                            findNavController().navigate(action)
                        }
                    } else {
                        val action =
                            LoginFragmentDirections.actionLoginFragmentToIconPickFragment(input)
                        findNavController().navigate(action)
                    }
                } else {
                    val action =
                        LoginFragmentDirections.actionLoginFragmentToNoInternetFragment(
                            input
                        )
                    findNavController().navigate(action)
                }
            }

        }
        setUpViewModelCalls()
        return view
    }

    private fun setUpViewModelCalls() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }


}