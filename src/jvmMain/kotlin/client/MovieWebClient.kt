package client

import androidx.compose.runtime.remember
import client.config.RetrofitInit
import client.response.ImdbResponse
import client.response.toMovie
import model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieWebClient {

    private val service = RetrofitInit().movieService

    val movies = mutableListOf<Movie>()

    fun findTop250Movies() {
        service.listTopMovies().enqueue(object : Callback<ImdbResponse> {
            override fun onResponse(
                call: Call<ImdbResponse>,
                response: Response<ImdbResponse>
            ) {
                println(response)
                println(response.body()?.toString())
                response.body().also {
                    it?.items?.forEach { movie ->
                        movies.add(movie.toMovie())
                    }
                }.also { it ->
                    it?.items?.forEach { println(it) }
                }
            }
            override fun onFailure(
                call: Call<ImdbResponse>,
                t: Throwable
            ) {
                println(t)
            }
        })
    }

}