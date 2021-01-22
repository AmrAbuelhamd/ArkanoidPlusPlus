package com.blogspot.soyamr.arkanoidplusplus.menu

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blogspot.soyamr.arkanoidplusplus.Repository

class MainActivityViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {
    fun settingsSetExitNotification(b: Boolean) {
        repository.settingsGetExitNotification()
    }

    private val _isMusicOn = MutableLiveData(false)
    val isMusicOn: LiveData<Boolean> = _isMusicOn

    private val _isExiting = MutableLiveData(false)
    val isExiting: LiveData<Boolean> = _isExiting

    init {
        _isMusicOn.value = repository.settingsGetMusic()
        _isExiting.value = repository.settingsGetExitNotification()
    }
}