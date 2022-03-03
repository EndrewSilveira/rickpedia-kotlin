package com.personal.rickpedia.data.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.personal.rickpedia.data.remote.apiHelper.CharacterApiHelper
import com.personal.rickpedia.domain.character.Character
import com.personal.rickpedia.helper.pagingsource.CharacterPagingSource
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val characterApiHelper: CharacterApiHelper
): CharactersRepositoryContract {

    override fun fetchList(): MutableLiveData<PagingData<Character>> = Pager(config = PagingConfig(pageSize = 10)) {
        fetchAllCharacters()
    }.liveData as MutableLiveData<PagingData<Character>>

    override fun fetchAllCharacters(): PagingSource<Int, Character> {
        return CharacterPagingSource(characterApiHelper)
    }

    override suspend fun fetchCharacter(id: Int): Character {
        return characterApiHelper.fetchCharacter(id)
    }
}