package com.kinley.data.models

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

data class PersonCastResponseModel(
    @SerializedName("cast")
    val personCast: List<PersonCast>
)

data class PersonCast(
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val id: Long,
    val name: String? = null,
    val title: String? = null,
    val order: Int? = 0,
    val poster_path: String? = null
)