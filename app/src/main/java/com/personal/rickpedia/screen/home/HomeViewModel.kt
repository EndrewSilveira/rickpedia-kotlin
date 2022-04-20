package com.personal.rickpedia.screen.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.personal.rickpedia.base.BaseViewModel
import com.personal.rickpedia.data.domain.CharactersRepository
import com.personal.rickpedia.domain.character.Character
import com.personal.rickpedia.util.OnLoading
import com.personal.rickpedia.util.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositoryContract: CharactersRepository
) : BaseViewModel() {

    val allCharacters = SingleLiveData<PagingData<Character>>()

    fun fetchList(OnLoading: OnLoading?) {
        repositoryContract.fetchList(OnLoading)
            .cachedIn(viewModelScope)
            .observeForever { allCharacters.post(it) }
    }
}