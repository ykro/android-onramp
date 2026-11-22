package onramp.paso1

// --- Parte 1: Cómo funciona `Column { Text("Hola") }` ---

class ColumnScope {
    private val lines = mutableListOf<String>()

    fun text(value: String) {
        lines += value
    }

    internal fun buildLines(): List<String> = lines.toList()
}

/**
 * Ejercicio 1 (lambda con receptor):
 * Crea un `ColumnScope`, ejecuta `content` sobre ese receptor y devuelve las líneas acumuladas.
 */
fun miniColumn(content: ColumnScope.() -> Unit): List<String> {
    TODO("Ejercicio 1: hoy miniColumn no ejecuta el bloque que recibe. Debe correrlo sobre un ColumnScope nuevo y devolver las líneas que ese bloque agregó.")
}
