package com.personal.rickpedia.domain.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllCharacterResult (
    val info: CharacterInfo,
    val results: List<Character>
): Parcelable