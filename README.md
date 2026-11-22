# Android On-Ramp

Este repositorio es un **on-ramp** práctico y autoevaluable (60 minutos) para preparar tu entrada a [ykro/android-course](https://github.com/ykro/android-course). Reúne cinco pasos independientes que corren en tu computadora con pruebas rápidas en Kotlin, sin emulador ni conexión a internet. Si ya dominas los primeros temas, puedes empezar directo por el Paso 3, el Paso 4 o el Paso 5.

![Mapa de Android On-Ramp](docs/diagrams/overview.svg)

## Inicio rápido

1. Clona este repositorio en tu computadora o crea una copia desde GitHub.
2. Verifica que tu entorno tenga JDK 21 o superior y `ANDROID_HOME` configurado (en macOS: `export ANDROID_HOME="$HOME/Library/Android/sdk"`):

```bash
./gradlew doctor --no-parallel
```

3. Elige cualquier paso en `steps/` y ejecuta su verificación individual:

```bash
./gradlew paso1 --no-parallel
./gradlew paso2 --no-parallel
./gradlew paso3 --no-parallel
./gradlew paso4 --no-parallel
./gradlew paso5 --no-parallel
```

4. Al terminar los cinco pasos, ejecuta la verificación completa (`doctor` y `paso1` a `paso5`, 14 pruebas):

```bash
./gradlew ready --no-parallel
```

Cuando `./gradlew ready --no-parallel` termine en verde, estás listo para empezar [ykro/android-course](https://github.com/ykro/android-course).

## Los 5 pasos (independientes)

| Paso | Tiempo | Enfoque | Verificación | Guía |
| --- | --- | --- | --- | --- |
| Paso 1 | 15 min | Trailing lambdas con receptor, `sealed interface` y `StateFlow` inmutable | `./gradlew paso1 --no-parallel` | [steps/01-kotlin-y-stateflow/README.md](steps/01-kotlin-y-stateflow/README.md) |
| Paso 2 | 15 min | `suspend`/`delay` con reloj virtual, `CancellationException` y `remember` | `./gradlew paso2 --no-parallel` | [steps/02-corrutinas-y-ui/README.md](steps/02-corrutinas-y-ui/README.md) |
| Paso 3 | 10 min | `kotlinx.serialization`, `MockWebServer` y errores HTTP o de parseo | `./gradlew paso3 --no-parallel` | [steps/03-http-json/README.md](steps/03-http-json/README.md) |
| Paso 4 | 10 min | SQLite (`CREATE TABLE`, `SELECT/ORDER BY`, `ON CONFLICT DO UPDATE`) y Room 3 | `./gradlew paso4 --no-parallel` | [steps/04-sql-y-upsert/README.md](steps/04-sql-y-upsert/README.md) |
| Paso 5 | 10 min | Pruebas automatizadas desde cero con JUnit y Turbine (Arrange -> Act -> Assert) | `./gradlew paso5 --no-parallel` | [steps/05-pruebas-desde-cero/README.md](steps/05-pruebas-desde-cero/README.md) |

## Guías de lectura y uso de IA

- **Cómo leer un ADR:** revisa el ejemplo de 15 líneas para Android en [docs/adr-ejemplo.md](docs/adr-ejemplo.md) antes de abrir `ARCHITECTURE_DECISIONS.md` en el curso.
- **Qué es el CI (Integración Continua):** entiende qué corre GitHub Actions tras cada `git push` en [docs/ci.md](docs/ci.md).
- **Firebase en la Unidad 3:** repasa Firebase Auth, Firestore con `callbackFlow` y `firestore.rules` en [docs/firebase.md](docs/firebase.md).
- **Glosario técnico:** si algún término te resulta nuevo, consulta [docs/glosario.md](docs/glosario.md).
- **Uso de IA:** puedes usar cualquier asistente de IA mientras trabajas; [AGENTS.md](AGENTS.md) lo configura en **modo tutor socrático** para guiarte con pistas antes de mostrar la solución.
- **Otros enlaces:** índice general en [ykro/courses-onramp](https://github.com/ykro/courses-onramp), curso principal en [ykro/android-course](https://github.com/ykro/android-course) y [repositorio de soluciones](https://github.com/ykro/android-onramp-solution).
