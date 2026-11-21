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
        TODO("Ejercicio 2: ejecuta CREATE TABLE IF NOT EXISTS books (...)")
    }

    /**
     * Ejercicio 3 (upsert conservando columnas locales):
     * Error intencional: `INSERT OR REPLACE` borra la fila vieja e inserta una nueva,
     * así que reinicia `favorite` a 0 y `local_note` a vacío cada vez que sincronizas.
     *
     * Cambia el SQL a `INSERT INTO books (id, title, author) VALUES (?, ?, ?) ON CONFLICT(id) DO UPDATE SET ...`
     * para actualizar solo `title` y `author`.
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
        TODO("Ejercicio 4: ejecuta SELECT id, title, author, favorite, local_note FROM books WHERE favorite = 1 ORDER BY title ASC")
    }

    override fun close() {
        connection.close()
    }
}
