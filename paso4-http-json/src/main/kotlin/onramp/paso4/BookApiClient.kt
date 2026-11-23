package onramp.paso4

import java.io.IOException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request

@Serializable
data class BookDto(
    val id: Int,
    val title: String,
    val author: String,
)

sealed interface ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>

    data class HttpError(val code: Int) : ApiResult<Nothing>

    data object ParseError : ApiResult<Nothing>

    data object NetworkError : ApiResult<Nothing>
}

/**
 * Ejercicio 1 (HTTP + JSON tolerante a campos extra):
 * - Haz GET a `"$baseUrl/books"` dentro de `withContext(dispatcher)`, nunca en el hilo que llama.
 * - Si `!response.isSuccessful`, devuelve `ApiResult.HttpError(response.code)`.
 * - Decodifica el JSON como `List<BookDto>` sin fallar cuando el API agrega campos que `BookDto` no conoce.
 * - Si el JSON está roto (`SerializationException`), devuelve `ApiResult.ParseError`.
 * - Si el servidor no contesta (`IOException`), devuelve `ApiResult.NetworkError`.
 */
class BookApiClient(
    private val baseUrl: String,
    private val client: OkHttpClient = OkHttpClient(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    suspend fun fetchBooks(): ApiResult<List<BookDto>> {
        TODO("Ejercicio 1: hoy no se hace ningún request. Cumple los cinco puntos del comentario de arriba.")
    }
}
