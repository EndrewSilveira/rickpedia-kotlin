package com.personal.rickpedia.data.remote.apiHelper

import com.personal.rickpedia.data.remote.api.CharacterApiFactory
import com.personal.rickpedia.domain.character.AllCharacterResult
import com.personal.rickpedia.domain.character.Character
import javax.inject.Inject

class CharacterApiHelperImpl @Inject constructor(
    private val characterApi: CharacterApiFactory
): CharacterApiHelper {

    override suspend fun fetchAllCharacters(page: Int): AllCharacterResult {
        return characterApi.fetchAllCharacters(page)
    }

    override suspend fun fetchCharacter(id: Int): Character {
        return characterApi.fetchCharacter(id)
    }
}