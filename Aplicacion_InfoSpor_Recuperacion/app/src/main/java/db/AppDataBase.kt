package db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dao.FavoritoDAO
import dao.LigaDAO
import dao.NoticiaDAO
import dao.PartidoDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.*
import utils.PreCargaDatos

@Database(
    entities = [
        Liga::class,
        Equipo::class,
        Partido::class,
        Clasificacion::class,
        Noticia::class,
        Jugador::class,
        Evento::class,
        Alineacion::class,
        Favorito::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun partidoDao(): PartidoDAO
    abstract fun ligaDao(): LigaDAO
    abstract fun noticiaDao(): NoticiaDAO
    abstract fun favoritoDao(): FavoritoDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "info_sports_db_v2"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        /*private fun roomDatabaseCallback(): Callback {
            return object : Callback() {
                // Se ejecuta SOLO cuando la base de datos se crea por primera vez
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    INSTANCE?.let { database ->
                        CoroutineScope(Dispatchers.IO).launch {
                            poblarBaseDeDatos(database)
                        }
                    }
                }
            }
        }

        private suspend fun poblarBaseDeDatos(db: AppDataBase) {
            // Limpiar todo (por seguridad)
            // db.clearAllTables() // Opcional si usamos onCreate

            // Insertar datos masivos
            db.ligaDao().insertarLigas(PreCargaDatos.LIGAS)
            db.ligaDao().insertarEquipos(PreCargaDatos.EQUIPOS)
            // db.ligaDao().insertarClasificacion(PreCargaDatos.CLASIFICACION) // Si tienes datos de clasificación

            db.partidoDao().insertarPartidos(PreCargaDatos.PARTIDOS)
            db.partidoDao().insertarEventos(PreCargaDatos.EVENTOS)
            db.partidoDao().insertarAlineaciones(PreCargaDatos.ALINEACIONES)

            db.noticiaDao().insertarNoticias(PreCargaDatos.NOTICIAS)
        }*/
    }
}