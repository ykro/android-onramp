package onramp.paso1

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// --- Parte 2: Estados de pantalla con sealed interface ---

sealed interface OrderBannerState {
    data object Empty : OrderBannerState

    data class Preparing(val count: Int) : OrderBannerState

    data class Ready(val ticketCode: String) : OrderBannerState

    data class Failed(val reason: String) : OrderBannerState
}

/**
 * Ejercicio 2 (`sealed interface` + `when` sin `else`):
 * Devuelve el texto exacto para cada estado:
 * - Empty -> "Sin pedidos activos"
 * - Preparing -> "Preparando <count> pedidos" (o "Preparando 1 pedido" si count == 1)
 * - Ready -> "Pedido <ticketCode> listo"
 * - Failed -> "Error: <reason>"
 */
fun renderOrderBanner(state: OrderBannerState): String {
    TODO("Ejercicio 2: devuelve el texto de cada estado. Si mañana alguien agrega un estado nuevo, el compilador debe avisar aquí.")
}

// --- Parte 3: Por qué una MutableList rompe StateFlow ---

// Error intencional: esta data class guarda una lista que se modifica en el mismo lugar.
// Lee el Ejercicio 3 para ver qué provoca.
data class OrderBoardState(
    val orders: MutableList<String> = mutableListOf(),
)

/**
 * Ejercicio 3 (inmutabilidad en StateFlow):
 * Observa la prueba: agregas un pedido, la lista sí crece, pero la pantalla nunca se entera.
 * Explica por qué antes de cambiar el código.
 *
 * Corrige `OrderBoardState` y esta clase para que cada cambio produzca un estado nuevo en lugar de
 * modificar el anterior.
 */
class OrderBoard {
    private val _state = MutableStateFlow(OrderBoardState())
    val state: StateFlow<OrderBoardState> = _state.asStateFlow()

    fun addOrder(item: String) {
        val clean = item.trim()
        if (clean.isEmpty()) return
        val current = _state.value
        current.orders.add(clean)
        _state.value = current
    }

    fun removeFirstOrder() {
        val current = _state.value
        if (current.orders.isNotEmpty()) {
            current.orders.removeAt(0)
            _state.value = current
        }
    }
}
