package com.kinley.data.models

import com.google.gson.annotations.SerializedName

data class MoviesDataModel(
  val page: Int,
  @SerializedName("total_results")
  val totalResults: Int,
  @SerializedName("total_pages")
  val totalPages: Int,
  @SerializedName("results")
  val movies: List<Movie>
)

data class Movie(
  val adult: Boolean,
  val backdrop_path: String?,
  val budget: Long,
  val genres: List<Genre>,
  val id: Long,
  val overview: String?,
  val popularity: Double,
  val poster_path: String?,
  val production_companies: List<ProductionCompanies>?,
  val production_countries: List<ProductionCountry>?,
  val spoken_languages: List<SpokenLanguage>,
  // TODO Can be date
  val release_date: String,
  val revenue: Long? = 0,
  val runtime: Int?,
  val tagline: String,
  val title: String,
  val vote_count: Long,
  val vote_average: Double,
  // TODO Can be enum
  val original_language: String,

  val genre_ids: List<Int>
)

data class Genre(
  val id: Long,
  val name: String
)

data class ProductionCompanies(
  val description: String,
  val headquarters: String,
  val homepage: String?,
  val id: Int,
  val logo_path: String?,
  val name: String,
  val origin_country: String
)

data class ProductionCountry(
  val iso_3166_1: String,
  val name: String
)

data class SpokenLanguage(
  val iso_639_1: String,
  val name: String
)