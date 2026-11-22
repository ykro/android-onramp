package onramp.m2

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Ejercicio 4 (Compose de verdad):
 * La misma pantalla del ejercicio 3, ahora como `@Composable`. Compose vuelve a llamar
 * esta función cada vez que cambia el estado que lee.
 *
 * Tiene un bug: al tocar "Sumar sesión", el texto sigue en "0 sesiones completadas".
 * Corrígelo y agrega un segundo botón con el texto "Reiniciar" que regrese el contador a 0.
 *
 * Texto esperado:
 * - 0 -> "0 sesiones completadas"
 * - 1 -> "1 sesión completada"
 * - N -> "$N sesiones completadas"
 */
@Composable
fun StudyCounterScreen() {
    var count by mutableStateOf(0)
    val label = if (count == 1) "1 sesión completada" else "$count sesiones completadas"

    Column {
        Text(label)
        Button(onClick = { count += 1 }) {
            Text("Sumar sesión")
        }
        // TODO Ejercicio 4: agrega aquí el botón "Reiniciar".
    }
}
