package onramp.m4

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.fail
import org.junit.Test

/**
 * Paso 5 — Pruebas automatizadas desde cero con JUnit.
 *
 * Escribe las 3 pruebas siguiendo el patrón Arrange -> Act -> Assert.
 * Revisa la guía en `steps/05-pruebas-desde-cero/README.md` y ejecuta:
 *   ./gradlew paso5 --no-parallel
 */
class ReadingStreakStudentTest {

    @Test
    fun `CA-5-1 caso normal devuelve la racha consecutiva mas larga`() {
        // Arrange: arma una lista con dos rachas separadas por un día en 0, por ejemplo [15, 20, 0, 30, 25, 10]
        // Act: llama a computeReadingStreak(days)
        // Assert: verifica con assertEquals(3, streak)
        fail("Pendiente (CA-5.1): escribe la prueba del caso normal en ReadingStreakStudentTest.kt")
    }

    @Test
    fun `CA-5-2 caso borde devuelve cero para lista vacia y dias en cero`() {
        // Arrange & Act & Assert:
        // 1. Verifica que computeReadingStreak(emptyList()) devuelve 0
        // 2. Verifica que computeReadingStreak(listOf(0, 0, 0)) devuelve 0
        fail("Pendiente (CA-5.2): escribe la prueba de casos borde en ReadingStreakStudentTest.kt")
    }

    @Test
    fun `CA-5-3 prueba pasa con la version sana y falla contra computeReadingStreakBuggy`() {
        // Arrange: elige una lista donde un día en 0 separe dos bloques de lectura, por ejemplo [15, 0, 20, 30]
        // Act & Assert:
        // 1. Comprueba que computeReadingStreak(days) devuelve 2 (verde con código sano)
        // 2. Comprueba con assertThrows(AssertionError::class.java) que assertEquals(2, computeReadingStreakBuggy(days)) falla (rojo ante el bug)
        fail("Pendiente (CA-5.3): escribe la prueba que detecta el bug en computeReadingStreakBuggy")
    }
}
