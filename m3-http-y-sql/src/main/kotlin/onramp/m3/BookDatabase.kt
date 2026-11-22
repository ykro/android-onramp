package onramp.m3

import java.sql.Connection
import java.sql.DriverManager

data class StoredBook(
    val id: Int,
    val title: String,
    val author: String,
    val favorite: Boolean,
    val localNote: String,
)

/**
 * Base de datos SQLite en memoria para practicar SQL antes de usar Room 3.
 */
class BookDatabase(
    private val connection: Connection = DriverManager.getConnection("jdbc:sqlite::memory:"),
) : AutoCloseable {
    /**
     * Ejercicio 2 (`CREATE TABLE` + `PRIMARY KEY`):
     * Crea la tabla `books` con columnas:
     * - `id INTEGER PRIMARY KEY`
     * - `title TEXT NOT NULL`
     * - `author TEXT NOT NULL`
     * - `favorite INTEGER NOT NULL DEFAULT 0`
     * - `local_note TEXT NOT NULL DEFAULT ''`
     */
    fun createSchema() {
        TODO("Ejercicio 2: hoy la tabla no existe. Créala con las columnas de arriba, sin fallar si ya existía.")
    }

    /**
     * Ejercicio 3 (upsert conservando columnas locales):
     * Error intencional: después de sincronizar dos veces, los favoritos y las notas locales
     * desaparecen. Averigua qué hace este SQL cuando el `id` ya existe.
     *
     * Cambia el SQL para que, si el libro ya existe, solo se actualicen `title` y `author`.
     */
    fun syncFromApi(books: List<BookDto>) {
        val sql = "INSERT OR REPLACE INTO books (id, title, author) VALUES (?, ?, ?)"

        connection.prepareStatement(sql).use { statement ->
            for (book in books) {
                statement.setInt(1, book.id)
                statement.setString(2, book.title)
                statement.setString(3, book.author)
                statement.executeUpdate()
            }
        }
    }

    /**
     * Función ya implementada: guarda la marca de favorito y una nota personal en el dispositivo.
     */
    fun setFavoriteAndNote(id: Int, favorite: Boolean, note: String) {
        val sql = "UPDATE books SET favorite = ?, local_note = ? WHERE id = ?"
        connection.prepareStatement(sql).use { statement ->
            statement.setInt(1, if (favorite) 1 else 0)
            statement.setString(2, note)
            statement.setInt(3, id)
            statement.executeUpdate()
        }
    }

    /**
     * Ejercicio 4 (`SELECT ... WHERE ... ORDER BY`):
     * Devuelve los libros con `favorite = 1`, ordenados alfabéticamente por `title` ascendente.
     */
    fun findFavoritesOrderedByTitle(): List<StoredBook> {
        TODO("Ejercicio 4: devuelve los libros favoritos ordenados por título, con todas sus columnas.")
    }

    override fun close() {
        connection.close()
    }
}
