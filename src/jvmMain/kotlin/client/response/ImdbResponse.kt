package client.response

data class ImdbResponse(
    val items : List<MovieIMDB>
)

data class MovieIMDB(
    val title: String,
    val fullTitle: String,
    val year: String,
    val image: String,
    val imDbRating: String
)
