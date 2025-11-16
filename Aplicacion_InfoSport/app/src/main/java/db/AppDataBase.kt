package db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dao.LigaDAO
import dao.NoticiaDAO
import dao.PartidoDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.Alineacion
import model.Clasificacion
import model.Equipo
import model.Evento
import model.Jugador
import model.Liga
import model.Noticia
import model.Partido
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
        Alineacion::class
    ],
    version = 1, // Incrementa esto si cambias el esquema
    exportSchema = false // Recomendado por el PDF para simplificar
)
abstract class AppDataBase : RoomDatabase() {

    // 2. MÉTODOS ABSTRACTOS PARA LOS DAOs
    // Room implementará estos métodos por ti.
    abstract fun partidoDao(): PartidoDAO
    abstract fun ligaDao(): LigaDAO
    abstract fun noticiaDao(): NoticiaDAO
    // (Añade aquí cualquier otro DAO que crees, ej: JugadorDAO)


    // 3. PATRÓN SINGLETON (Companion Object)
    // Esto asegura que solo exista UNA instancia de la base de datos
    // en toda la aplicación, evitando problemas de memoria.
    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "info_sports_db_v2" // <-- 1. El nombre nuevo
                )
                    .addCallback(roomDatabaseCallback()) // <-- 2. Llamada al callback
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // --- CÓDIGO NUEVO Y CORREGIDO ---
        // Callback para pre-cargar la base de datos
        private fun roomDatabaseCallback(): Callback {
            return object : Callback() {

                // Se llama CADA VEZ que la BBDD se abre
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)

                    val dbInstance = INSTANCE ?: return // Si no hay BBDD, no hagas nada

                    // Lanzamos una corrutina para no bloquear el hilo
                    CoroutineScope(Dispatchers.IO).launch {

                        // Comprueba si la BBDD ya tiene datos
                        if (dbInstance.ligaDao().getLigaCount() == 0) {
                            // Está vacía, ¡vamos a llenarla!
                            poblarBaseDeDatos(dbInstance)
                        }
                        // Si getLigaCount() > 0, no hace nada y la app usa los datos existentes
                    }
                }
            }
        }

        // Nueva función de ayuda para poblar
        private suspend fun poblarBaseDeDatos(db: AppDataBase) {
            // Insertamos todos los datos de la clase PreCargaDatos
            db.ligaDao().insertarLigas(PreCargaDatos.LIGAS)
            db.ligaDao().insertarEquipos(PreCargaDatos.EQUIPOS)
            db.ligaDao().insertarClasificacion(PreCargaDatos.CLASIFICACION)

            db.partidoDao().insertarPartidos(PreCargaDatos.PARTIDOS)
            db.partidoDao().insertarEventos(PreCargaDatos.EVENTOS)
            db.partidoDao().insertarAlineaciones(PreCargaDatos.ALINEACIONES)

            db.noticiaDao().insertarNoticias(PreCargaDatos.NOTICIAS)
        }
    }
}