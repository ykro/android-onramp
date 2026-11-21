# Android On-Ramp

Este repositorio es un **on-ramp** práctico y autoevaluable (60 minutos) para preparar tu entrada a [ykro/android-course](https://github.com/ykro/android-course). Reúne cinco pasos independientes que corren en tu computadora con pruebas rápidas en Kotlin, sin emulador ni conexión a internet. Si ya dominas los primeros temas, puedes empezar directo por el Paso 3, el Paso 4 o el Paso 5.

![Mapa de Android On-Ramp](docs/diagrams/overview.svg)

## Los 5 pasos (independientes)

| Paso | Tiempo | Enfoque | Verificación | Guía |
| --- | --- | --- | --- | --- |
| Paso 1 | 10 min | Entorno: JDK 21 o superior y `ANDROID_HOME` | `./gradlew paso1 --no-parallel` | [steps/01-entorno/README.md](steps/01-entorno/README.md) |
| Paso 2 | 15 min | Trailing lambdas con receptor, `sealed interface` y `StateFlow` inmutable | `./gradlew paso2 --no-parallel` | [steps/02-kotlin-y-stateflow/README.md](steps/02-kotlin-y-stateflow/README.md) |
| Paso 3 | 15 min | `suspend`/`delay` con reloj virtual, `CancellationException` y `remember` | `./gradlew paso3 --no-parallel` | [steps/03-corrutinas-y-ui/README.md](steps/03-corrutinas-y-ui/README.md) |
| Paso 4 | 10 min | `kotlinx.serialization`, `MockWebServer` y errores HTTP o de parseo | `./gradlew paso4 --no-parallel` | [steps/04-http-json/README.md](steps/04-http-json/README.md) |
| Paso 5 | 10 min | SQLite (`CREATE TABLE`, `SELECT/ORDER BY`, `ON CONFLICT DO UPDATE`) y Room 3 | `./gradlew paso5 --no-parallel` | [steps/05-sql-y-upsert/README.md](steps/05-sql-y-upsert/README.md) |

## Inicio rápido

1. Clona este repositorio en tu computadora o crea una copia desde GitHub.
2. Verifica tu entorno con el Paso 1 (JDK 21 y `ANDROID_HOME`):

```bash
./gradlew paso1 --no-parallel
```

3. Elige cualquier paso en `steps/` y ejecuta su verificación individual:

```bash
./gradlew paso2 --no-parallel
./gradlew paso3 --no-parallel
./gradlew paso4 --no-parallel
./gradlew paso5 --no-parallel
```

4. Al terminar los cinco pasos, ejecuta la verificación completa (`paso1` a `paso5`, 11 pruebas):

```bash
./gradlew ready --no-parallel
```

Cuando `./gradlew ready --no-parallel` termine en verde, estás listo para empezar [ykro/android-course](https://github.com/ykro/android-course).

## Recursos y uso de IA

- Si algún término técnico te resulta nuevo, consulta el [glosario](docs/glosario.md).
- Puedes usar cualquier asistente de IA mientras trabajas: [AGENTS.md](AGENTS.md) lo configura en **modo tutor socrático** para guiarte con pistas antes de mostrar la solución.
- Índice general de todos los on-ramps: [ykro/courses-onramp](https://github.com/ykro/courses-onramp).
- Curso principal: [ykro/android-course](https://github.com/ykro/android-course).
- Si quieres comparar tu trabajo o revisar cada cambio explicado, consulta el [repositorio de soluciones](https://github.com/ykro/android-onramp-solution).
