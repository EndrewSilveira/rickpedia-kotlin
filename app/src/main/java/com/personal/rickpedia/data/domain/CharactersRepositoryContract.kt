package com.personal.rickpedia.data.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.personal.rickpedia.domain.character.Character

interface CharactersRepositoryContract {

    fun fetchList(): MutableLiveData<PagingData<Character>>
    fun fetchAllCharacters(): PagingSource<Int, Character>
    suspend fun fetchCharacter(id: Int): Character
}