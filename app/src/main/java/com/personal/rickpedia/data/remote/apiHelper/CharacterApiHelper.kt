package com.personal.rickpedia.data.remote.apiHelper

import com.personal.rickpedia.domain.character.AllCharacterResult
import com.personal.rickpedia.domain.character.Character

interface CharacterApiHelper {

    suspend fun fetchAllCharacters(page: Int): AllCharacterResult
    suspend fun fetchCharacter(id: Int): Character
}