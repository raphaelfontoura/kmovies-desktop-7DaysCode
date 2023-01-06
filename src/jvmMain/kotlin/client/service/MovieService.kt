package client.service

import client.config.IMDB_API_KEY
import client.response.ImdbResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET("Top250Movies/$IMDB_API_KEY")
    suspend fun listTopMovies() : ImdbResponse

}
