package com.kinley.moviebrowsing.models

import com.google.gson.annotations.SerializedName

data class Person(
    val id: Long,
    val name: String,
    val biography: String,
    @SerializedName("place_of_birth")
    val placeOfBirth: String,
    @SerializedName("profile_path")
    val profilePath: String?,
    val birthday: String,
    val deathDay: String?
)