package com.personal.rickpedia.domain.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterInfo (
    val count: Int,
    var pages: Int,
    var next: String?,
    var prev: String?
): Parcelable
