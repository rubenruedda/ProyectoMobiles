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
        Equipo(105, "Real Sociedad", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f1/Real_Sociedad_logo.svg/1200px-Real_Sociedad_logo.svg.png", "1", false),
        Equipo(106, "Villarreal CF", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/Villarreal_CF_logo.svg/1200px-Villarreal_CF_logo.svg.png", "1", false),
        // Inglaterra
        Equipo(201, "Manchester City", "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Manchester_City_FC_badge.svg/1200px-Manchester_City_FC_badge.svg.png", "2", true),
        Equipo(202, "Liverpool", "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/1200px-Liverpool_FC.svg.png", "2", false),
        Equipo(203, "Arsenal", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Arsenal_FC.svg/1200px-Arsenal_FC.svg.png", "2", false),
        Equipo(204, "Chelsea", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/Chelsea_FC.svg/1200px-Chelsea_FC.svg.png", "2", false),
        Equipo(205, "Manchester United", "https://upload.wikimedia.org/wikipedia/en/thumb/7/7a/Manchester_United_FC_crest.svg/1200px-Manchester_United_FC_crest.svg.png", "2", false),
        // Italia
        Equipo(301, "Juventus", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Juventus_FC_2017_icon_%28black%29.svg/1200px-Juventus_FC_2017_icon_%28black%29.svg.png", "3", false),
        Equipo(302, "AC Milan", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Logo_of_AC_Milan.svg/1200px-Logo_of_AC_Milan.svg.png", "3", true),
        Equipo(303, "Inter de Milán", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/FC_Internazionale_Milano_2021.svg/1200px-FC_Internazionale_Milano_2021.svg.png", "3", false),
        Equipo(304, "AS Roma", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f7/AS_Roma_logo_%282017%29.svg/1200px-AS_Roma_logo_%282017%29.svg.png", "3", false),
        // Alemania
        Equipo(401, "Bayern Munich", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg/1200px-FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg.png", "4", false),
        Equipo(402, "Borussia Dortmund", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Borussia_Dortmund_logo.svg/1200px-Borussia_Dortmund_logo.svg.png", "4", true),
        Equipo(403, "RB Leipzig", "https://upload.wikimedia.org/wikipedia/en/thumb/0/04/RB_Leipzig_2014_logo.svg/1200px-RB_Leipzig_2014_logo.svg.png", "4", false),
        Equipo(404, "Bayer Leverkusen", "https://upload.wikimedia.org/wikipedia/en/thumb/5/59/Bayer_04_Leverkusen_logo.svg/1200px-Bayer_04_Leverkusen_logo.svg.png", "4", false),
        // Francia
        Equipo(501, "PSG", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a7/Paris_Saint-Germain_F.C..svg/1200px-Paris_Saint-Germain_F.C..svg.png", "5", true),
        Equipo(502, "Olympique de Marsella", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Olympique_Marseille_logo.svg/1200px-Olympique_Marseille_logo.svg.png", "5", false),
        Equipo(503, "Mónaco", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cf/AS_Monaco_FC.svg/1200px-AS_Monaco_FC.svg.png", "5", false),
        Equipo(504, "Lyon", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a5/Olympique_Lyonnais.svg/1200px-Olympique_Lyonnais.svg.png", "5", false)
    )

    // --- 3. NOTICIAS (con URLs de imágenes que funcionan) ---
    val NOTICIAS = listOf(
        Noticia(1, "El Clásico paraliza el mundo", "Real Madrid y Barcelona se enfrentan en un duelo decisivo por el liderato de LaLiga. Las estrellas de ambos equipos llegan en plena forma. Vinicius Jr. ha sido el jugador más destacado del Real Madrid esta temporada, mientras que Lewandowski lidera la tabla de goleadores del Barcelona. El estadio Santiago Bernabéu será el escenario de este encuentro que promete emociones fuertes.", "Marca", "16/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/b8wbxp9i3afuqr8sxm4i"),
        Noticia(2, "Haaland rompe otro récord", "El delantero noruego del Manchester City ha superado la marca de goles en una sola temporada de la Premier League. Con 35 goles en 28 partidos, Erling Haaland continúa demostrando por qué es considerado el mejor delantero del mundo.", "BBC Sport", "15/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/c9iswfyfyauo1xjzvcx9"),
        Noticia(3, "Mbappé triunfa en Madrid", "Kylian Mbappé se ha adaptado perfectamente al Real Madrid y ya es el máximo goleador del equipo esta temporada. El francés ha formado una conexión letal con Vinicius Jr. y Bellingham.", "L'Équipe", "14/01/2026", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/2019-07-17_SG_Dynamo_Dresden_vs._Paris_Saint-Germain_by_Sandro_Halank%E2%80%93129_%28cropped%29.jpg/440px-2019-07-17_SG_Dynamo_Dresden_vs._Paris_Saint-Germain_by_Sandro_Halank%E2%80%93129_%28cropped%29.jpg"),
        Noticia(4, "España se prepara para el Mundial", "La selección española ha comenzado su concentración en Las Rozas con la vista puesta en el próximo Mundial. Luis de la Fuente ha convocado a 26 jugadores para los amistosos preparatorios.", "AS", "13/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/nqeevzoxwvnpsxhxvpxf"),
        Noticia(5, "Messi sigue haciendo historia", "Leo Messi continúa brillando en el Inter Miami y ha llevado al equipo a su segundo título consecutivo de la MLS. A sus 38 años, el argentino sigue demostrando que es uno de los mejores jugadores de todos los tiempos.", "ESPN", "12/01/2026", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Lionel-Messi-Argentina-2022-FIFA-World-Cup_%28cropped%29.jpg/440px-Lionel-Messi-Argentina-2022-FIFA-World-Cup_%28cropped%29.jpg"),
        Noticia(6, "Champions League: los favoritos", "Con la fase de grupos finalizada, analizamos qué equipos tienen más opciones de levantar la orejona. Real Madrid, Manchester City y Bayern Munich encabezan las apuestas.", "UEFA", "11/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/xvvvw87jhxuphvvdyefy"),
        Noticia(7, "Bellingham: el jugador del momento", "Jude Bellingham ha sido elegido mejor jugador del mes en LaLiga por tercera vez consecutiva. El inglés es fundamental en el esquema de Ancelotti con números espectaculares.", "Goal", "10/01/2026", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Jude_Bellingham_2023.jpg/440px-Jude_Bellingham_2023.jpg"),
        Noticia(8, "El VAR: polémica en el fútbol", "El uso del VAR sigue generando debate. En la última jornada hubo varias decisiones controvertidas que afectaron el resultado de partidos importantes.", "Sport", "09/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/tvx2fxq0xmtzc9y8x4wa")
    )

    // --- 4. PARTIDOS (Mezcla de pasados y futuros para todas las ligas) ---
    // Fecha actual: 16/01/2026
    val PARTIDOS = listOf(
        // === LALIGA (Liga 1) ===
        // Partidos de HOY
        Partido(1, "1", 101, 102, "Real Madrid", "FC Barcelona", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/1200px-FC_Barcelona_%28crest%29.svg.png", null, null, "16/01/2026", "21:00"),
        Partido(2, "1", 103, 104, "Atlético de Madrid", "Sevilla FC", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f4/Atletico_Madrid_2017_logo.svg/1200px-Atletico_Madrid_2017_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/3/3b/Sevilla_FC_logo.svg/1200px-Sevilla_FC_logo.svg.png", null, null, "16/01/2026", "18:30"),
        // Partidos PASADOS LaLiga
        Partido(3, "1", 102, 101, "FC Barcelona", "Real Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/1200px-FC_Barcelona_%28crest%29.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", "2", "3", "10/01/2026", "21:00"),
        Partido(4, "1", 105, 106, "Real Sociedad", "Villarreal CF", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f1/Real_Sociedad_logo.svg/1200px-Real_Sociedad_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/Villarreal_CF_logo.svg/1200px-Villarreal_CF_logo.svg.png", "1", "1", "08/01/2026", "20:00"),
        Partido(5, "1", 104, 103, "Sevilla FC", "Atlético de Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/3/3b/Sevilla_FC_logo.svg/1200px-Sevilla_FC_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f4/Atletico_Madrid_2017_logo.svg/1200px-Atletico_Madrid_2017_logo.svg.png", "0", "2", "05/01/2026", "16:15"),
        // Partidos FUTUROS LaLiga
        Partido(6, "1", 106, 101, "Villarreal CF", "Real Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/Villarreal_CF_logo.svg/1200px-Villarreal_CF_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", null, null, "20/01/2026", "21:00"),
        
        // === PREMIER LEAGUE (Liga 2) ===
        // Partido de HOY
        Partido(7, "2", 201, 202, "Manchester City", "Liverpool", "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Manchester_City_FC_badge.svg/1200px-Manchester_City_FC_badge.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/1200px-Liverpool_FC.svg.png", null, null, "16/01/2026", "17:30"),
        // Partidos PASADOS Premier
        Partido(8, "2", 203, 204, "Arsenal", "Chelsea", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Arsenal_FC.svg/1200px-Arsenal_FC.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/Chelsea_FC.svg/1200px-Chelsea_FC.svg.png", "2", "1", "12/01/2026", "16:00"),
        Partido(9, "2", 202, 205, "Liverpool", "Manchester United", "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/1200px-Liverpool_FC.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/7/7a/Manchester_United_FC_crest.svg/1200px-Manchester_United_FC_crest.svg.png", "3", "0", "09/01/2026", "20:45"),
        Partido(10, "2", 204, 201, "Chelsea", "Manchester City", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/Chelsea_FC.svg/1200px-Chelsea_FC.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Manchester_City_FC_badge.svg/1200px-Manchester_City_FC_badge.svg.png", "1", "4", "06/01/2026", "18:30"),
        
        // === SERIE A (Liga 3) ===
        Partido(11, "3", 302, 301, "AC Milan", "Juventus", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Logo_of_AC_Milan.svg/1200px-Logo_of_AC_Milan.svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Juventus_FC_2017_icon_%28black%29.svg/1200px-Juventus_FC_2017_icon_%28black%29.svg.png", "2", "2", "11/01/2026", "20:45"),
        Partido(12, "3", 303, 304, "Inter de Milán", "AS Roma", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/FC_Internazionale_Milano_2021.svg/1200px-FC_Internazionale_Milano_2021.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f7/AS_Roma_logo_%282017%29.svg/1200px-AS_Roma_logo_%282017%29.svg.png", "3", "1", "07/01/2026", "18:00"),
        Partido(13, "3", 301, 303, "Juventus", "Inter de Milán", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Juventus_FC_2017_icon_%28black%29.svg/1200px-Juventus_FC_2017_icon_%28black%29.svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/FC_Internazionale_Milano_2021.svg/1200px-FC_Internazionale_Milano_2021.svg.png", null, null, "19/01/2026", "20:45"),
        
        // === BUNDESLIGA (Liga 4) ===
        Partido(14, "4", 401, 402, "Bayern Munich", "Borussia Dortmund", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg/1200px-FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Borussia_Dortmund_logo.svg/1200px-Borussia_Dortmund_logo.svg.png", "2", "1", "13/01/2026", "18:30"),
        Partido(15, "4", 403, 404, "RB Leipzig", "Bayer Leverkusen", "https://upload.wikimedia.org/wikipedia/en/thumb/0/04/RB_Leipzig_2014_logo.svg/1200px-RB_Leipzig_2014_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/5/59/Bayer_04_Leverkusen_logo.svg/1200px-Bayer_04_Leverkusen_logo.svg.png", "1", "3", "10/01/2026", "15:30"),
        Partido(16, "4", 402, 403, "Borussia Dortmund", "RB Leipzig", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Borussia_Dortmund_logo.svg/1200px-Borussia_Dortmund_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/0/04/RB_Leipzig_2014_logo.svg/1200px-RB_Leipzig_2014_logo.svg.png", null, null, "18/01/2026", "18:30"),
        
        // === LIGUE 1 (Liga 5) ===
        Partido(17, "5", 501, 502, "PSG", "Olympique de Marsella", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a7/Paris_Saint-Germain_F.C..svg/1200px-Paris_Saint-Germain_F.C..svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Olympique_Marseille_logo.svg/1200px-Olympique_Marseille_logo.svg.png", "4", "0", "14/01/2026", "21:00"),
        Partido(18, "5", 503, 504, "Mónaco", "Lyon", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cf/AS_Monaco_FC.svg/1200px-AS_Monaco_FC.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a5/Olympique_Lyonnais.svg/1200px-Olympique_Lyonnais.svg.png", "2", "2", "08/01/2026", "19:00"),
        Partido(19, "5", 502, 503, "Olympique de Marsella", "Mónaco", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Olympique_Marseille_logo.svg/1200px-Olympique_Marseille_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cf/AS_Monaco_FC.svg/1200px-AS_Monaco_FC.svg.png", null, null, "21/01/2026", "21:00")
    )

    // --- 5. EVENTOS (Para varios partidos) ---
    val EVENTOS = listOf(
        // Partido 3: Barcelona 2-3 Real Madrid
        Evento(1, 3, 12, "Gol", 0, "Vinicius Jr."),
        Evento(2, 3, 25, "Tarjeta Amarilla", 0, "Gavi"),
        Evento(3, 3, 38, "Gol", 0, "Lewandowski"),
        Evento(4, 3, 52, "Gol", 0, "Bellingham"),
        Evento(5, 3, 67, "Tarjeta Amarilla", 0, "Modric"),
        Evento(6, 3, 78, "Gol", 0, "Raphinha"),
        Evento(7, 3, 89, "Gol", 0, "Mbappé"),
        
        // Partido 5: Sevilla 0-2 Atlético
        Evento(8, 5, 34, "Gol", 0, "Griezmann"),
        Evento(9, 5, 56, "Tarjeta Amarilla", 0, "Fernando"),
        Evento(10, 5, 82, "Gol", 0, "Álvaro Morata"),
        
        // Partido 8: Arsenal 2-1 Chelsea
        Evento(11, 8, 18, "Gol", 0, "Saka"),
        Evento(12, 8, 44, "Tarjeta Amarilla", 0, "Caicedo"),
        Evento(13, 8, 61, "Gol", 0, "Palmer"),
        Evento(14, 8, 88, "Gol", 0, "Havertz"),
        Evento(15, 8, 90, "Tarjeta Roja", 0, "Enzo Fernández"),
        
        // Partido 9: Liverpool 3-0 Man United
        Evento(16, 9, 15, "Gol", 0, "Salah"),
        Evento(17, 9, 28, "Tarjeta Amarilla", 0, "Casemiro"),
        Evento(18, 9, 45, "Gol", 0, "Díaz"),
        Evento(19, 9, 72, "Gol", 0, "Darwin Núñez"),
        Evento(20, 9, 80, "Sustitución", 0, "Szoboszlai por Gakpo"),
        
        // Partido 10: Chelsea 1-4 Man City
        Evento(21, 10, 8, "Gol", 0, "Haaland"),
        Evento(22, 10, 22, "Gol", 0, "De Bruyne"),
        Evento(23, 10, 35, "Gol", 0, "Jackson"),
        Evento(24, 10, 58, "Gol", 0, "Haaland"),
        Evento(25, 10, 76, "Gol", 0, "Foden"),
        
        // Partido 11: Milan 2-2 Juventus
        Evento(26, 11, 23, "Gol", 0, "Leão"),
        Evento(27, 11, 41, "Gol", 0, "Vlahović"),
        Evento(28, 11, 67, "Tarjeta Amarilla", 0, "Locatelli"),
        Evento(29, 11, 75, "Gol", 0, "Pulisic"),
        Evento(30, 11, 88, "Gol", 0, "Chiesa"),
        
        // Partido 14: Bayern 2-1 Dortmund
        Evento(31, 14, 19, "Gol", 0, "Kane"),
        Evento(32, 14, 55, "Gol", 0, "Musiala"),
        Evento(33, 14, 78, "Gol", 0, "Brandt"),
        Evento(34, 14, 82, "Tarjeta Amarilla", 0, "Sabitzer"),
        
        // Partido 15: Leipzig 1-3 Leverkusen
        Evento(35, 15, 12, "Gol", 0, "Wirtz"),
        Evento(36, 15, 38, "Gol", 0, "Openda"),
        Evento(37, 15, 62, "Gol", 0, "Schick"),
        Evento(38, 15, 85, "Gol", 0, "Grimaldo"),
        
        // Partido 17: PSG 4-0 Marsella
        Evento(39, 17, 11, "Gol", 0, "Dembélé"),
        Evento(40, 17, 33, "Gol", 0, "Asensio"),
        Evento(41, 17, 56, "Tarjeta Amarilla", 0, "Kondogbia"),
        Evento(42, 17, 71, "Gol", 0, "Barcola"),
        Evento(43, 17, 89, "Gol", 0, "Kolo Muani")
    )

    val ALINEACIONES = listOf(
        // Partido 3: Barcelona vs Real Madrid (ID del partido = 3)
        Alineacion(3, 1, 101, "Courtois", true, 1),
        Alineacion(3, 2, 101, "Carvajal", true, 2),
        Alineacion(3, 3, 101, "Rüdiger", true, 22),
        Alineacion(3, 4, 101, "Alaba", true, 4),
        Alineacion(3, 5, 101, "Mendy", true, 23),
        Alineacion(3, 6, 101, "Camavinga", true, 6),
        Alineacion(3, 7, 101, "Bellingham", true, 5),
        Alineacion(3, 8, 101, "Modric", true, 10),
        Alineacion(3, 9, 101, "Vinicius Jr", true, 7),
        Alineacion(3, 10, 101, "Mbappé", true, 9),
        Alineacion(3, 11, 101, "Rodrygo", true, 11),

        Alineacion(3, 12, 102, "Ter Stegen", true, 1),
        Alineacion(3, 13, 102, "Koundé", true, 23),
        Alineacion(3, 14, 102, "Araujo", true, 4),
        Alineacion(3, 15, 102, "Cubarsí", true, 2),
        Alineacion(3, 16, 102, "Balde", true, 28),
        Alineacion(3, 17, 102, "Pedri", true, 8),
        Alineacion(3, 18, 102, "De Jong", true, 21),
        Alineacion(3, 19, 102, "Gavi", true, 6),
        Alineacion(3, 20, 102, "Lamine Yamal", true, 27),
        Alineacion(3, 21, 102, "Lewandowski", true, 9),
        Alineacion(3, 22, 102, "Raphinha", true, 11)
    )

    // --- 6. CLASIFICACIÓN (Actualizada para todas las ligas) ---
    val CLASIFICACION = listOf(
        // LaLiga EA Sports (Liga 1)
        Clasificacion("1", 101, "Real Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", 1, 52, 20, 16, 4, 0),
        Clasificacion("1", 102, "FC Barcelona", "https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/1200px-FC_Barcelona_%28crest%29.svg.png", 2, 48, 20, 15, 3, 2),
        Clasificacion("1", 103, "Atlético de Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f4/Atletico_Madrid_2017_logo.svg/1200px-Atletico_Madrid_2017_logo.svg.png", 3, 42, 20, 13, 3, 4),
        Clasificacion("1", 105, "Real Sociedad", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f1/Real_Sociedad_logo.svg/1200px-Real_Sociedad_logo.svg.png", 4, 36, 20, 10, 6, 4),
        Clasificacion("1", 106, "Villarreal CF", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/Villarreal_CF_logo.svg/1200px-Villarreal_CF_logo.svg.png", 5, 34, 20, 10, 4, 6),
        Clasificacion("1", 104, "Sevilla FC", "https://upload.wikimedia.org/wikipedia/en/thumb/3/3b/Sevilla_FC_logo.svg/1200px-Sevilla_FC_logo.svg.png", 6, 28, 20, 8, 4, 8),
        
        // Premier League (Liga 2)
        Clasificacion("2", 201, "Manchester City", "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Manchester_City_FC_badge.svg/1200px-Manchester_City_FC_badge.svg.png", 1, 54, 21, 17, 3, 1),
        Clasificacion("2", 202, "Liverpool", "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/1200px-Liverpool_FC.svg.png", 2, 51, 21, 16, 3, 2),
        Clasificacion("2", 203, "Arsenal", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Arsenal_FC.svg/1200px-Arsenal_FC.svg.png", 3, 47, 21, 14, 5, 2),
        Clasificacion("2", 204, "Chelsea", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/Chelsea_FC.svg/1200px-Chelsea_FC.svg.png", 4, 38, 21, 11, 5, 5),
        Clasificacion("2", 205, "Manchester United", "https://upload.wikimedia.org/wikipedia/en/thumb/7/7a/Manchester_United_FC_crest.svg/1200px-Manchester_United_FC_crest.svg.png", 5, 32, 21, 9, 5, 7),
        
        // Serie A (Liga 3)
        Clasificacion("3", 303, "Inter de Milán", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/FC_Internazionale_Milano_2021.svg/1200px-FC_Internazionale_Milano_2021.svg.png", 1, 50, 19, 16, 2, 1),
        Clasificacion("3", 301, "Juventus", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Juventus_FC_2017_icon_%28black%29.svg/1200px-Juventus_FC_2017_icon_%28black%29.svg.png", 2, 43, 19, 13, 4, 2),
        Clasificacion("3", 302, "AC Milan", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Logo_of_AC_Milan.svg/1200px-Logo_of_AC_Milan.svg.png", 3, 39, 19, 11, 6, 2),
        Clasificacion("3", 304, "AS Roma", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f7/AS_Roma_logo_%282017%29.svg/1200px-AS_Roma_logo_%282017%29.svg.png", 4, 34, 19, 10, 4, 5),
        
        // Bundesliga (Liga 4)
        Clasificacion("4", 404, "Bayer Leverkusen", "https://upload.wikimedia.org/wikipedia/en/thumb/5/59/Bayer_04_Leverkusen_logo.svg/1200px-Bayer_04_Leverkusen_logo.svg.png", 1, 49, 18, 15, 4, 0),
        Clasificacion("4", 401, "Bayern Munich", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg/1200px-FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg.png", 2, 46, 18, 14, 4, 0),
        Clasificacion("4", 402, "Borussia Dortmund", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Borussia_Dortmund_logo.svg/1200px-Borussia_Dortmund_logo.svg.png", 3, 40, 18, 12, 4, 2),
        Clasificacion("4", 403, "RB Leipzig", "https://upload.wikimedia.org/wikipedia/en/thumb/0/04/RB_Leipzig_2014_logo.svg/1200px-RB_Leipzig_2014_logo.svg.png", 4, 35, 18, 10, 5, 3),
        
        // Ligue 1 (Liga 5)
        Clasificacion("5", 501, "PSG", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a7/Paris_Saint-Germain_F.C..svg/1200px-Paris_Saint-Germain_F.C..svg.png", 1, 53, 19, 17, 2, 0),
        Clasificacion("5", 503, "Mónaco", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cf/AS_Monaco_FC.svg/1200px-AS_Monaco_FC.svg.png", 2, 41, 19, 12, 5, 2),
        Clasificacion("5", 504, "Lyon", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a5/Olympique_Lyonnais.svg/1200px-Olympique_Lyonnais.svg.png", 3, 38, 19, 11, 5, 3),
        Clasificacion("5", 502, "Olympique de Marsella", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Olympique_Marseille_logo.svg/1200px-Olympique_Marseille_logo.svg.png", 4, 35, 19, 10, 5, 4)
    )
}