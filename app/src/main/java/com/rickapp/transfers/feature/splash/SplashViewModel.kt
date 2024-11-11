package com.rickapp.transfers.feature.splash

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _startNextScreen = MutableLiveData<Boolean>()
    val startNextScreen: LiveData<Boolean> = _startNextScreen

    private val _logoTypeSelected = MutableLiveData<LogoType>()
    val logoTypeSelected: LiveData<LogoType> = _logoTypeSelected

    private val _splashTimer = object : CountDownTimer(2000, 200) {
        override fun onTick(milliseconds: Long) {
            if (milliseconds <= 1200) {
                _logoTypeSelected.value = LogoType.WithColor
            }
        }

        override fun onFinish() {
            _startNextScreen.value = true
        }
    }.start()

}

enum class LogoType {
    WithColor, WithoutColor
}