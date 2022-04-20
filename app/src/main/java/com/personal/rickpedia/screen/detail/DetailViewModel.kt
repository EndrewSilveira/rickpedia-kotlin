package com.personal.rickpedia.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.personal.rickpedia.base.BaseViewModel
import com.personal.rickpedia.data.domain.CharactersRepository
import com.personal.rickpedia.domain.character.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repositoryContract: CharactersRepository
): BaseViewModel() {

    val characterData: LiveData<Character?> = MutableLiveData()

    fun setData(id: Int?) {
        defaultLaunch {
            val character =  id?.let { repositoryContract.fetchCharacter(it) }
            characterData.post(character)
        }
    }
}