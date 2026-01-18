package com.infosport.compose.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.infosport.compose.data.dao.LigaDao
import com.infosport.compose.data.dao.NoticiaDao
import com.infosport.compose.data.dao.PartidoDao
import com.infosport.compose.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Liga::class,
        Equipo::class,
        Partido::class,
        Noticia::class,
        Evento::class,
        Clasificacion::class
    ],
    version = 1,
    exportSchema = false
)
abstract class InfoSportDatabase : RoomDatabase() {
    abstract fun ligaDao(): LigaDao
    abstract fun partidoDao(): PartidoDao
    abstract fun noticiaDao(): NoticiaDao

    companion object {
        @Volatile
        private var INSTANCE: InfoSportDatabase? = null

        fun getDatabase(context: Context): InfoSportDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InfoSportDatabase::class.java,
                    "infosport_database"
                )
                    .addCallback(DatabaseCallback())
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        precargarDatos(database)
                    }
                }
            }
        }

        private suspend fun precargarDatos(database: InfoSportDatabase) {
            val ligaDao = database.ligaDao()
            val partidoDao = database.partidoDao()
            val noticiaDao = database.noticiaDao()

            // Ligas
            val ligas = listOf(
                Liga("1", "LaLiga EA Sports", "España", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/LaLiga_logo_2023.svg/1200px-LaLiga_logo_2023.svg.png", true),
                Liga("2", "Premier League", "Inglaterra", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f2/Premier_League_Logo.svg/1200px-Premier_League_Logo.svg.png", false),
                Liga("3", "Serie A", "Italia", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Serie_A_logo_2022.svg/1200px-Serie_A_logo_2022.svg.png", false),
                Liga("4", "Bundesliga", "Alemania", "https://upload.wikimedia.org/wikipedia/en/thumb/d/df/Bundesliga_logo_%282017%29.svg/1200px-Bundesliga_logo_%282017%29.svg.png", false),
                Liga("5", "Ligue 1", "Francia", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Ligue_1_Uber_Eats_logo.svg/1200px-Ligue_1_Uber_Eats_logo.svg.png", false),
                Liga("6", "Champions League", "Europa", "https://upload.wikimedia.org/wikipedia/en/thumb/b/bf/UEFA_Champions_League_logo_2024.svg/1200px-UEFA_Champions_League_logo_2024.svg.png", true)
            )
            ligaDao.insertarLigas(ligas)

            // Partidos
            val partidos = listOf(
                Partido(1, "1", 101, 102, "Real Madrid", "FC Barcelona", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/1200px-FC_Barcelona_%28crest%29.svg.png", null, null, "17/01/2026", "21:00"),
                Partido(2, "1", 103, 104, "Atlético de Madrid", "Sevilla FC", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f4/Atletico_Madrid_2017_logo.svg/1200px-Atletico_Madrid_2017_logo.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/3/3b/Sevilla_FC_logo.svg/1200px-Sevilla_FC_logo.svg.png", null, null, "17/01/2026", "18:30"),
                Partido(3, "1", 102, 101, "FC Barcelona", "Real Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/1200px-FC_Barcelona_%28crest%29.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", "2", "3", "10/01/2026", "21:00"),
                Partido(4, "2", 201, 202, "Manchester City", "Liverpool", "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Manchester_City_FC_badge.svg/1200px-Manchester_City_FC_badge.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/1200px-Liverpool_FC.svg.png", null, null, "17/01/2026", "17:30"),
                Partido(5, "2", 203, 204, "Arsenal", "Chelsea", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Arsenal_FC.svg/1200px-Arsenal_FC.svg.png", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/Chelsea_FC.svg/1200px-Chelsea_FC.svg.png", "2", "1", "12/01/2026", "16:00"),
                Partido(6, "3", 301, 302, "AC Milan", "Juventus", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Logo_of_AC_Milan.svg/1200px-Logo_of_AC_Milan.svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Juventus_FC_2017_icon_%28black%29.svg/1200px-Juventus_FC_2017_icon_%28black%29.svg.png", "2", "2", "11/01/2026", "20:45"),
                Partido(7, "4", 401, 402, "Bayern Munich", "Borussia Dortmund", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg/1200px-FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Borussia_Dortmund_logo.svg/1200px-Borussia_Dortmund_logo.svg.png", null, null, "18/01/2026", "18:30"),
                Partido(8, "5", 501, 502, "PSG", "Marsella", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a7/Paris_Saint-Germain_F.C..svg/1200px-Paris_Saint-Germain_F.C..svg.png", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Olympique_Marseille_logo.svg/1200px-Olympique_Marseille_logo.svg.png", "4", "0", "14/01/2026", "21:00")
            )
            partidoDao.insertarPartidos(partidos)

            // Eventos
            val eventos = listOf(
                Evento(1, 3, 12, "Gol", 101, "Vinicius Jr."),
                Evento(2, 3, 38, "Gol", 102, "Lewandowski"),
                Evento(3, 3, 52, "Gol", 101, "Bellingham"),
                Evento(4, 3, 78, "Gol", 102, "Raphinha"),
                Evento(5, 3, 89, "Gol", 101, "Mbappé"),
                Evento(6, 5, 18, "Gol", 203, "Saka"),
                Evento(7, 5, 61, "Gol", 204, "Palmer"),
                Evento(8, 5, 88, "Gol", 203, "Havertz")
            )
            partidoDao.insertarEventos(eventos)

            // Clasificación
            val clasificacion = listOf(
                Clasificacion("1", 101, "Real Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png", 1, 52, 20, 16, 4, 0),
                Clasificacion("1", 102, "FC Barcelona", "https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/1200px-FC_Barcelona_%28crest%29.svg.png", 2, 48, 20, 15, 3, 2),
                Clasificacion("1", 103, "Atlético de Madrid", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f4/Atletico_Madrid_2017_logo.svg/1200px-Atletico_Madrid_2017_logo.svg.png", 3, 42, 20, 13, 3, 4),
                Clasificacion("2", 201, "Manchester City", "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/Manchester_City_FC_badge.svg/1200px-Manchester_City_FC_badge.svg.png", 1, 54, 21, 17, 3, 1),
                Clasificacion("2", 202, "Liverpool", "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/1200px-Liverpool_FC.svg.png", 2, 51, 21, 16, 3, 2),
                Clasificacion("2", 203, "Arsenal", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Arsenal_FC.svg/1200px-Arsenal_FC.svg.png", 3, 47, 21, 14, 5, 2)
            )
            partidoDao.insertarClasificacion(clasificacion)

            // Noticias
            val noticias = listOf(
                Noticia(1, "El Clásico paraliza el mundo", "Real Madrid y Barcelona se enfrentan en un duelo decisivo por el liderato de LaLiga.", "Marca", "17/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/b8wbxp9i3afuqr8sxm4i"),
                Noticia(2, "Haaland rompe otro récord", "El delantero noruego del Manchester City ha superado la marca de goles en una sola temporada.", "BBC Sport", "16/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/c9iswfyfyauo1xjzvcx9"),
                Noticia(3, "Mbappé triunfa en Madrid", "Kylian Mbappé se ha adaptado perfectamente al Real Madrid.", "L'Équipe", "15/01/2026", null),
                Noticia(4, "España se prepara para el Mundial", "La selección española ha comenzado su concentración.", "AS", "14/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/nqeevzoxwvnpsxhxvpxf"),
                Noticia(5, "Champions League: los favoritos", "Analizamos qué equipos tienen más opciones de ganar.", "UEFA", "13/01/2026", "https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_lg_2x/f_auto/primary/xvvvw87jhxuphvvdyefy")
            )
            noticiaDao.insertarNoticias(noticias)
        }
    }
}
