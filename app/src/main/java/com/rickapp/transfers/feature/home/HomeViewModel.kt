package com.rickapp.transfers.feature.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickapp.transfers.feature.home.data.Character
import com.rickapp.transfers.feature.home.data.HomeService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeService: HomeService
) : ViewModel() {

    private val _characterList = mutableStateListOf<Character>()
    val characterList: List<Character> = _characterList

    init {
        viewModelScope.launch {
            val allCharacterResponse = homeService.getAllCharacter()
            if (allCharacterResponse.isSuccessful && allCharacterResponse.body() != null) {
                _characterList.addAll(allCharacterResponse.body()!!.results)
            }
        }
    }


    private val _characterSelected = MutableLiveData<Character?>(null)
    val characterSelected: LiveData<Character?> = _characterSelected

    fun changeCharacterSelected(character: Character) {
        _characterSelected.value = character
    }

    fun clearCharacterSelected() {
        _characterSelected.value = null
    }

    fun getRandomCharacter() {
        _characterSelected.value = _characterList.random()
    }

}