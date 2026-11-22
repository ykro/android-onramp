package onramp.paso2

import kotlinx.coroutines.flow.Flow

/**
 * Ejercicio 1 (`suspend` + `delay` + `Flow`):
 * Emite una cuenta regresiva desde `from` hasta `0` (inclusive).
 * Entre cada número espera `stepMs` milisegundos usando `delay`.
 * Si `from < 0`, no emite nada.
 */
fun countdownTicks(from: Int, stepMs: Long): Flow<Int> {
    TODO("Ejercicio 1: emite la cuenta regresiva sin bloquear el hilo. Los tests usan un reloj virtual que solo avanza con esperas suspend.")
}

/**
 * Ejercicio 2 (no atrapar `CancellationException`):
 * Ejecuta `action()`. Si lanza una excepción normal, devuelve `"Error: ${e.message ?: "desconocido"}"`.
 * Si la corrutina se cancela (`CancellationException`), debes relanzar esa excepción.
 */
suspend fun runSafely(action: suspend () -> String): String {
    TODO("Ejercicio 2: convierte los errores normales en texto, pero deja que la cancelación siga su camino.")
}
