# Android On-Ramp

Este repositorio es un on-ramp, una nivelación práctica para llegar con lo mínimo al [curso de Android](https://github.com/ykro/android-course). Está pensado para estudiantes que ya programan (estructuras de datos, algoritmos, orientación a objetos, casi siempre en Java) pero no han usado Kotlin moderno ni una base de datos. Todo corre en tu computadora con Gradle y tests de JUnit, sin emulador y sin cuentas externas.

![Mapa de Android On-Ramp](docs/diagrams/overview.svg)

## Por dónde empezar

Si es tu primera vez con Kotlin, sigue los pasos del 0 al 6 en orden; el recorrido completo toma unas cinco horas, sin contar la instalación de Android Studio. Para la Unidad 1 bastan los pasos 0, 1, 2, 3 y 6, unas tres horas y media; haz los pasos 4 y 5 antes de la Unidad 2.

Cada paso sigue la misma forma. Empieza con tres preguntas para pensar antes de tocar código, explica por qué importa para el curso, te pide leer, debuguear y escribir algo pequeño, y cierra con preguntas sobre un caso nuevo. Si te trabas, cada paso tiene tres pistas que van de la idea general a un esqueleto de código.

| Paso | Tiempo | Qué practicas | Archivo | Guía |
| --- | --- | --- | --- | --- |
| Paso 0 | 40 min | Revisar JDK y Android SDK con `doctor`, y Kotlin básico (tipos nulos, `data class`, `filter`/`map`, `when`) | [PrimerosPasos.kt](paso0-entorno-y-kotlin/src/main/kotlin/onramp/paso0/PrimerosPasos.kt) | [steps/00-entorno-y-kotlin](steps/00-entorno-y-kotlin/README.md) |
| Paso 1 | 35 min | Lambdas con receptor (la sintaxis de Compose y Koin), `sealed interface` y `StateFlow` inmutable | [DslBuilders.kt](paso1-kotlin-y-stateflow/src/main/kotlin/onramp/paso1/DslBuilders.kt), [OrderState.kt](paso1-kotlin-y-stateflow/src/main/kotlin/onramp/paso1/OrderState.kt) | [steps/01-kotlin-y-stateflow](steps/01-kotlin-y-stateflow/README.md) |
| Paso 2 | 45 min | `suspend` y `delay` con reloj virtual, cancelación y tu primer `@Composable` con estado y test de UI | [CoroutinesAndUi.kt](paso2-corrutinas-y-ui/src/main/kotlin/onramp/paso2/CoroutinesAndUi.kt), [StudyCounterScreen.kt](paso2-corrutinas-y-ui/src/main/kotlin/onramp/paso2/StudyCounterScreen.kt) | [steps/02-corrutinas-y-ui](steps/02-corrutinas-y-ui/README.md) |
| Paso 3 | 75 min | UI declarativa, MVVM con `ViewModel`, `StateFlow` y `viewModelScope`, repositorio e inyección de dependencias con Koin | [ShelfViewModel.kt](paso3-mvvm-y-di/src/main/kotlin/onramp/paso3/ShelfViewModel.kt), [ShelfScreen.kt](paso3-mvvm-y-di/src/main/kotlin/onramp/paso3/ShelfScreen.kt), [ShelfModule.kt](paso3-mvvm-y-di/src/main/kotlin/onramp/paso3/ShelfModule.kt) | [steps/03-mvvm-y-di](steps/03-mvvm-y-di/README.md) |
| Paso 4 | 40 min | Requests con OkHttp, JSON tolerante a campos nuevos y errores HTTP o de parseo con `MockWebServer` | [BookApiClient.kt](paso4-http-json/src/main/kotlin/onramp/paso4/BookApiClient.kt) | [steps/04-http-json](steps/04-http-json/README.md) |
| Paso 5 | 45 min | Tu primera base de datos, tablas, llave primaria, `SELECT` y `ON CONFLICT DO UPDATE` en SQLite, y su equivalente en Room 3 | [BookDatabase.kt](paso5-sql-y-upsert/src/main/kotlin/onramp/paso5/BookDatabase.kt) | [steps/05-sql-y-upsert](steps/05-sql-y-upsert/README.md) |
| Paso 6 | 30 min | Tests desde cero con JUnit que se ponen en rojo ante dos versiones con un bug | [ReadingStreakStudentTest.kt](paso6-pruebas-desde-cero/src/test/kotlin/onramp/paso6/ReadingStreakStudentTest.kt) | [steps/06-pruebas-desde-cero](steps/06-pruebas-desde-cero/README.md) |

## Inicio rápido

1. Instala [Android Studio](https://developer.android.com/studio), que trae JDK 21 y el Android SDK, y clona este repositorio.
2. Corre `./gradlew doctor --no-parallel` (en Windows PowerShell, `.\gradlew.bat doctor --no-parallel`) y sigue lo que te pida.
3. Sigue el [Paso 0](steps/00-entorno-y-kotlin/README.md). `./gradlew :paso0-entorno-y-kotlin:run --no-parallel` corre tu primer programa en Kotlin.
4. Verifica cada paso con `./gradlew paso0 --no-parallel` hasta `./gradlew paso6 --no-parallel`. `./gradlew readyU1 --no-parallel` corre la ruta de la Unidad 1 (pasos 0, 1, 2, 3 y 6) y `./gradlew ready --no-parallel` corre los siete juntos.

Al inicio todos los pasos fallan; cada uno pasa cuando lo resuelves. Con la ruta de la Unidad 1, `./gradlew readyU1 --no-parallel` queda en verde mientras dejas los pasos 4 y 5 para antes de la Unidad 2.

## Errores comunes al preparar el entorno

| Lo que ves | Qué significa | Qué hacer |
| --- | --- | --- |
| `[FALTA] JDK detectado: 17...` | Gradle corre con un JDK viejo | Instala JDK 21 (viene con Android Studio) y apunta `JAVA_HOME` a él. En macOS con Android Studio: `export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"`. En Windows (PowerShell): `$env:JAVA_HOME = "C:\Program Files\Android\Android Studio\jbr"` |
| `[AVISO] No se encontró un Android SDK válido` | No hay SDK instalado o `ANDROID_HOME` apunta a otra carpeta | Los 7 pasos de este on-ramp corren en la JVM sin bloquearse. Antes de pasar a `android-course`, abre Android Studio, **Settings**, **Android SDK**, instala una plataforma y exporta `ANDROID_HOME` como indica `doctor` |
| `./gradlew: Permission denied` | El script perdió el permiso de ejecución al copiarse | `chmod +x gradlew` |
| La primera corrida tarda varios minutos | Gradle descarga su propia versión y las dependencias | Es normal la primera vez; las siguientes son rápidas |
| `Could not resolve ...` | No hay internet o un proxy bloquea Maven Central | Conéctate y vuelve a correr el comando; después de la primera descarga ya no hace falta red |

## Lecturas cortas y uso de IA

- El [glosario](docs/glosario.md) explica cada término con un enlace a la documentación oficial.
- [Cómo leer un ADR](docs/adr-ejemplo.md) te prepara para el `ARCHITECTURE_DECISIONS.md` del curso.
- [Qué es la Integración Continua (CI)](docs/ci.md) explica qué corre GitHub Actions cuando haces push.
- [Firebase en la Unidad 3](docs/firebase.md) adelanta Firebase Auth, Firestore con `callbackFlow` y `firestore.rules`.
- Puedes usar tu asistente de IA (Claude Code, Antigravity, Codex o Gemini CLI). [AGENTS.md](AGENTS.md) le pide que te guíe con preguntas y pistas antes de mostrarte código. Si aceptas código que no puedes explicar con tus palabras, el on-ramp pierde su sentido.

## Repositorios relacionados

- Índice de on-ramps: [ykro/courses-onramp](https://github.com/ykro/courses-onramp)
- Curso principal: [ykro/android-course](https://github.com/ykro/android-course)
- Solución de referencia, con las respuestas de autoevaluación de cada paso: [ykro/android-onramp-solution](https://github.com/ykro/android-onramp-solution)
