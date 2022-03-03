package com.personal.rickpedia.helper.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.personal.rickpedia.data.remote.apiHelper.CharacterApiHelper
import com.personal.rickpedia.domain.character.AllCharacterResult
import com.personal.rickpedia.domain.character.Character
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class CharacterPagingSource(
    private val characterCharacterApiHelper: CharacterApiHelper
): PagingSource<Int, Character>() {

    companion object {
        private const val INITIAL_PAGE_INDEX = 1
        private const val DEFAULT_PAGE_SIZE = 10
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int {
        return INITIAL_PAGE_INDEX
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        try {
            val nextPageNumber = params.key ?: 1
            val allCharactersResponse = getAllCharacters(nextPageNumber, DEFAULT_PAGE_SIZE)
            val characters = allCharactersResponse?.results ?: emptyList()

            val nextKey = if (allCharactersResponse == null) {
                null
            } else {
                nextPageNumber + INITIAL_PAGE_INDEX
            }

            return LoadResult.Page(
                data = characters,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }

    private suspend fun getAllCharacters(nextPageNumber: Int, loadSize: Int): AllCharacterResult? {
        var response: AllCharacterResult? = null
        coroutineScope {
            val characterResponse = async {
                response = characterCharacterApiHelper.fetchAllCharacters(nextPageNumber)
            }
            characterResponse.await()
        }
        return response
    }
}