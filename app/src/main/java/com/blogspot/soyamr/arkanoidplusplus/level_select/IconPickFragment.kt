package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.databinding.ActivityMainBinding
import com.blogspot.soyamr.arkanoidplusplus.databinding.FragmentIconPickBinding
import com.blogspot.soyamr.arkanoidplusplus.menu.MainActivity
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.Icon
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.IconsAdapter
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.OnIconIListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IconPickFragment : Fragment(), OnIconIListener {

    private val viewModel: IconPickViewModel by viewModels()

    lateinit var binding: FragmentIconPickBinding


    private lateinit var iconRecyclerView: RecyclerView
    private lateinit var iconAdapter: IconsAdapter

    private var chosenIconNumber: Int = 0

    var letTheJourneyBeginButton: Button? = null

    var nickname: String = "kinda_error_i_guess"

    private var icons: MutableList<Icon>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private fun setUpViewModelCalls() {
        binding.lifecycleOwner = this

        viewModel.icons.observe(viewLifecycleOwner, { icons ->
            iconAdapter.setAllIcons(icons.toMutableList())
            this.icons = icons.toMutableList()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val args = arguments?.let { IconPickFragmentArgs.fromBundle(it) }
        nickname = args?.nickname!!

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_icon_pick, container, false)
        val view = binding.root
        setUpViewModelCalls()
        letTheJourneyBeginButton = view.findViewById(R.id.buttonLetTheJourneyBegin)
        letTheJourneyBeginButton!!.setOnClickListener {
            viewModel.apiChangeOrAddUser(nickname, chosenIconNumber + 1)
            val action = IconPickFragmentDirections.actionIconPickFragmentToStoryFragment(
                chosenIconNumber + 1,
                nickname
            )
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
        if (chosenIconNumber != null) {
            icons?.get(chosenIconNumber!!)?.activated = false
        }
        chosenIconNumber = position
        icons?.get(chosenIconNumber!!)?.activated = true
    }


}