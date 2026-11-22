package onramp.m3

import kotlinx.serialization.Serializable
import okhttp3.OkHttpClient

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
}

/**
 * Ejercicio 1 (HTTP + JSON tolerante a campos extra):
 * - Haz GET a `"$baseUrl/books"`.
 * - Si `!response.isSuccessful`, devuelve `ApiResult.HttpError(response.code)`.
 * - Decodifica el JSON como `List<BookDto>` sin fallar cuando el API agrega campos que `BookDto` no conoce.
 * - Si el JSON está roto (`SerializationException`), devuelve `ApiResult.ParseError`.
 */
class BookApiClient(
    private val baseUrl: String,
    private val client: OkHttpClient = OkHttpClient(),
) {
    fun fetchBooks(): ApiResult<List<BookDto>> {
        TODO("Ejercicio 1: hoy no se hace ningún request. Cumple los cuatro puntos del comentario de arriba.")
    }
}
