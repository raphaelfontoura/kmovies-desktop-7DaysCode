import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.skia.Image as ImageSkia
import java.net.URL


@Composable
@Preview
fun App() {

    val batmanMovie = Movie(
        titulo = "Batman: O Cavaleiro das Trevas",
        nota = 9.0,
        ano = 2008,
        imagem = "https://br.web.img3.acsta.net/c_310_420/medias/nmedia/18/86/98/32/19870786.jpg"
    )

    with(batmanMovie) {
        MaterialTheme {
            Column {
                Text(titulo, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                Image(
//                    painter = painterResource(imagem),
                    bitmap = imagem.loadImageBitmap(),
                    contentDescription = "capa $imagem"
                )
                Text("Nota: $nota - Ano: $ano")
            }
        }
    }
}

fun String.loadImageBitmap(): ImageBitmap {
    val url = URL(this)
    val openStream = url.openStream()
    val bytes = openStream.readAllBytes()
    return ImageSkia.makeFromEncoded(bytes).toComposeImageBitmap()
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
