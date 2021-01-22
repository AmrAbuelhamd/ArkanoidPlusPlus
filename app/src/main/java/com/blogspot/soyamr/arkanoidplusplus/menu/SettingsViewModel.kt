package com.blogspot.soyamr.arkanoidplusplus.menu

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blogspot.soyamr.arkanoidplusplus.Repository

class SettingsViewModel @ViewModelInject constructor(val repository: Repository) : ViewModel() {


    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    val musicON: MutableLiveData<Boolean> = MutableLiveData(repository.settingsGetMusic())
    val soundON: MutableLiveData<Boolean> = MutableLiveData(repository.settingsGetSound())
    val touchON: MutableLiveData<Boolean> = MutableLiveData(repository.settingsGetTouch())

    fun save(touchON: Boolean) {
        _isLoading.value = true
        repository.settingsSetMusic(musicON.value!!)
        repository.settingsSetSound(soundON.value!!)
        repository.settingsSetTouch(touchON)
        _isLoading.value = true

    }
}