package onramp.m0

/**
 * Paso 0 (~25 min): tu primer programa en Kotlin.
 *
 * Córrelo con `./gradlew :m0-doctor:run --no-parallel` y verifica con
 * `./gradlew paso0 --no-parallel`.
 */

data class Libro(val titulo: String, val paginas: Int)

val ESTANTE = listOf(
    Libro("Hombres de Maíz", 352),
    Libro("El Señor Presidente", 480),
    Libro("Leyendas de Guatemala", 112),
    Libro("Cien años de soledad", 496),
)

fun main() {
    println("Hola desde Kotlin")
    println(saludar("Ana"))
    println(saludar(null))
    println("Libros largos: ${librosLargos(ESTANTE)}")
    for (minutos in listOf(0, 15, 45)) {
        println("$minutos minutos -> ${nivelDeLectura(minutos)}")
    }
}

// TODO 1: Hoy saludar("Ana") devuelve "Hola, lector". Con un nombre debe devolver "Hola, Ana".
// Si el nombre es null o solo espacios, sí debe devolver "Hola, lector".
fun saludar(nombre: String?): String {
    return "Hola, lector"
}

// TODO 2: Hoy devuelve todos los títulos en el orden del estante. Debe devolver solo los
// títulos de libros con más de 300 páginas, en orden alfabético.
fun librosLargos(libros: List<Libro>): List<String> {
    val titulos = mutableListOf<String>()
    for (libro in libros) {
        titulos.add(libro.titulo)
    }
    return titulos
}

// TODO 3: Hoy todo es "corta". Con 0 o menos minutos es "sin lectura", de 1 a 29 es "corta"
// y de 30 en adelante es "larga".
fun nivelDeLectura(minutos: Int): String {
    return "corta"
}
