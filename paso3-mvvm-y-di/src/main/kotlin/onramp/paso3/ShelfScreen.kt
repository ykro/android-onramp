package onramp.paso3

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

/**
 * La pantalla conectada: lee el estado del ViewModel y le pasa los eventos.
 * Este composable ya está bien escrito. En el curso verás `collectAsStateWithLifecycle()`,
 * que además deja de escuchar cuando la app pasa a segundo plano.
 */
@Composable
fun ShelfScreen(viewModel: ShelfViewModel) {
    val state by viewModel.uiState.collectAsState()
    ShelfContent(state = state, onEvent = viewModel::onEvent)
}

/**
 * Ejercicio 3 (UI declarativa): esta función describe la pantalla para un estado dado.
 *
 * Síntoma: cuando llega un estado con sugerencia, el contador de arriba cambia pero el texto
 * sigue diciendo «Sin sugerencia».
 *
 * Además falta:
 * - Que «Sugerir» esté deshabilitado cuando no hay pendientes.
 * - Un botón «Marcar como leído», visible solo cuando hay sugerencia, que avise con
 *   `ShelfEvent.MarkRead` del libro sugerido.
 */
@Composable
fun ShelfContent(state: ShelfUiState, onEvent: (ShelfEvent) -> Unit) {
    val suggestion by remember { mutableStateOf(state.suggestion) }

    Column {
        Text("Pendientes: ${state.pending.size} · Leídos: ${state.readCount}")
        Text(suggestion?.let { "Te toca: $it" } ?: "Sin sugerencia")
        Button(onClick = { onEvent(ShelfEvent.Suggest) }) {
            Text("Sugerir")
        }
        // TODO Ejercicio 3: botón «Marcar como leído».
    }
}
