package utils

import model.Alineacion
import model.Clasificacion
import model.Equipo
import model.Evento
import model.Liga
import model.Noticia
import model.Partido
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PreCargaDatos {

    // Función para obtener la fecha de hoy en el formato correcto
    private fun getFechaHoy(): String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
    }

    // --- LIGAS ---
    val LIGAS = listOf(
        Liga(1, "LaLiga EA Sports", "España", "https://media.laligafc.com/laliga-ea-sports/2023-24/horizontal-master-brand/LALIGA_EA_SPORTS_H_Master_Brand_RGB.png", false),
        Liga(2, "Premier League", "Inglaterra", "https://seeklogo.com/images/P/premier-league-new-logo-D22A921354-seeklogo.com.png", false),
        Liga(3, "Serie A", "Italia", "https://seeklogo.com/images/S/serie-a-logo-6A069E4B0A-seeklogo.com.png", false)
    )

    // --- EQUIPOS ---
    val EQUIPOS = listOf(
        // LaLiga
        Equipo(101, "Real Madrid", "httpsDE_REAL_MADRID_LOGO", "1", false),
        Equipo(102, "FC Barcelona", "url_barcelona_logo", "1", false),
        Equipo(103, "Atlético de Madrid", "url_atletico_logo", "1", false),
        Equipo(104, "Getafe CF", "url_getafe_logo", "1", false),
        // Premier
        Equipo(201, "Manchester City", "url_mancity_logo", "2", false),
        Equipo(202, "Liverpool", "url_liverpool_logo", "2", false),
        // Serie A
        Equipo(301, "Inter Milan", "url_inter_logo", "3", false)
    )

    // --- PARTIDOS ---
    // Incluimos un partido PARA HOY (para que la app no se vea vacía)
    val PARTIDOS = listOf(
        Partido(
            id = 1,
            ligaId = "1",
            equipoLocalId = 101,
            equipoVisitanteId = 102,
            nombreLocal = "Real Madrid",
            nombreVisitante = "FC Barcelona",
            escudoLocal = "url_real_madrid_logo",
            escudoVisitante = "url_barcelona_logo",
            marcadorLocal = null, // No se ha jugado
            marcadorVisitante = null,
            fecha = getFechaHoy(), // <-- ¡Importante!
            hora = "21:00"
        ),
        Partido(
            id = 2,
            ligaId = "1",
            equipoLocalId = 103,
            equipoVisitanteId = 104,
            nombreLocal = "Atlético de Madrid",
            nombreVisitante = "Getafe CF",
            escudoLocal = "url_atletico_logo",
            escudoVisitante = "url_getafe_logo",
            marcadorLocal = "2", // Partido jugado
            marcadorVisitante = "0",
            fecha = "15/11/2025", // Fecha pasada
            hora = "16:00"
        ),
        Partido(
            id = 3,
            ligaId = "2",
            equipoLocalId = 201,
            equipoVisitanteId = 202,
            nombreLocal = "Manchester City",
            nombreVisitante = "Liverpool",
            escudoLocal = "url_mancity_logo",
            escudoVisitante = "url_liverpool_logo",
            marcadorLocal = null,
            marcadorVisitante = null,
            fecha = getFechaHoy(), // <-- ¡Importante!
            hora = "17:30"
        )
    )

    // --- CLASIFICACIÓN (Ejemplo para LaLiga) ---
    val CLASIFICACION = listOf(
        Clasificacion("1", 101, "Real Madrid", "url_real_madrid_logo", 1, 30, 12, 9, 3, 0),
        Clasificacion("1", 102, "FC Barcelona", "url_barcelona_logo", 2, 28, 12, 9, 1, 2),
        Clasificacion("1", 103, "Atlético de Madrid", "url_atletico_logo", 3, 25, 12, 8, 1, 3),
        Clasificacion("1", 104, "Getafe CF", "url_getafe_logo", 4, 20, 12, 5, 5, 2)
    )

    // --- NOTICIAS ---
    val NOTICIAS = listOf(
        Noticia(1, "¡El Clásico calienta motores!", "El partido entre Real Madrid y FC Barcelona de hoy promete ser un espectáculo...", "Diario Sport", getFechaHoy(), "url_imagen_clasico"),
        Noticia(2, "El Atlético gana y se posiciona", "Con una victoria de 2-0 sobre el Getafe, los colchoneros suben al tercer puesto.", "Marca", "15/11/2025", "url_imagen_atletico")
    )

    // --- EVENTOS (Para el partido ID=2) ---
    val EVENTOS = listOf(
        Evento(partidoId = 2, minuto = 30, tipo = "Gol", jugadorId = 1, nombreJugador = "A. Griezmann"),
        Evento(partidoId = 2, minuto = 45, tipo = "Tarjeta Amarilla", jugadorId = 2, nombreJugador = "S. Ñíguez"),
        Evento(partidoId = 2, minuto = 75, tipo = "Gol", jugadorId = 1, nombreJugador = "A. Griezmann")
    )

    // --- ALINEACIONES (Para el partido ID=2) ---
    val ALINEACIONES = listOf(
        // Atlético
        Alineacion(2, 1, 103, "A. Griezmann", true, 7),
        Alineacion(2, 2, 103, "S. Ñíguez", true, 8),
        // Getafe
        Alineacion(2, 3, 104, "Borja Mayoral", true, 9)
    )
}