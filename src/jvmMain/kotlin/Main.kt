import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {

    val batmanMovie = Movie(
        titulo = "Batman: O Cavaleiro das Trevas",
        nota = 9.0,
        ano = 2008,
        imagem = "batman-cavaleiro-trevas.png"
    )

    with(batmanMovie) {
        MaterialTheme {
            Column {
                Text(titulo, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                Image(
                    painter = painterResource(imagem),
                    contentDescription = "capa $imagem"
                )
                Text("Nota: $nota - Ano: $ano")
            }
        }
    }
}

data class Movie(
    val titulo: String = "",
    val nota: Double = 0.0,
    val ano: Int = 0,
    val imagem: String = ""
)

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
