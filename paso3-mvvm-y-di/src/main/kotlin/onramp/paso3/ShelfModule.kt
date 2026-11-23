package onramp.paso3

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Ejercicio 4 (Koin): el test le pide a Koin un `ShelfViewModel` y hoy Koin no sabe armarlo.
 *
 * Declara aquí cómo se construye cada pieza:
 * - Un solo `ReadingListRepository` para toda la app, en memoria y con `SEED_BOOKS`.
 * - Un solo `BookPicker`, el que elige al azar.
 * - El `ShelfViewModel`, que Koin arma con lo anterior.
 *
 * Aquí usas Koin, el mismo del curso.
 */
val shelfModule = module {
    // TODO Ejercicio 4
}
