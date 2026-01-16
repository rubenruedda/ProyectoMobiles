package utils

import model.*

object PreCargaDatos {

    // --- 1. LIGAS (Incluye ligas principales e inferiores) ---
    val LIGAS = listOf(
        // Ligas principales
        Liga("1", "LaLiga EA Sports", "España", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/LaLiga_logo_2023.svg/1200px-LaLiga_logo_2023.svg.png", true),
        Liga("2", "Premier League", "Inglaterra", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f2/Premier_League_Logo.svg/1200px-Premier_League_Logo.svg.png", false),
        Liga("3", "Serie A", "Italia", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Serie_A_logo_2022.svg/1200px-Serie_A_logo_2022.svg.png", false),
        Liga("4", "Bundesliga", "Alemania", "https://upload.wikimedia.org/wikipedia/en/thumb/d/df/Bundesliga_logo_%282017%29.svg/1200px-Bundesliga_logo_%282017%29.svg.png", false),
        Liga("5", "Ligue 1", "Francia", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Ligue_1_Uber_Eats_logo.svg/1200px-Ligue_1_Uber_Eats_logo.svg.png", false),
        // Ligas secundarias / inferiores
        Liga("6", "LaLiga Hypermotion", "España", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/LaLiga_Hypermotion_2023_logo.svg/1200px-LaLiga_Hypermotion_2023_logo.svg.png", false),
        Liga("7", "Serie B", "Italia", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Serie_B_%28logo%29.svg/1200px-Serie_B_%28logo%29.svg.png", false),
        Liga("8", "Championship", "Inglaterra", "https://upload.wikimedia.org/wikipedia/en/thumb/3/3d/EFL_Championship.svg/1200px-EFL_Championship.svg.png", false),
        Liga("9", "Eredivisie", "Países Bajos", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Eredivisie_Logo.svg/1200px-Eredivisie_Logo.svg.png", false),
        Liga("10", "Liga Portugal", "Portugal", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Liga_Portugal_logo.svg/1200px-Liga_Portugal_logo.svg.png", false),
        Liga("11", "Champions League", "Europa", "https://upload.wikimedia.org/wikipedia/en/thumb/b/bf/UEFA_Champions_League_logo_2024.svg/1200px-UEFA_Champions_League_logo_2024.svg.png", true),
        Liga("12", "Europa League", "Europa", "https://upload.wikimedia.org/wikipedia/en/thumb/0/03/UEFA_Europa_League_logo_%282024%29.svg/1200px-UEFA_Europa_League_logo_%282024%29.svg.png", false)
    )

    // --- 2. EQUIPOS (Muchos más equipos) ---
    val EQUIPOS = listOf(
        // España - LaLiga
        Equipo(101, "Real Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", "1", true),
        Equipo(102, "FC Barcelona", "https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/1200px-FC_Barcelona_%28crest%29.svg.png", "1", true),
        Equipo(103, "Atlético de Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f4/Atletico_Madrid_2017_logo.svg/1200px-Atletico_Madrid_2017_logo.svg.png", "1", false),
        Equipo(104, "Sevilla FC", "https://upload.wikimedia.org/wikipedia/en/thumb/3/3b/Sevilla_FC_logo.svg/1200px-Sevilla_FC_logo.svg.png", "1", false),
        Equipo(105, "Real Sociedad", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f1/Real_Sociedad_logo.svg/1200px-Real_Sociedad_logo.svg.png", "1", false),
        Equipo(106, "Villarreal CF", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/Villarreal_CF_logo.svg/1200px-Villarreal_CF_logo.svg.png", "1", false),
        Equipo(107, "Real Betis", "https://upload.wikimedia.org/wikipedia/en/thumb/1/13/Real_betis_logo.svg/1200px-Real_betis_logo.svg.png", "1", false),
        Equipo(108, "Athletic Club", "https://upload.wikimedia.org/wikipedia/en/thumb/9/98/Club_Athletic_Bilbao_logo.svg/1200px-Club_Athletic_Bilbao_logo.svg.png", "1", false),
        Equipo(109, "Valencia CF", "https://upload.wikimedia.org/wikipedia/en/thumb/c/ce/Valenciacf.svg/1200px-Valenciacf.svg.png", "1", false),
        Equipo(110, "Girona FC", "https://upload.wikimedia.org/wikipedia/en/thumb/e/e2/Girona_FC_Logo.svg/1200px-Girona_FC_Logo.svg.png", "1", false),
        // España - Segunda División
        Equipo(150, "Racing de Santander", "https://upload.wikimedia.org/wikipedia/en/thumb/5/51/Racing_de_Santander_logo.svg/1200px-Racing_de_Santander_logo.svg.png", "6", false),
        Equipo(151, "Real Zaragoza", "https://upload.wikimedia.org/wikipedia/en/thumb/8/8e/Real_Zaragoza_logo.svg/1200px-Real_Zaragoza_logo.svg.png", "6", false),
        Equipo(152, "Sporting de Gijón", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f8/Real_Sporting_de_Gij%C3%B3n_logo.svg/1200px-Real_Sporting_de_Gij%C3%B3n_logo.svg.png", "6", false),
        Equipo(153, "Levante UD", "https://upload.wikimedia.org/wikipedia/en/thumb/7/7b/Levante_Uni%C3%B3n_Deportiva%2C_S.A.D._logo.svg/1200px-Levante_Uni%C3%B3n_Deportiva%2C_S.A.D._logo.svg.png", "6", false),
        // Inglaterra - Premier League
        Equipo(201, "Manchester City", "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Manchester_City_FC_badge.svg/1200px-Manchester_City_FC_badge.svg.png", "2", true),
        Equipo(202, "Liverpool", "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/1200px-Liverpool_FC.svg.png", "2", false),
        Equipo(203, "Arsenal", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Arsenal_FC.svg/1200px-Arsenal_FC.svg.png", "2", false),
        Equipo(204, "Chelsea", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/Chelsea_FC.svg/1200px-Chelsea_FC.svg.png", "2", false),
        Equipo(205, "Manchester United", "https://upload.wikimedia.org/wikipedia/en/thumb/7/7a/Manchester_United_FC_crest.svg/1200px-Manchester_United_FC_crest.svg.png", "2", false),
        Equipo(206, "Tottenham", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b4/Tottenham_Hotspur.svg/1200px-Tottenham_Hotspur.svg.png", "2", false),
        Equipo(207, "Newcastle United", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Newcastle_United_Logo.svg/1200px-Newcastle_United_Logo.svg.png", "2", false),
        Equipo(208, "Aston Villa", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f9/Aston_Villa_FC_crest_%282016%29.svg/1200px-Aston_Villa_FC_crest_%282016%29.svg.png", "2", false),
        // Inglaterra - Championship
        Equipo(250, "Leeds United", "https://upload.wikimedia.org/wikipedia/en/thumb/5/54/Leeds_United_F.C._logo.svg/1200px-Leeds_United_F.C._logo.svg.png", "8", false),
        Equipo(251, "Sheffield United", "https://upload.wikimedia.org/wikipedia/en/thumb/9/9c/Sheffield_United_FC_logo.svg/1200px-Sheffield_United_FC_logo.svg.png", "8", false),
        // Italia
        Equipo(301, "Juventus", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Juventus_FC_2017_icon_%28black%29.svg/1200px-Juventus_FC_2017_icon_%28black%29.svg.png", "3", false),
        Equipo(302, "AC Milan", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Logo_of_AC_Milan.svg/1200px-Logo_of_AC_Milan.svg.png", "3", true),
        Equipo(303, "Inter de Milán", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/FC_Internazionale_Milano_2021.svg/1200px-FC_Internazionale_Milano_2021.svg.png", "3", false),
        Equipo(304, "AS Roma", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f7/AS_Roma_logo_%282017%29.svg/1200px-AS_Roma_logo_%282017%29.svg.png", "3", false),
        Equipo(305, "Napoli", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/SSC_Neapel.svg/1200px-SSC_Neapel.svg.png", "3", false),
        Equipo(306, "Lazio", "https://upload.wikimedia.org/wikipedia/en/thumb/7/71/SS_Lazio.svg/1200px-SS_Lazio.svg.png", "3", false),
        Equipo(307, "Fiorentina", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/ACF_Fiorentina_2022.svg/1200px-ACF_Fiorentina_2022.svg.png", "3", false),
        Equipo(308, "Atalanta", "https://upload.wikimedia.org/wikipedia/en/thumb/6/66/AtalantaBC.svg/1200px-AtalantaBC.svg.png", "3", false),
        // Alemania
        Equipo(401, "Bayern Munich", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg/1200px-FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg.png", "4", false),
        Equipo(402, "Borussia Dortmund", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Borussia_Dortmund_logo.svg/1200px-Borussia_Dortmund_logo.svg.png", "4", true),
        Equipo(403, "RB Leipzig", "https://upload.wikimedia.org/wikipedia/en/thumb/0/04/RB_Leipzig_2014_logo.svg/1200px-RB_Leipzig_2014_logo.svg.png", "4", false),
        Equipo(404, "Bayer Leverkusen", "https://upload.wikimedia.org/wikipedia/en/thumb/5/59/Bayer_04_Leverkusen_logo.svg/1200px-Bayer_04_Leverkusen_logo.svg.png", "4", false),
        Equipo(405, "Eintracht Frankfurt", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/Eintracht_Frankfurt_Logo.svg/1200px-Eintracht_Frankfurt_Logo.svg.png", "4", false),
        Equipo(406, "VfB Stuttgart", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/VfB_Stuttgart_1893_Logo.svg/1200px-VfB_Stuttgart_1893_Logo.svg.png", "4", false),
        // Francia
        Equipo(501, "PSG", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a7/Paris_Saint-Germain_F.C..svg/1200px-Paris_Saint-Germain_F.C..svg.png", "5", true),
        Equipo(502, "Olympique de Marsella", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Olympique_Marseille_logo.svg/1200px-Olympique_Marseille_logo.svg.png", "5", false),
        Equipo(503, "Mónaco", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cf/AS_Monaco_FC.svg/1200px-AS_Monaco_FC.svg.png", "5", false),
        Equipo(504, "Lyon", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a5/Olympique_Lyonnais.svg/1200px-Olympique_Lyonnais.svg.png", "5", false),
        Equipo(505, "Lille", "https://upload.wikimedia.org/wikipedia/en/thumb/7/7e/Lille_OSC_2018_logo.svg/1200px-Lille_OSC_2018_logo.svg.png", "5", false),
        // Países Bajos - Eredivisie
        Equipo(601, "Ajax", "https://upload.wikimedia.org/wikipedia/en/thumb/7/79/Ajax_Amsterdam.svg/1200px-Ajax_Amsterdam.svg.png", "9", false),
        Equipo(602, "PSV Eindhoven", "https://upload.wikimedia.org/wikipedia/en/thumb/0/05/PSV_Eindhoven.svg/1200px-PSV_Eindhoven.svg.png", "9", false),
        Equipo(603, "Feyenoord", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cd/Feyenoord_logo.svg/1200px-Feyenoord_logo.svg.png", "9", false),
        // Portugal
        Equipo(701, "Benfica", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a2/SL_Benfica_logo.svg/1200px-SL_Benfica_logo.svg.png", "10", false),
        Equipo(702, "Porto", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f1/FC_Porto.svg/1200px-FC_Porto.svg.png", "10", false),
        Equipo(703, "Sporting CP", "https://upload.wikimedia.org/wikipedia/en/thumb/e/e1/Sporting_Clube_de_Portugal_%28Logo%29.svg/1200px-Sporting_Clube_de_Portugal_%28Logo%29.svg.png", "10", false)
    )

    // --- 3. NOTICIAS (Muchas más noticias variadas) ---
    val NOTICIAS = listOf(
        Noticia(1, "El Clásico paraliza el mundo", "Real Madrid y Barcelona se enfrentan en un duelo decisivo por el liderato de LaLiga. Las estrellas de ambos equipos llegan en plena forma. Vinicius Jr. ha sido el jugador más destacado del Real Madrid esta temporada, mientras que Lewandowski lidera la tabla de goleadores del Barcelona.", "Marca", "16/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/b8wbxp9i3afuqr8sxm4i"),
        Noticia(2, "Haaland rompe otro récord", "El delantero noruego del Manchester City ha superado la marca de goles en una sola temporada de la Premier League. Con 35 goles en 28 partidos, Erling Haaland continúa demostrando por qué es considerado el mejor delantero del mundo.", "BBC Sport", "15/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/c9iswfyfyauo1xjzvcx9"),
        Noticia(3, "Mbappé triunfa en Madrid", "Kylian Mbappé se ha adaptado perfectamente al Real Madrid y ya es el máximo goleador del equipo esta temporada. El francés ha formado una conexión letal con Vinicius Jr. y Bellingham.", "L'Équipe", "14/01/2026", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/2019-07-17_SG_Dynamo_Dresden_vs._Paris_Saint-Germain_by_Sandro_Halank%E2%80%93129_%28cropped%29.jpg/440px-2019-07-17_SG_Dynamo_Dresden_vs._Paris_Saint-Germain_by_Sandro_Halank%E2%80%93129_%28cropped%29.jpg"),
        Noticia(4, "España se prepara para el Mundial", "La selección española ha comenzado su concentración en Las Rozas con la vista puesta en el próximo Mundial. Luis de la Fuente ha convocado a 26 jugadores para los amistosos preparatorios.", "AS", "13/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/nqeevzoxwvnpsxhxvpxf"),
        Noticia(5, "Messi sigue haciendo historia", "Leo Messi continúa brillando en el Inter Miami y ha llevado al equipo a su segundo título consecutivo de la MLS. A sus 38 años, el argentino sigue demostrando que es uno de los mejores jugadores de todos los tiempos.", "ESPN", "12/01/2026", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Lionel-Messi-Argentina-2022-FIFA-World-Cup_%28cropped%29.jpg/440px-Lionel-Messi-Argentina-2022-FIFA-World-Cup_%28cropped%29.jpg"),
        Noticia(6, "Champions League: los favoritos", "Con la fase de grupos finalizada, analizamos qué equipos tienen más opciones de levantar la orejona. Real Madrid, Manchester City y Bayern Munich encabezan las apuestas.", "UEFA", "11/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/xvvvw87jhxuphvvdyefy"),
        Noticia(7, "Bellingham: el jugador del momento", "Jude Bellingham ha sido elegido mejor jugador del mes en LaLiga por tercera vez consecutiva. El inglés es fundamental en el esquema de Ancelotti con números espectaculares.", "Goal", "10/01/2026", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Jude_Bellingham_2023.jpg/440px-Jude_Bellingham_2023.jpg"),
        Noticia(8, "El VAR: polémica en el fútbol", "El uso del VAR sigue generando debate. En la última jornada hubo varias decisiones controvertidas que afectaron el resultado de partidos importantes.", "Sport", "09/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/tvx2fxq0xmtzc9y8x4wa"),
        Noticia(9, "Lamine Yamal, el futuro del fútbol", "Con solo 18 años, Lamine Yamal se ha convertido en una de las estrellas más brillantes del Barcelona. Su rendimiento ha llamado la atención de los mejores clubes del mundo.", "Mundo Deportivo", "08/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/b8wbxp9i3afuqr8sxm4i"),
        Noticia(10, "Bayern Munich ficha estrella", "El Bayern Munich ha cerrado el fichaje más caro de su historia. El club alemán invierte 100 millones de euros en reforzar su plantilla para la segunda mitad de la temporada.", "Kicker", "07/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/c9iswfyfyauo1xjzvcx9"),
        Noticia(11, "Girona sueña con Champions", "El Girona FC está realizando una temporada histórica y pelea por puestos de Champions League. El proyecto del City Football Group está dando sus frutos en Cataluña.", "Sport", "06/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/nqeevzoxwvnpsxhxvpxf"),
        Noticia(12, "El derbi de Manchester", "Manchester United y Manchester City protagonizaron un derbi vibrante. El partido acabó con polémica arbitral y expulsiones de ambos bandos.", "Sky Sports", "05/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/xvvvw87jhxuphvvdyefy"),
        Noticia(13, "Inter campeón de invierno", "El Inter de Milán se proclama campeón de invierno en la Serie A con una ventaja de 5 puntos sobre la Juventus. Lautaro Martínez lidera la tabla de goleadores.", "Gazzetta", "04/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/tvx2fxq0xmtzc9y8x4wa"),
        Noticia(14, "Leverkusen invicto en Bundesliga", "El Bayer Leverkusen sigue sin conocer la derrota en la Bundesliga. El equipo de Xabi Alonso marca el ritmo en Alemania con un fútbol espectacular.", "Bild", "03/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/b8wbxp9i3afuqr8sxm4i"),
        Noticia(15, "PSG domina la Ligue 1", "El Paris Saint-Germain lidera cómodamente la liga francesa. Los parisinos buscan su cuarto título consecutivo de liga.", "L'Équipe", "02/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/c9iswfyfyauo1xjzvcx9"),
        Noticia(16, "Ajax renace en Eredivisie", "El Ajax de Amsterdam está recuperando su mejor nivel y pelea por el título en la Eredivisie. Los jóvenes talentos del club brillan en la liga holandesa.", "Voetbal International", "01/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/nqeevzoxwvnpsxhxvpxf"),
        Noticia(17, "Benfica arrasa en Portugal", "El Benfica lidera la Liga Portugal con números de récord. El equipo ha ganado todos sus partidos como local esta temporada.", "Record", "31/12/2025", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/xvvvw87jhxuphvvdyefy"),
        Noticia(18, "El mercado de invierno se calienta", "Los principales clubes europeos preparan movimientos importantes para el mercado de invierno. Varios jugadores podrían cambiar de equipo en las próximas semanas.", "Transfermarkt", "30/12/2025", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/tvx2fxq0xmtzc9y8x4wa")
    )

    // --- 4. PARTIDOS (Muchos más partidos para todas las ligas) ---
    val PARTIDOS = listOf(
        // === LALIGA (Liga 1) ===
        Partido(1, "1", 101, 102, "Real Madrid", "FC Barcelona", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/1200px-FC_Barcelona_%28crest%29.svg.png", null, null, "16/01/2026", "21:00"),
        Partido(2, "1", 103, 104, "Atlético de Madrid", "Sevilla FC", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f4/Atletico_Madrid_2017_logo.svg/1200px-Atletico_Madrid_2017_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/3/3b/Sevilla_FC_logo.svg/1200px-Sevilla_FC_logo.svg.png", null, null, "16/01/2026", "18:30"),
        Partido(3, "1", 102, 101, "FC Barcelona", "Real Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/1200px-FC_Barcelona_%28crest%29.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", "2", "3", "10/01/2026", "21:00"),
        Partido(4, "1", 105, 106, "Real Sociedad", "Villarreal CF", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f1/Real_Sociedad_logo.svg/1200px-Real_Sociedad_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/Villarreal_CF_logo.svg/1200px-Villarreal_CF_logo.svg.png", "1", "1", "08/01/2026", "20:00"),
        Partido(5, "1", 104, 103, "Sevilla FC", "Atlético de Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/3/3b/Sevilla_FC_logo.svg/1200px-Sevilla_FC_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f4/Atletico_Madrid_2017_logo.svg/1200px-Atletico_Madrid_2017_logo.svg.png", "0", "2", "05/01/2026", "16:15"),
        Partido(6, "1", 106, 101, "Villarreal CF", "Real Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/Villarreal_CF_logo.svg/1200px-Villarreal_CF_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", null, null, "20/01/2026", "21:00"),
        Partido(40, "1", 107, 108, "Real Betis", "Athletic Club", "https://upload.wikimedia.org/wikipedia/en/thumb/1/13/Real_betis_logo.svg/1200px-Real_betis_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/9/98/Club_Athletic_Bilbao_logo.svg/1200px-Club_Athletic_Bilbao_logo.svg.png", "2", "1", "16/01/2026", "14:00"),
        Partido(41, "1", 109, 110, "Valencia CF", "Girona FC", "https://upload.wikimedia.org/wikipedia/en/thumb/c/ce/Valenciacf.svg/1200px-Valenciacf.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/e/e2/Girona_FC_Logo.svg/1200px-Girona_FC_Logo.svg.png", null, null, "17/01/2026", "21:00"),
        
        // === LALIGA HYPERMOTION (Liga 6) ===
        Partido(50, "6", 150, 151, "Racing de Santander", "Real Zaragoza", "https://upload.wikimedia.org/wikipedia/en/thumb/5/51/Racing_de_Santander_logo.svg/1200px-Racing_de_Santander_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/8/8e/Real_Zaragoza_logo.svg/1200px-Real_Zaragoza_logo.svg.png", "1", "0", "15/01/2026", "21:00"),
        Partido(51, "6", 152, 153, "Sporting de Gijón", "Levante UD", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f8/Real_Sporting_de_Gij%C3%B3n_logo.svg/1200px-Real_Sporting_de_Gij%C3%B3n_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/7/7b/Levante_Uni%C3%B3n_Deportiva%2C_S.A.D._logo.svg/1200px-Levante_Uni%C3%B3n_Deportiva%2C_S.A.D._logo.svg.png", "2", "2", "16/01/2026", "16:00"),
        
        // === PREMIER LEAGUE (Liga 2) ===
        Partido(7, "2", 201, 202, "Manchester City", "Liverpool", "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Manchester_City_FC_badge.svg/1200px-Manchester_City_FC_badge.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/1200px-Liverpool_FC.svg.png", null, null, "16/01/2026", "17:30"),
        Partido(8, "2", 203, 204, "Arsenal", "Chelsea", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Arsenal_FC.svg/1200px-Arsenal_FC.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/Chelsea_FC.svg/1200px-Chelsea_FC.svg.png", "2", "1", "12/01/2026", "16:00"),
        Partido(9, "2", 202, 205, "Liverpool", "Manchester United", "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/1200px-Liverpool_FC.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/7/7a/Manchester_United_FC_crest.svg/1200px-Manchester_United_FC_crest.svg.png", "3", "0", "09/01/2026", "20:45"),
        Partido(10, "2", 204, 201, "Chelsea", "Manchester City", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/Chelsea_FC.svg/1200px-Chelsea_FC.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Manchester_City_FC_badge.svg/1200px-Manchester_City_FC_badge.svg.png", "1", "4", "06/01/2026", "18:30"),
        Partido(42, "2", 206, 207, "Tottenham", "Newcastle United", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b4/Tottenham_Hotspur.svg/1200px-Tottenham_Hotspur.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Newcastle_United_Logo.svg/1200px-Newcastle_United_Logo.svg.png", "1", "2", "15/01/2026", "20:30"),
        Partido(43, "2", 208, 203, "Aston Villa", "Arsenal", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f9/Aston_Villa_FC_crest_%282016%29.svg/1200px-Aston_Villa_FC_crest_%282016%29.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Arsenal_FC.svg/1200px-Arsenal_FC.svg.png", null, null, "18/01/2026", "17:30"),
        
        // === CHAMPIONSHIP (Liga 8) ===
        Partido(55, "8", 250, 251, "Leeds United", "Sheffield United", "https://upload.wikimedia.org/wikipedia/en/thumb/5/54/Leeds_United_F.C._logo.svg/1200px-Leeds_United_F.C._logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/9/9c/Sheffield_United_FC_logo.svg/1200px-Sheffield_United_FC_logo.svg.png", "3", "1", "16/01/2026", "15:00"),
        
        // === SERIE A (Liga 3) ===
        Partido(11, "3", 302, 301, "AC Milan", "Juventus", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Logo_of_AC_Milan.svg/1200px-Logo_of_AC_Milan.svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Juventus_FC_2017_icon_%28black%29.svg/1200px-Juventus_FC_2017_icon_%28black%29.svg.png", "2", "2", "11/01/2026", "20:45"),
        Partido(12, "3", 303, 304, "Inter de Milán", "AS Roma", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/FC_Internazionale_Milano_2021.svg/1200px-FC_Internazionale_Milano_2021.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f7/AS_Roma_logo_%282017%29.svg/1200px-AS_Roma_logo_%282017%29.svg.png", "3", "1", "07/01/2026", "18:00"),
        Partido(13, "3", 301, 303, "Juventus", "Inter de Milán", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Juventus_FC_2017_icon_%28black%29.svg/1200px-Juventus_FC_2017_icon_%28black%29.svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/FC_Internazionale_Milano_2021.svg/1200px-FC_Internazionale_Milano_2021.svg.png", null, null, "19/01/2026", "20:45"),
        Partido(44, "3", 305, 306, "Napoli", "Lazio", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/SSC_Neapel.svg/1200px-SSC_Neapel.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/7/71/SS_Lazio.svg/1200px-SS_Lazio.svg.png", "2", "0", "14/01/2026", "20:45"),
        Partido(45, "3", 307, 308, "Fiorentina", "Atalanta", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/ACF_Fiorentina_2022.svg/1200px-ACF_Fiorentina_2022.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/6/66/AtalantaBC.svg/1200px-AtalantaBC.svg.png", null, null, "16/01/2026", "20:45"),
        
        // === BUNDESLIGA (Liga 4) ===
        Partido(14, "4", 401, 402, "Bayern Munich", "Borussia Dortmund", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg/1200px-FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Borussia_Dortmund_logo.svg/1200px-Borussia_Dortmund_logo.svg.png", "2", "1", "13/01/2026", "18:30"),
        Partido(15, "4", 403, 404, "RB Leipzig", "Bayer Leverkusen", "https://upload.wikimedia.org/wikipedia/en/thumb/0/04/RB_Leipzig_2014_logo.svg/1200px-RB_Leipzig_2014_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/5/59/Bayer_04_Leverkusen_logo.svg/1200px-Bayer_04_Leverkusen_logo.svg.png", "1", "3", "10/01/2026", "15:30"),
        Partido(16, "4", 402, 403, "Borussia Dortmund", "RB Leipzig", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Borussia_Dortmund_logo.svg/1200px-Borussia_Dortmund_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/0/04/RB_Leipzig_2014_logo.svg/1200px-RB_Leipzig_2014_logo.svg.png", null, null, "18/01/2026", "18:30"),
        Partido(46, "4", 405, 406, "Eintracht Frankfurt", "VfB Stuttgart", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/Eintracht_Frankfurt_Logo.svg/1200px-Eintracht_Frankfurt_Logo.svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/VfB_Stuttgart_1893_Logo.svg/1200px-VfB_Stuttgart_1893_Logo.svg.png", "1", "1", "16/01/2026", "15:30"),
        
        // === LIGUE 1 (Liga 5) ===
        Partido(17, "5", 501, 502, "PSG", "Olympique de Marsella", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a7/Paris_Saint-Germain_F.C..svg/1200px-Paris_Saint-Germain_F.C..svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Olympique_Marseille_logo.svg/1200px-Olympique_Marseille_logo.svg.png", "4", "0", "14/01/2026", "21:00"),
        Partido(18, "5", 503, 504, "Mónaco", "Lyon", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cf/AS_Monaco_FC.svg/1200px-AS_Monaco_FC.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a5/Olympique_Lyonnais.svg/1200px-Olympique_Lyonnais.svg.png", "2", "2", "08/01/2026", "19:00"),
        Partido(19, "5", 502, 503, "Olympique de Marsella", "Mónaco", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Olympique_Marseille_logo.svg/1200px-Olympique_Marseille_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cf/AS_Monaco_FC.svg/1200px-AS_Monaco_FC.svg.png", null, null, "21/01/2026", "21:00"),
        Partido(47, "5", 505, 501, "Lille", "PSG", "https://upload.wikimedia.org/wikipedia/en/thumb/7/7e/Lille_OSC_2018_logo.svg/1200px-Lille_OSC_2018_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a7/Paris_Saint-Germain_F.C..svg/1200px-Paris_Saint-Germain_F.C..svg.png", null, null, "17/01/2026", "21:00"),
        
        // === EREDIVISIE (Liga 9) ===
        Partido(60, "9", 601, 602, "Ajax", "PSV Eindhoven", "https://upload.wikimedia.org/wikipedia/en/thumb/7/79/Ajax_Amsterdam.svg/1200px-Ajax_Amsterdam.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/0/05/PSV_Eindhoven.svg/1200px-PSV_Eindhoven.svg.png", "2", "3", "15/01/2026", "20:00"),
        Partido(61, "9", 603, 601, "Feyenoord", "Ajax", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cd/Feyenoord_logo.svg/1200px-Feyenoord_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/7/79/Ajax_Amsterdam.svg/1200px-Ajax_Amsterdam.svg.png", null, null, "19/01/2026", "14:30"),
        
        // === LIGA PORTUGAL (Liga 10) ===
        Partido(70, "10", 701, 702, "Benfica", "Porto", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a2/SL_Benfica_logo.svg/1200px-SL_Benfica_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f1/FC_Porto.svg/1200px-FC_Porto.svg.png", "3", "1", "12/01/2026", "21:00"),
        Partido(71, "10", 703, 701, "Sporting CP", "Benfica", "https://upload.wikimedia.org/wikipedia/en/thumb/e/e1/Sporting_Clube_de_Portugal_%28Logo%29.svg/1200px-Sporting_Clube_de_Portugal_%28Logo%29.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a2/SL_Benfica_logo.svg/1200px-SL_Benfica_logo.svg.png", null, null, "18/01/2026", "18:00"),
        
        // === CHAMPIONS LEAGUE (Liga 11) ===
        Partido(80, "11", 101, 201, "Real Madrid", "Manchester City", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Manchester_City_FC_badge.svg/1200px-Manchester_City_FC_badge.svg.png", null, null, "22/01/2026", "21:00"),
        Partido(81, "11", 401, 501, "Bayern Munich", "PSG", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg/1200px-FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a7/Paris_Saint-Germain_F.C..svg/1200px-Paris_Saint-Germain_F.C..svg.png", null, null, "22/01/2026", "21:00"),
        Partido(82, "11", 102, 303, "FC Barcelona", "Inter de Milán", "https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/1200px-FC_Barcelona_%28crest%29.svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/FC_Internazionale_Milano_2021.svg/1200px-FC_Internazionale_Milano_2021.svg.png", "2", "1", "08/01/2026", "21:00")
    )

    // --- 5. EVENTOS ---
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
        Evento(43, 17, 89, "Gol", 0, "Kolo Muani"),
        
        // Partido 40: Betis 2-1 Athletic
        Evento(44, 40, 23, "Gol", 0, "Isco"),
        Evento(45, 40, 56, "Gol", 0, "Nico Williams"),
        Evento(46, 40, 78, "Gol", 0, "Ayoze Pérez"),
        
        // Partido 42: Tottenham 1-2 Newcastle
        Evento(47, 42, 15, "Gol", 0, "Son"),
        Evento(48, 42, 44, "Gol", 0, "Isak"),
        Evento(49, 42, 82, "Gol", 0, "Gordon"),
        
        // Partido 44: Napoli 2-0 Lazio
        Evento(50, 44, 33, "Gol", 0, "Osimhen"),
        Evento(51, 44, 67, "Gol", 0, "Kvara"),
        
        // Partido 50: Racing 1-0 Zaragoza
        Evento(52, 50, 58, "Gol", 0, "Álvaro García"),
        
        // Partido 55: Leeds 3-1 Sheffield
        Evento(53, 55, 12, "Gol", 0, "Bamford"),
        Evento(54, 55, 45, "Gol", 0, "Gnonto"),
        Evento(55, 55, 68, "Gol", 0, "McBurnie"),
        Evento(56, 55, 88, "Gol", 0, "Rutter"),
        
        // Partido 60: Ajax 2-3 PSV
        Evento(57, 60, 8, "Gol", 0, "Taylor"),
        Evento(58, 60, 25, "Gol", 0, "de Jong"),
        Evento(59, 60, 55, "Gol", 0, "Berghuis"),
        Evento(60, 60, 72, "Gol", 0, "de Jong"),
        Evento(61, 60, 90, "Gol", 0, "Veerman"),
        
        // Partido 70: Benfica 3-1 Porto
        Evento(62, 70, 18, "Gol", 0, "Di María"),
        Evento(63, 70, 34, "Gol", 0, "Taremi"),
        Evento(64, 70, 56, "Gol", 0, "Neres"),
        Evento(65, 70, 78, "Gol", 0, "Aursnes"),
        
        // Partido 82: Barcelona 2-1 Inter
        Evento(66, 82, 22, "Gol", 0, "Lamine Yamal"),
        Evento(67, 82, 45, "Gol", 0, "Lautaro"),
        Evento(68, 82, 89, "Gol", 0, "Lewandowski")
    )

    val ALINEACIONES = listOf(
        // Partido 3: Barcelona vs Real Madrid
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

    // --- 6. CLASIFICACIÓN (Todas las ligas) ---
    val CLASIFICACION = listOf(
        // LaLiga EA Sports (Liga 1)
        Clasificacion("1", 101, "Real Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", 1, 52, 20, 16, 4, 0),
        Clasificacion("1", 102, "FC Barcelona", "https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/1200px-FC_Barcelona_%28crest%29.svg.png", 2, 48, 20, 15, 3, 2),
        Clasificacion("1", 103, "Atlético de Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f4/Atletico_Madrid_2017_logo.svg/1200px-Atletico_Madrid_2017_logo.svg.png", 3, 42, 20, 13, 3, 4),
        Clasificacion("1", 110, "Girona FC", "https://upload.wikimedia.org/wikipedia/en/thumb/e/e2/Girona_FC_Logo.svg/1200px-Girona_FC_Logo.svg.png", 4, 40, 20, 12, 4, 4),
        Clasificacion("1", 108, "Athletic Club", "https://upload.wikimedia.org/wikipedia/en/thumb/9/98/Club_Athletic_Bilbao_logo.svg/1200px-Club_Athletic_Bilbao_logo.svg.png", 5, 38, 20, 11, 5, 4),
        Clasificacion("1", 105, "Real Sociedad", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f1/Real_Sociedad_logo.svg/1200px-Real_Sociedad_logo.svg.png", 6, 36, 20, 10, 6, 4),
        Clasificacion("1", 106, "Villarreal CF", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/Villarreal_CF_logo.svg/1200px-Villarreal_CF_logo.svg.png", 7, 34, 20, 10, 4, 6),
        Clasificacion("1", 107, "Real Betis", "https://upload.wikimedia.org/wikipedia/en/thumb/1/13/Real_betis_logo.svg/1200px-Real_betis_logo.svg.png", 8, 32, 20, 9, 5, 6),
        Clasificacion("1", 104, "Sevilla FC", "https://upload.wikimedia.org/wikipedia/en/thumb/3/3b/Sevilla_FC_logo.svg/1200px-Sevilla_FC_logo.svg.png", 9, 28, 20, 8, 4, 8),
        Clasificacion("1", 109, "Valencia CF", "https://upload.wikimedia.org/wikipedia/en/thumb/c/ce/Valenciacf.svg/1200px-Valenciacf.svg.png", 10, 25, 20, 7, 4, 9),
        
        // Premier League (Liga 2)
        Clasificacion("2", 201, "Manchester City", "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Manchester_City_FC_badge.svg/1200px-Manchester_City_FC_badge.svg.png", 1, 54, 21, 17, 3, 1),
        Clasificacion("2", 202, "Liverpool", "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/1200px-Liverpool_FC.svg.png", 2, 51, 21, 16, 3, 2),
        Clasificacion("2", 203, "Arsenal", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Arsenal_FC.svg/1200px-Arsenal_FC.svg.png", 3, 47, 21, 14, 5, 2),
        Clasificacion("2", 208, "Aston Villa", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f9/Aston_Villa_FC_crest_%282016%29.svg/1200px-Aston_Villa_FC_crest_%282016%29.svg.png", 4, 41, 21, 12, 5, 4),
        Clasificacion("2", 206, "Tottenham", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b4/Tottenham_Hotspur.svg/1200px-Tottenham_Hotspur.svg.png", 5, 40, 21, 12, 4, 5),
        Clasificacion("2", 207, "Newcastle United", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Newcastle_United_Logo.svg/1200px-Newcastle_United_Logo.svg.png", 6, 39, 21, 11, 6, 4),
        Clasificacion("2", 204, "Chelsea", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/Chelsea_FC.svg/1200px-Chelsea_FC.svg.png", 7, 38, 21, 11, 5, 5),
        Clasificacion("2", 205, "Manchester United", "https://upload.wikimedia.org/wikipedia/en/thumb/7/7a/Manchester_United_FC_crest.svg/1200px-Manchester_United_FC_crest.svg.png", 8, 32, 21, 9, 5, 7),
        
        // Serie A (Liga 3)
        Clasificacion("3", 303, "Inter de Milán", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/FC_Internazionale_Milano_2021.svg/1200px-FC_Internazionale_Milano_2021.svg.png", 1, 50, 19, 16, 2, 1),
        Clasificacion("3", 301, "Juventus", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Juventus_FC_2017_icon_%28black%29.svg/1200px-Juventus_FC_2017_icon_%28black%29.svg.png", 2, 43, 19, 13, 4, 2),
        Clasificacion("3", 305, "Napoli", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/SSC_Neapel.svg/1200px-SSC_Neapel.svg.png", 3, 41, 19, 12, 5, 2),
        Clasificacion("3", 302, "AC Milan", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Logo_of_AC_Milan.svg/1200px-Logo_of_AC_Milan.svg.png", 4, 39, 19, 11, 6, 2),
        Clasificacion("3", 308, "Atalanta", "https://upload.wikimedia.org/wikipedia/en/thumb/6/66/AtalantaBC.svg/1200px-AtalantaBC.svg.png", 5, 37, 19, 11, 4, 4),
        Clasificacion("3", 306, "Lazio", "https://upload.wikimedia.org/wikipedia/en/thumb/7/71/SS_Lazio.svg/1200px-SS_Lazio.svg.png", 6, 35, 19, 10, 5, 4),
        Clasificacion("3", 307, "Fiorentina", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/ACF_Fiorentina_2022.svg/1200px-ACF_Fiorentina_2022.svg.png", 7, 34, 19, 10, 4, 5),
        Clasificacion("3", 304, "AS Roma", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f7/AS_Roma_logo_%282017%29.svg/1200px-AS_Roma_logo_%282017%29.svg.png", 8, 34, 19, 10, 4, 5),
        
        // Bundesliga (Liga 4)
        Clasificacion("4", 404, "Bayer Leverkusen", "https://upload.wikimedia.org/wikipedia/en/thumb/5/59/Bayer_04_Leverkusen_logo.svg/1200px-Bayer_04_Leverkusen_logo.svg.png", 1, 49, 18, 15, 4, 0),
        Clasificacion("4", 401, "Bayern Munich", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg/1200px-FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg.png", 2, 46, 18, 14, 4, 0),
        Clasificacion("4", 406, "VfB Stuttgart", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/VfB_Stuttgart_1893_Logo.svg/1200px-VfB_Stuttgart_1893_Logo.svg.png", 3, 42, 18, 13, 3, 2),
        Clasificacion("4", 402, "Borussia Dortmund", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Borussia_Dortmund_logo.svg/1200px-Borussia_Dortmund_logo.svg.png", 4, 40, 18, 12, 4, 2),
        Clasificacion("4", 403, "RB Leipzig", "https://upload.wikimedia.org/wikipedia/en/thumb/0/04/RB_Leipzig_2014_logo.svg/1200px-RB_Leipzig_2014_logo.svg.png", 5, 35, 18, 10, 5, 3),
        Clasificacion("4", 405, "Eintracht Frankfurt", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/Eintracht_Frankfurt_Logo.svg/1200px-Eintracht_Frankfurt_Logo.svg.png", 6, 32, 18, 9, 5, 4),
        
        // Ligue 1 (Liga 5)
        Clasificacion("5", 501, "PSG", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a7/Paris_Saint-Germain_F.C..svg/1200px-Paris_Saint-Germain_F.C..svg.png", 1, 53, 19, 17, 2, 0),
        Clasificacion("5", 503, "Mónaco", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cf/AS_Monaco_FC.svg/1200px-AS_Monaco_FC.svg.png", 2, 41, 19, 12, 5, 2),
        Clasificacion("5", 505, "Lille", "https://upload.wikimedia.org/wikipedia/en/thumb/7/7e/Lille_OSC_2018_logo.svg/1200px-Lille_OSC_2018_logo.svg.png", 3, 39, 19, 11, 6, 2),
        Clasificacion("5", 504, "Lyon", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a5/Olympique_Lyonnais.svg/1200px-Olympique_Lyonnais.svg.png", 4, 38, 19, 11, 5, 3),
        Clasificacion("5", 502, "Olympique de Marsella", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Olympique_Marseille_logo.svg/1200px-Olympique_Marseille_logo.svg.png", 5, 35, 19, 10, 5, 4),
        
        // Eredivisie (Liga 9)
        Clasificacion("9", 602, "PSV Eindhoven", "https://upload.wikimedia.org/wikipedia/en/thumb/0/05/PSV_Eindhoven.svg/1200px-PSV_Eindhoven.svg.png", 1, 48, 18, 15, 3, 0),
        Clasificacion("9", 603, "Feyenoord", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cd/Feyenoord_logo.svg/1200px-Feyenoord_logo.svg.png", 2, 42, 18, 13, 3, 2),
        Clasificacion("9", 601, "Ajax", "https://upload.wikimedia.org/wikipedia/en/thumb/7/79/Ajax_Amsterdam.svg/1200px-Ajax_Amsterdam.svg.png", 3, 38, 18, 11, 5, 2),
        
        // Liga Portugal (Liga 10)
        Clasificacion("10", 701, "Benfica", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a2/SL_Benfica_logo.svg/1200px-SL_Benfica_logo.svg.png", 1, 45, 17, 14, 3, 0),
        Clasificacion("10", 703, "Sporting CP", "https://upload.wikimedia.org/wikipedia/en/thumb/e/e1/Sporting_Clube_de_Portugal_%28Logo%29.svg/1200px-Sporting_Clube_de_Portugal_%28Logo%29.svg.png", 2, 42, 17, 13, 3, 1),
        Clasificacion("10", 702, "Porto", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f1/FC_Porto.svg/1200px-FC_Porto.svg.png", 3, 39, 17, 12, 3, 2),
        
        // LaLiga Hypermotion (Liga 6)
        Clasificacion("6", 150, "Racing de Santander", "https://upload.wikimedia.org/wikipedia/en/thumb/5/51/Racing_de_Santander_logo.svg/1200px-Racing_de_Santander_logo.svg.png", 1, 38, 20, 11, 5, 4),
        Clasificacion("6", 153, "Levante UD", "https://upload.wikimedia.org/wikipedia/en/thumb/7/7b/Levante_Uni%C3%B3n_Deportiva%2C_S.A.D._logo.svg/1200px-Levante_Uni%C3%B3n_Deportiva%2C_S.A.D._logo.svg.png", 2, 36, 20, 10, 6, 4),
        Clasificacion("6", 152, "Sporting de Gijón", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f8/Real_Sporting_de_Gij%C3%B3n_logo.svg/1200px-Real_Sporting_de_Gij%C3%B3n_logo.svg.png", 3, 35, 20, 10, 5, 5),
        Clasificacion("6", 151, "Real Zaragoza", "https://upload.wikimedia.org/wikipedia/en/thumb/8/8e/Real_Zaragoza_logo.svg/1200px-Real_Zaragoza_logo.svg.png", 4, 33, 20, 9, 6, 5),
        
        // Championship (Liga 8)
        Clasificacion("8", 250, "Leeds United", "https://upload.wikimedia.org/wikipedia/en/thumb/5/54/Leeds_United_F.C._logo.svg/1200px-Leeds_United_F.C._logo.svg.png", 1, 50, 24, 15, 5, 4),
        Clasificacion("8", 251, "Sheffield United", "https://upload.wikimedia.org/wikipedia/en/thumb/9/9c/Sheffield_United_FC_logo.svg/1200px-Sheffield_United_FC_logo.svg.png", 2, 45, 24, 13, 6, 5)
    )
}