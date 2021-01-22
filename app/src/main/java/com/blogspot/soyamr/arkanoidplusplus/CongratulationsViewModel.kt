package com.blogspot.soyamr.arkanoidplusplus

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class CongratulationsViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {
    private val _isMusicOn = MutableLiveData(false)
    val isMusicOn: LiveData<Boolean> = _isMusicOn

    init {
        _isMusicOn .value = repository.settingsGetMusic()
    }
}