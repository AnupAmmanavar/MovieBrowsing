package com.kinley.data.models

data class MovieCredits(
    val cast: List<Cast>,
    val crew: List<Crew>
)

data class Cast(
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val id: Long,
    val name: String,
    val order: Int,
    val profile_path: String?
)

data class Crew(
    val department: String,
    val id: Long,
    val job: String,
    val name: String,
    val profile_path: String?
)