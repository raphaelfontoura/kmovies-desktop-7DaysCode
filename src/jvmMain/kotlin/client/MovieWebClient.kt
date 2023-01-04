package client

import client.config.RetrofitInit
import client.response.ImdbResponse
import model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieWebClient {

    private val service = RetrofitInit().movieService

    val movies = mutableListOf<Movie>()

    init {
        findTop250Movies()
    }

    fun findTop250Movies() {
        service.listTopMovies().enqueue(object : Callback<ImdbResponse> {
            override fun onResponse(
                call: Call<ImdbResponse>,
                response: Response<ImdbResponse>
            ) {
                response.body().also {
                    it?.items?.forEach { movie ->
                        movies.add(Movie(
                            titulo = movie.title,
                            nota = movie.imDbRating.toDouble(),
                            ano = movie.year.toInt(),
                            imagemUrl = movie.image
                        ))

                    }
                }
            }
            override fun onFailure(
                call: Call<ImdbResponse>,
                t: Throwable
            ) {

            }
        })
    }

}