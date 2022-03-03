package com.personal.rickpedia.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.personal.rickpedia.base.BaseViewModel
import com.personal.rickpedia.data.domain.CharactersRepositoryContract
import com.personal.rickpedia.domain.character.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositoryContract: CharactersRepositoryContract
): BaseViewModel() {

    val allCharacters: MutableLiveData<PagingData<Character>>
        get() = repositoryContract.fetchList().cachedIn(viewModelScope) as MutableLiveData<PagingData<Character>>

//    fun search(query: String) {
//        allCharacters.value = query
//    }
}