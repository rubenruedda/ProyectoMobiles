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
    }
}