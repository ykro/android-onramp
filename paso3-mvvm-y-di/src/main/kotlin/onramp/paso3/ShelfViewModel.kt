package onramp.paso3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/** Todo lo que la pantalla necesita para dibujarse. La pantalla no guarda nada más. */
data class ShelfUiState(
    val pending: List<String> = emptyList(),
    val suggestion: String? = null,
    val readCount: Int = 0,
) {
    val canSuggest: Boolean get() = pending.isNotEmpty()
}

/** Lo que la persona puede hacer en la pantalla. La pantalla solo avisa; decide el ViewModel. */
sealed interface ShelfEvent {
    data object Suggest : ShelfEvent

    data class MarkRead(val title: String) : ShelfEvent
}

class ShelfViewModel(
    private val repository: ReadingListRepository,
    private val picker: BookPicker,
) : ViewModel() {
    /**
     * Ejercicio 2, parte A: hoy cualquier pantalla puede escribir en `uiState`
     * (por ejemplo `viewModel.uiState.value = ShelfUiState()`), y el test lo marca.
     * Solo el ViewModel debería poder cambiar el estado.
     */
    val uiState = MutableStateFlow(ShelfUiState(pending = repository.pending()))

    fun onEvent(event: ShelfEvent) {
        when (event) {
            ShelfEvent.Suggest -> suggest()
            is ShelfEvent.MarkRead -> markRead(event.title)
        }
    }

    /**
     * Ejercicio 1: el test le pasa al ViewModel un `BookPicker` que siempre elige el último
     * libro, y aun así la sugerencia cambia de una corrida a otra.
     *
     * Además, la sugerencia debe llegar después de `SUGGEST_DELAY_MS`, como en la U1:
     * espera con `delay` dentro de `viewModelScope.launch { }`, sin bloquear el hilo.
     */
    private fun suggest() {
        val options = uiState.value.pending
        if (options.isEmpty()) return
        val choice = options.random()
        uiState.update { it.copy(suggestion = choice) }
    }

    /**
     * Ejercicio 2, parte B: marcar un libro como leído.
     * - El repositorio se entera (`repository.markRead`).
     * - El estado nuevo ya no tiene ese libro en `pending` y suma 1 a `readCount`.
     * - Si ese libro era la sugerencia, la sugerencia queda en `null`.
     * - El estado anterior no cambia: produces uno nuevo.
     */
    private fun markRead(title: String) {
        TODO("Ejercicio 2: hoy marcar como leído no hace nada. Sigue los cuatro puntos del comentario.")
    }

    companion object {
        /** Lo que espera el ViewModel antes de mostrar la sugerencia. */
        const val SUGGEST_DELAY_MS = 500L
    }
}
