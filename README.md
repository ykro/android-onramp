# Android On-Ramp (60 minutos antes de `android-course`)

Este repositorio reúne cuatro módulos cortos para que practiques las bases de Kotlin, estado inmutable, corrutinas, HTTP y SQLite antes de empezar el curso principal. Todas las pruebas corren en tu computadora en pocos segundos y no necesitas emulador.

- Índice general de todos los on-ramps: [ykro/courses-onramp](https://github.com/ykro/courses-onramp)
- Curso principal: [ykro/android-course](https://github.com/ykro/android-course)
- Solución resuelta y explicada paso a paso: [ykro/android-onramp-solution](https://github.com/ykro/android-onramp-solution)

## Ruta sugerida (60 minutos)

| Módulo | Tiempo | Qué practicas |
| --- | --- | --- |
| [`m0-doctor`](m0-doctor/README.md) | 10 min | Verificar [JDK 21 / Temurin](https://adoptium.net/temurin/releases/?version=21) y la variable [`ANDROID_HOME`](https://developer.android.com/tools/variables#envar). |
| [`m1-kotlin-dsl-estado`](m1-kotlin-dsl-estado/README.md) | 15 min | [Trailing lambdas](https://kotlinlang.org/docs/lambdas.html#passing-trailing-lambdas), [function literals with receiver](https://kotlinlang.org/docs/lambdas.html#function-literals-with-receiver), [`sealed interface`](https://kotlinlang.org/docs/sealed-classes.html) con [`when`](https://kotlinlang.org/docs/control-flow.html#when-expressions-and-statements), [`data class`](https://kotlinlang.org/docs/data-classes.html) con [`copy()`](https://kotlinlang.org/docs/data-classes.html#copying) y [`StateFlow`](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow). |
| [`m2-corrutinas-y-ui`](m2-corrutinas-y-ui/README.md) | 15 min | [`suspend` y `delay`](https://kotlinlang.org/docs/coroutines-basics.html), [`CancellationException`](https://kotlinlang.org/docs/cancellation-and-timeouts.html), pruebas con [`runTest`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/kotlinx.coroutines.test/run-test.html) + [Turbine](https://github.com/cashapp/turbine) y estado entre redibujos como [`remember` en Jetpack Compose](https://developer.android.com/develop/ui/compose/state#state-in-composables). |
| [`m3-http-y-sql`](m3-http-y-sql/README.md) | 20 min | [`kotlinx.serialization` con `ignoreUnknownKeys`](https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/json.md#ignoring-unknown-keys), [OkHttp `MockWebServer`](https://github.com/square/okhttp/tree/master/mockwebserver), SQLite ([`CREATE TABLE` y `PRIMARY KEY`](https://www.sqlite.org/lang_createtable.html), [`SELECT ... WHERE ... ORDER BY`](https://www.sqlite.org/lang_select.html), [`ON CONFLICT DO UPDATE`](https://www.sqlite.org/lang_upsert.html)) y su puente hacia [Room](https://developer.android.com/training/data-storage/room). |

## Comandos principales

Desde la raíz del repositorio puedes correr:

```bash
# 1. Verifica tu entorno (JDK 21 y ANDROID_HOME)
./gradlew doctor --no-parallel

# 2. Corre un módulo a la vez mientras avanzas
./gradlew :m1-kotlin-dsl-estado:test --no-parallel
./gradlew :m2-corrutinas-y-ui:test --no-parallel
./gradlew :m3-http-y-sql:test --no-parallel

# 3. Verificación completa: corre doctor y las 11 pruebas
./gradlew ready --no-parallel
```

Cuando `./gradlew ready --no-parallel` termine en verde, estás listo para arrancar [ykro/android-course](https://github.com/ykro/android-course).

---

## Guía paso a paso (si en algún punto dices "no entiendo")

Avanza un módulo a la vez. Si un concepto te suena nuevo, abre el enlace oficial, lee la explicación corta y corre las pruebas del módulo para ver el error exacto.

### Módulo 0: `m0-doctor` — Verifica tu entorno

**Qué comprueba:** que tu computadora tenga instalado [JDK 21 (Eclipse Temurin)](https://adoptium.net/temurin/releases/?version=21) y que la variable de entorno [`ANDROID_HOME`](https://developer.android.com/tools/variables#envar) apunte a la carpeta del Android SDK.

**Paso a paso:**

1. Abre una terminal en la raíz del repositorio y corre `./gradlew doctor --no-parallel`.
2. Si aparece `[FALTA]` en JDK 21, instala [Eclipse Temurin 21](https://adoptium.net/temurin/releases/?version=21) (o `brew install openjdk@21` en macOS) y confirma con `java -version`.
3. Si aparece `[FALTA]` en `ANDROID_HOME`, instala una plataforma desde Android Studio (**Settings -> Languages & Frameworks -> Android SDK**) y define [`ANDROID_HOME`](https://developer.android.com/tools/variables#envar):
   - macOS: `export ANDROID_HOME="$HOME/Library/Android/sdk"`
   - Linux: `export ANDROID_HOME="$HOME/Android/Sdk"`
   - Windows (PowerShell): `$env:ANDROID_HOME = "$env:LOCALAPPDATA\Android\Sdk"`
4. Vuelve a correr `./gradlew doctor --no-parallel` hasta ver `[OK] Entorno listo para trabajar.`.

Consulta los detalles adicionales en [`m0-doctor/README.md`](m0-doctor/README.md).

---

### Módulo 1: `m1-kotlin-dsl-estado` — Bloques DSL, `sealed interface` y `StateFlow`

**Si dices "no entiendo por qué en Compose y Koin se escribe `Column { Text("Hola") }`":**

En Kotlin no hay trucos ocultos detrás de esa sintaxis. Se combinan dos características del lenguaje:

1. **[Trailing lambdas](https://kotlinlang.org/docs/lambdas.html#passing-trailing-lambdas):** cuando el último parámetro de una función es una lambda, puedes escribir las llaves fuera de los paréntesis (y omitir los paréntesis si no hay otros parámetros).
2. **[Function literals with receiver](https://kotlinlang.org/docs/lambdas.html#function-literals-with-receiver):** una función con tipo `ColumnScope.() -> Unit` recibe un objeto implícito (`this`) de tipo `ColumnScope`. Por eso dentro de `{ ... }` puedes llamar directamente a los métodos de `ColumnScope`, como `text(...)`.

**Si dices "no entiendo por qué mi `StateFlow` no avisa a la pantalla":**

[`StateFlow`](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) compara el estado anterior con el estado nuevo usando `==`. Si tu [`data class`](https://kotlinlang.org/docs/data-classes.html) guarda una `MutableList` y haces `orders.add(...)`, la referencia en memoria sigue siendo la misma lista. Al comparar el estado anterior con el nuevo, `StateFlow` ve que ambos son iguales y no emite ningún cambio. Para evitarlo, usa una `List` de solo lectura y crea un objeto nuevo con [`copy()`](https://kotlinlang.org/docs/data-classes.html#copying) dentro de `_state.update { ... }`.

**Paso a paso:**

1. Abre [`DslBuilders.kt`](m1-kotlin-dsl-estado/src/main/kotlin/onramp/m1/DslBuilders.kt).
   - En `miniColumn`, crea un `ColumnScope()`, ejecuta `scope.content()` y devuelve `scope.buildLines()`.
   - En `miniModule`, crea un `ModuleScope()`, ejecuta `scope.definitions()` y devuelve `scope.buildContainer()`.
2. Abre [`OrderState.kt`](m1-kotlin-dsl-estado/src/main/kotlin/onramp/m1/OrderState.kt).
   - En `renderOrderBanner`, usa [`when`](https://kotlinlang.org/docs/control-flow.html#when-expressions-and-statements) sobre la [`sealed interface`](https://kotlinlang.org/docs/sealed-classes.html) `OrderBannerState` cubriendo las cuatro ramas (`Empty`, `Preparing`, `Ready`, `Failed`) sin `else`.
   - En `OrderBoardState`, cambia `MutableList<String>` por `List<String> = emptyList()`.
   - En `OrderBoard`, actualiza `_state` con `_state.update { current -> current.copy(...) }` usando `current.orders + clean` para agregar y `current.orders.drop(1)` para quitar el primero.
3. Corre las 4 pruebas del módulo:

```bash
./gradlew :m1-kotlin-dsl-estado:test --no-parallel
```

Revisa [`m1-kotlin-dsl-estado/README.md`](m1-kotlin-dsl-estado/README.md) y [`Module1Test.kt`](m1-kotlin-dsl-estado/src/test/kotlin/onramp/m1/Module1Test.kt) para ver los casos exactos.

---

### Módulo 2: `m2-corrutinas-y-ui` — Corrutinas, cancelación y memoria entre redibujos

**Si dices "no entiendo la diferencia entre `delay` y `Thread.sleep`":**

`Thread.sleep` congela el hilo completo (en Android, la pantalla deja de responder). En cambio, [`suspend` y `delay`](https://kotlinlang.org/docs/coroutines-basics.html) pausan solo la corrutina y dejan el hilo libre. Además, en pruebas con [`runTest`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/kotlinx.coroutines.test/run-test.html) y [Turbine](https://github.com/cashapp/turbine), el reloj virtual adelanta `delay(10_000L)` al instante y la prueba termina en milisegundos.

**Si dices "no entiendo por qué no debo atrapar `CancellationException`":**

Cuando un usuario cierra una pantalla, Android cancela las corrutinas asociadas lanzando [`CancellationException`](https://kotlinlang.org/docs/cancellation-and-timeouts.html). Si escribes `catch (e: Exception)` y devuelves un texto de error sin relanzar `CancellationException`, la corrutina ignora la orden de cancelarse y sigue consumiendo recursos.

**Si dices "no entiendo por qué mi contador vuelve a 0 en cada redibujo":**

En Jetpack Compose, las funciones de pantalla se vuelven a llamar cada vez que cambia un dato. Si declaras `var count = 0` dentro de la función, el valor regresa a `0` en cada llamada. Por eso existe [`remember` en Jetpack Compose](https://developer.android.com/develop/ui/compose/state#state-in-composables), que guarda el dato fuera de la llamada local para conservarlo entre redibujos. En este módulo practicas esa misma idea con `UiMemory.remember(...)`.

**Paso a paso:**

1. Abre [`CoroutinesAndUi.kt`](m2-corrutinas-y-ui/src/main/kotlin/onramp/m2/CoroutinesAndUi.kt).
2. En `countdownTicks`: devuelve `flow { ... }`. Si `from < 0`, sal de inmediato. Si no, recorre `for (current in from downTo 0)`, llama `emit(current)` y espera con `delay(stepMs)` cuando `current > 0`.
3. En `runSafely`: envuelve `action()` en `try`, agrega primero `catch (cancelled: CancellationException) { throw cancelled }` y después `catch (error: Exception) { "Error: ${error.message ?: "desconocido"}" }`.
4. En `renderStudyCounter`: reemplaza `var count = 0` por `val sessions = memory.remember("sessions") { 0 }`, lee `sessions.value` para armar `label` y modifica `sessions.value` en `onIncrement` y `onReset`.
5. Corre las 3 pruebas del módulo:

```bash
./gradlew :m2-corrutinas-y-ui:test --no-parallel
```

Revisa [`m2-corrutinas-y-ui/README.md`](m2-corrutinas-y-ui/README.md) y [`Module2Test.kt`](m2-corrutinas-y-ui/src/test/kotlin/onramp/m2/Module2Test.kt) para verificar cada expectativa.

---

### Módulo 3: `m3-http-y-sql` — HTTP, JSON tolerante y SQLite hacia Room

**Si dices "no entiendo por qué falla mi JSON si el servidor envía campos extra":**

Por defecto, `kotlinx.serialization` falla cuando el JSON trae propiedades que tu `data class` no declara. Al configurar [`Json { ignoreUnknownKeys = true }`](https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/json.md#ignoring-unknown-keys), el lector ignora los campos adicionales sin romperse. En las pruebas usamos [OkHttp `MockWebServer`](https://github.com/square/okhttp/tree/master/mockwebserver) para simular respuestas HTTP `200`, errores `500` y cuerpos con JSON dañado sin depender de internet.

**Si dices "no entiendo por qué `INSERT OR REPLACE` borró mis favoritos en SQLite":**

En SQLite, `INSERT OR REPLACE` elimina la fila existente e inserta una fila nueva con los valores por defecto (`favorite = 0`, `local_note = ''`). Para actualizar solo las columnas remotas (`title`, `author`) y conservar lo que el usuario guardó en su dispositivo, usa [`ON CONFLICT(id) DO UPDATE SET`](https://www.sqlite.org/lang_upsert.html). Estos mismos conceptos de [`CREATE TABLE` con `PRIMARY KEY`](https://www.sqlite.org/lang_createtable.html), [`SELECT ... WHERE ... ORDER BY`](https://www.sqlite.org/lang_select.html) y actualización parcial son la base directa de [Room en Android](https://developer.android.com/training/data-storage/room).

**Paso a paso:**

1. Abre [`BookApiClient.kt`](m3-http-y-sql/src/main/kotlin/onramp/m3/BookApiClient.kt).
   - Crea una instancia de `Json { ignoreUnknownKeys = true }`.
   - En `fetchBooks()`, construye la petición GET hacia `"${baseUrl.trimEnd('/')}/books"` con OkHttp.
   - Si `!response.isSuccessful`, devuelve `ApiResult.HttpError(response.code)`.
   - Decodifica `response.body.string()` con `json.decodeFromString<List<BookDto>>(body)` dentro de `try/catch (SerializationException)` para devolver `ApiResult.Success(books)` o `ApiResult.ParseError`.
2. Abre [`BookDatabase.kt`](m3-http-y-sql/src/main/kotlin/onramp/m3/BookDatabase.kt).
   - En `createSchema()`, ejecuta [`CREATE TABLE IF NOT EXISTS books`](https://www.sqlite.org/lang_createtable.html) con `id INTEGER PRIMARY KEY`, `title TEXT NOT NULL`, `author TEXT NOT NULL`, `favorite INTEGER NOT NULL DEFAULT 0` y `local_note TEXT NOT NULL DEFAULT ''`.
   - En `syncFromApi()`, reemplaza `INSERT OR REPLACE` por `INSERT INTO books (id, title, author) VALUES (?, ?, ?) ON CONFLICT(id) DO UPDATE SET title = excluded.title, author = excluded.author`.
   - En `findFavoritesOrderedByTitle()`, ejecuta `SELECT id, title, author, favorite, local_note FROM books WHERE favorite = 1 ORDER BY title ASC` y mapea cada fila a `StoredBook`.
3. Corre las 4 pruebas del módulo:

```bash
./gradlew :m3-http-y-sql:test --no-parallel
```

Revisa [`m3-http-y-sql/README.md`](m3-http-y-sql/README.md) y [`Module3Test.kt`](m3-http-y-sql/src/test/kotlin/onramp/m3/Module3Test.kt) si necesitas más contexto.

---

## Uso de asistentes de IA

Puedes usar cualquier asistente de IA mientras trabajas en este repositorio. El archivo [`AGENTS.md`](AGENTS.md) lo configura en **modo tutor socrático**: te explica los conceptos con ejemplos simples, te da pistas concretas y te guía paso a paso cuando tienes dudas. Pregúntale por qué falla una prueba o pide una pista antes de consultar [ykro/android-onramp-solution](https://github.com/ykro/android-onramp-solution).
