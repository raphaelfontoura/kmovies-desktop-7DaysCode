import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import client.MovieWebClient
import extensions.loadImageBitmapV2
import model.Movie


@Composable
@Preview
fun App(movies: MutableState<MutableList<Movie>>) {

    MaterialTheme(
        colors = darkColors()
    ) {
        Surface {
            Box(modifier = Modifier.fillMaxWidth()) {
                LazyColumn {
                    items(movies.component1()) { movie ->
                        movieItem(movie)
                    }
                }
            }
        }

    }

}

fun main() = application {
    val client = MovieWebClient()
    client.findTop250Movies()

    var movies = remember { mutableStateOf(client.movies) }

    Window(onCloseRequest = ::exitApplication) {
        App(movies)
    }
}

@Composable
fun movieItem(movie: Movie) = run {
    with(movie) {
        Column(
            modifier = Modifier.padding(16.dp).width(320.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                bitmap = imagemUrl.loadImageBitmapV2(),
                contentDescription = "capa $imagemUrl",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(320.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "star icon",
                        tint = Color.Yellow,
                        modifier = Modifier.height(16.dp)
                    )
                    Text(
                        "$nota",
                        modifier = Modifier.padding(start = 2.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text("$ano", fontSize = 14.sp)
            }
            Text(
                titulo,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 14.dp)
            )

        }
    }
}
