package com.personal.rickpedia.domain.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterOrigin (
    val name: String?,
    val url: String?
): Parcelable
