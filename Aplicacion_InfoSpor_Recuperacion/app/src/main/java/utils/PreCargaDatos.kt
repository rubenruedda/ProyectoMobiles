package utils

import model.*

object PreCargaDatos {

    // --- 1. LIGAS ---
    val LIGAS = listOf(
        Liga("1", "LaLiga EA Sports", "España", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/LaLiga_logo_2023.svg/1200px-LaLiga_logo_2023.svg.png", true),
        Liga("2", "Premier League", "Inglaterra", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f2/Premier_League_Logo.svg/1200px-Premier_League_Logo.svg.png", false),
        Liga("3", "Serie A", "Italia", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Serie_A_logo_2022.svg/1200px-Serie_A_logo_2022.svg.png", false),
        Liga("4", "Bundesliga", "Alemania", "https://upload.wikimedia.org/wikipedia/en/thumb/d/df/Bundesliga_logo_%282017%29.svg/1200px-Bundesliga_logo_%282017%29.svg.png", false),
        Liga("5", "Ligue 1", "Francia", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Ligue_1_Uber_Eats_logo.svg/1200px-Ligue_1_Uber_Eats_logo.svg.png", false)
    )

    // --- 2. EQUIPOS (Una selección representativa) ---
    val EQUIPOS = listOf(
        // España
        Equipo(101, "Real Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", "1", true),
        Equipo(102, "FC Barcelona", "https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/1200px-FC_Barcelona_%28crest%29.svg.png", "1", true),
        Equipo(103, "Atlético de Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f4/Atletico_Madrid_2017_logo.svg/1200px-Atletico_Madrid_2017_logo.svg.png", "1", false),
        Equipo(104, "Sevilla FC", "https://upload.wikimedia.org/wikipedia/en/thumb/3/3b/Sevilla_FC_logo.svg/1200px-Sevilla_FC_logo.svg.png", "1", false),
        // Inglaterra
        Equipo(201, "Manchester City", "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Manchester_City_FC_badge.svg/1200px-Manchester_City_FC_badge.svg.png", "2", true),
        Equipo(202, "Liverpool", "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/1200px-Liverpool_FC.svg.png", "2", false),
        Equipo(203, "Arsenal", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Arsenal_FC.svg/1200px-Arsenal_FC.svg.png", "2", false),
        // Italia
        Equipo(301, "Juventus", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Juventus_FC_2017_icon_%28black%29.svg/1200px-Juventus_FC_2017_icon_%28black%29.svg.png", "3", false),
        Equipo(302, "AC Milan", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Logo_of_AC_Milan.svg/1200px-Logo_of_AC_Milan.svg.png", "3", true),
        // Alemania
        Equipo(401, "Bayern Munich", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/FC_Bayern_München_logo_%282017%29.svg/1200px-FC_Bayern_München_logo_%282017%29.svg.png", "4", false),
        Equipo(402, "Borussia Dortmund", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Borussia_Dortmund_logo.svg/1200px-Borussia_Dortmund_logo.svg.png", "4", true)
    )

    // --- 3. NOTICIAS ---
    val NOTICIAS = listOf(
        Noticia(1, "El Clásico paraliza el mundo", "Real Madrid y Barcelona se enfrentan en un duelo decisivo por el liderato de LaLiga. Las estrellas de ambos equipos llegan en plena forma...", "Marca", "20/11/2023", "https://e00-marca.uecdn.es/assets/multimedia/imagenes/2023/10/28/16985056976606.jpg"),
        Noticia(2, "Haaland rompe otro récord", "El delantero noruego del Manchester City ha superado la marca de goles en una sola temporada de la Premier League...", "BBC Sport", "19/11/2023", "https://e00-marca.uecdn.es/assets/multimedia/imagenes/2023/05/03/16831478799839.jpg"),
        Noticia(3, "Mbappé y su futuro incierto", "Los rumores sobre la salida de Kylian Mbappé del PSG vuelven a cobrar fuerza tras sus últimas declaraciones...", "L'Équipe", "18/11/2023", "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2023/06/13/16866536544062.jpg"),
        Noticia(4, "España se prepara para la Eurocopa", "La selección española ha comenzado su concentración en Las Rozas con la vista puesta en el próximo torneo continental...", "AS", "17/11/2023", "https://as01.epimg.net/futbol/imagenes/2023/09/08/seleccion/1694165682_936662_1694165977_noticia_normal.jpg"),
        Noticia(5, "Messi gana su octavo Balón de Oro", "El astro argentino hace historia una vez más al levantar el prestigioso galardón en París...", "France Football", "30/10/2023", "https://estaticos-cdn.prensaiberica.es/clip/2d690769-9596-4556-9720-63954d762e74_16-9-aspect-ratio_default_0.jpg")
    )

    // --- 4. PARTIDOS (Mezcla de pasados y futuros para probar todo) ---
    // NOTA: Usa la fecha actual de tu dispositivo para ver partidos en "Inicio".
    // Aquí pondremos partidos con fecha "HOY" simulada.

    val PARTIDOS = listOf(
        // -- Partidos de HOY (Simulados para que aparezcan en Inicio) --
        // Asegúrate de que la fecha coincida con la del sistema o cambia la lógica del ViewModel para pruebas.
        // Asumimos formato dd/MM/yyyy
        Partido(1, "1", 101, 102, "Real Madrid", "FC Barcelona", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/1200px-FC_Barcelona_%28crest%29.svg.png", null, null, "20/11/2025", "21:00"),
        Partido(2, "2", 201, 202, "Manchester City", "Liverpool", "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Manchester_City_FC_badge.svg/1200px-Manchester_City_FC_badge.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/1200px-Liverpool_FC.svg.png", "1", "1", "20/11/2025", "18:30"), // Jugándose

        // -- Partidos PASADOS (Resultados) --
        Partido(3, "1", 103, 104, "Atlético de Madrid", "Sevilla FC", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f4/Atletico_Madrid_2017_logo.svg/1200px-Atletico_Madrid_2017_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/3/3b/Sevilla_FC_logo.svg/1200px-Sevilla_FC_logo.svg.png", "2", "0", "10/11/2025", "16:15"),
        Partido(4, "3", 302, 301, "AC Milan", "Juventus", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Logo_of_AC_Milan.svg/1200px-Logo_of_AC_Milan.svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Juventus_FC_2017_icon_%28black%29.svg/1200px-Juventus_FC_2017_icon_%28black%29.svg.png", "1", "1", "12/11/2025", "20:45"),

        // -- Partidos FUTUROS (Próximos) --
        Partido(5, "4", 401, 402, "Bayern Munich", "Dortmund", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/FC_Bayern_München_logo_%282017%29.svg/1200px-FC_Bayern_München_logo_%282017%29.svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Borussia_Dortmund_logo.svg/1200px-Borussia_Dortmund_logo.svg.png", null, null, "25/12/2025", "18:30")
    )

    // --- 5. DETALLES (Eventos y Alineaciones para el partido ID=1 Real Madrid vs Barça) ---
    val EVENTOS = listOf(
        Evento(0, 1, 15, "Gol", 0, "Vinicius Jr."),
        Evento(0, 1, 32, "Tarjeta Amarilla", 0, "Gavi"),
        Evento(0, 1, 44, "Gol", 0, "Lewandowski")
    )

    val ALINEACIONES = listOf(
        Alineacion(1, 1, 101, "Courtois", true, 1),
        Alineacion(1, 2, 101, "Carvajal", true, 2),
        Alineacion(1, 3, 101, "Alaba", true, 4),
        Alineacion(1, 4, 101, "Modric", true, 10),
        Alineacion(1, 5, 101, "Vinicius Jr", true, 7),

        Alineacion(1, 6, 102, "Ter Stegen", true, 1),
        Alineacion(1, 7, 102, "Araujo", true, 4),
        Alineacion(1, 8, 102, "Pedri", true, 8),
        Alineacion(1, 9, 102, "Lewandowski", true, 9)
    )
}