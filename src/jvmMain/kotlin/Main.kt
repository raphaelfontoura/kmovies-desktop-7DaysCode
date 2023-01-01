import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import extensions.loadImageBitmapV2
import model.Movie


@Composable
@Preview
fun App() {

    val batmanMovie = Movie(
        titulo = "Batman: O Cavaleiro das Trevas",
        nota = 9.0,
        ano = 2008,
        imagemUrl = "https://br.web.img3.acsta.net/c_310_420/medias/nmedia/18/86/98/32/19870786.jpg"
    )

    with(batmanMovie) {
        MaterialTheme(colors = darkColors()) {
            Column {
                Text(titulo, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                Image(
//                    painter = painterResource(imagemUrl),
                    bitmap = imagemUrl.loadImageBitmapV2(),
                    contentDescription = "capa $imagemUrl"
                )
                Text("Nota: $nota - Ano: $ano")
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
