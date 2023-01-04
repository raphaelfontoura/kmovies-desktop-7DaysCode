package client.service

import client.response.ImdbResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET("Top250Movies/api-key-here")
    fun listTopMovies() : Call<ImdbResponse>

}
