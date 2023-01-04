package client.response

import model.Movie

data class ImdbResponse(
    val items : List<MovieIMDB>,
    val errorMessage: String? = null
)

data class MovieIMDB(
    val title: String,
    val fullTitle: String,
    val year: String,
    val image: String,
    val imDbRating: String
)

fun MovieIMDB.toMovie(): Movie = Movie(
    titulo = this.title,
    nota = this.imDbRating.toDouble(),
    ano = this.year.toInt(),
    imagemUrl = this.image
)