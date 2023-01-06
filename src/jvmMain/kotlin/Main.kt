import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import client.MovieWebClient
import client.Status
import ui.CenteredMessage
import ui.MoviesListScreen


@Composable
@Preview
fun App(content: @Composable () -> Unit) {

    MaterialTheme(
        colors = darkColors()
    ) {
        Surface {
            Box(modifier = Modifier.fillMaxWidth()) {
                content()
            }
        }

    }

}

fun main() = application {
    val client = MovieWebClient()

    val loadingMovieStatus = client
        .findTop250Movies()
        .collectAsState(Status.Loading)
        .value

    Window(
        onCloseRequest = ::exitApplication,
        title = "KMovies-IMDB"
    ) {
        App {
            when (loadingMovieStatus) {
                is Status.Success -> {
                    val movies = loadingMovieStatus.data
                    if (movies.isNotEmpty()) {
                        MoviesListScreen(movies)
                    } else {
                        CenteredMessage("Sem filmes")
                    }
                }

                is Status.Error -> {
                    val error = loadingMovieStatus.exception
                    var showSnackBar by remember {
                        mutableStateOf(true)
                    }
                    if (showSnackBar) {
                        Snackbar(
                            modifier = Modifier
                                .padding(8.dp),
                            action = {
                                Button(onClick = {
                                    showSnackBar = false
                                }) {
                                    Text("Close")
                                }
                            },
                        ) {
                            Text("Falha ao buscar filmes")
                            error.printStackTrace()
                        }
                    }
                }

                Status.Loading -> {
                    CenteredMessage("Carregando filmes...")
                }
            }
        }
    }
}



