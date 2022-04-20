package com.personal.rickpedia.data.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.personal.rickpedia.data.remote.apiHelper.CharacterApiHelper
import com.personal.rickpedia.domain.character.Character
import com.personal.rickpedia.helper.pagingsource.CharacterPagingSource
import com.personal.rickpedia.util.OnLoading
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val characterApiHelper: CharacterApiHelper
){

    fun fetchList(loading: OnLoading?): LiveData<PagingData<Character>> =
        Pager(
            config = PagingConfig(
                pageSize = CharacterPagingSource.DEFAULT_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { CharacterPagingSource(characterApiHelper, loading) }
        ).liveData

    suspend fun fetchCharacter(id: Int): Character {
        return characterApiHelper.fetchCharacter(id)
    }
}