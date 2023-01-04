package client.config

import client.service.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInit {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://imdb-api.com/API/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieService: MovieService
        get() = retrofit.create(MovieService::class.java)
}