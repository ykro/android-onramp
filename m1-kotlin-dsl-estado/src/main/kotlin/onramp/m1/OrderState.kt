package onramp.m1

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// --- Parte 3: Estados de pantalla con sealed interface ---

sealed interface OrderBannerState {
    data object Empty : OrderBannerState

    data class Preparing(val count: Int) : OrderBannerState

    data class Ready(val ticketCode: String) : OrderBannerState

    data class Failed(val reason: String) : OrderBannerState
}

/**
 * Ejercicio 3 (`sealed interface` + `when` sin `else`):
 * Devuelve el texto exacto para cada estado:
 * - Empty -> "Sin pedidos activos"
 * - Preparing -> "Preparando <count> pedidos" (o "Preparando 1 pedido" si count == 1)
 * - Ready -> "Pedido <ticketCode> listo"
 * - Failed -> "Error: <reason>"
 */
fun renderOrderBanner(state: OrderBannerState): String {
    TODO("Ejercicio 3: usa when (state) cubriendo las cuatro ramas sin else")
}

// --- Parte 4: Por qué una MutableList rompe StateFlow ---

// Error intencional: esta data class guarda una MutableList.
// Cámbiala por `List<String> = emptyList()` y actualiza el estado con `copy(...)`.
data class OrderBoardState(
    val orders: MutableList<String> = mutableListOf(),
)

/**
 * Ejercicio 4 (inmutabilidad en StateFlow):
 * Observa por qué la prueba falla: al mutar `orders.add(...)`, el estado anterior y el nuevo
 * apuntan a la misma lista en memoria, así que StateFlow cree que nada cambió y no avisa a la pantalla.
 *
 * Corrige `OrderBoardState` y esta clase usando una `List<String>` de solo lectura y `_state.update { ... }`.
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
