package com.blogspot.soyamr.arkanoidplusplus.level_select

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.recycle_icons.Icon

class IconPickViewModel @ViewModelInject constructor(val repository: Repository) : ViewModel() {
    fun apiChangeOrAddUser(nickname: String, chosenIconNumber: Int) {
        repository.apiChangeOrAddUser(nickname, 0, true, chosenIconNumber, 1)
    }

    private val _icons: MutableLiveData<List<Icon>> = MutableLiveData(repository.Icons)
    val icons: LiveData<List<Icon>> = _icons

}