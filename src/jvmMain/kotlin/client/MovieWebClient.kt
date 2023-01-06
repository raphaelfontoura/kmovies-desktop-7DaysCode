package client

import androidx.compose.runtime.remember
import client.config.RetrofitInit
import client.response.ImdbResponse
import client.response.toMovie
import kotlinx.coroutines.flow.flow
import model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieWebClient {

    private val service = RetrofitInit().movieService

    fun findTop250Movies() = flow {
        val result = try {
            val movies = service.listTopMovies()
                .items.map { it.toMovie() }
            println("movies loaded $movies")
            Status.Success(movies)
        } catch (e: Exception) {
            println("exception loaded")
            Status.Error(e)
        }
        emit(result)
    }
}

sealed class Status<out R> {
    data class Success<out T>(val data: T) : Status<T>()
    data class Error(val exception: Exception) : Status<Nothing>()
    object Loading : Status<Nothing>()
}