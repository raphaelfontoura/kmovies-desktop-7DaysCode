package extensions

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.net.URL
import javax.imageio.ImageIO

fun String.loadImageBitmap(): ImageBitmap {
    val url = URL(this)
    val openStream = url.openStream()
    val bytes = openStream.readAllBytes()
    return Image.makeFromEncoded(bytes).toComposeImageBitmap()
}

// https://github.com/alura-cursos/SevenDaysOfCodeKotlin/commit/664613605b4597389f42bd0a06b01153bd9e4505?utm_source=ActiveCampaign&utm_medium=email&utm_content=%237DaysOfCode+-+Kotlin+3%2F7%3A+Personalizar+a+parte+visual&utm_campaign=%5BAlura+%237Days+Of+Code%5D%28Kotlin%29+Dia+3%2F7%3A+Personalizar+a+parte+visual
fun String.loadImageBitmapV2(): ImageBitmap {
    val inputStream = BufferedInputStream(URL(this).openStream())
    val bufferedImage = ImageIO.read(inputStream)
    val stream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "png", stream)
    val byteArray = stream.toByteArray()

    return Image.makeFromEncoded(byteArray).toComposeImageBitmap()
}