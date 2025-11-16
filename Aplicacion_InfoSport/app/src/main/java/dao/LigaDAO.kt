package dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import model.Clasificacion
import model.Equipo
import model.Liga

@Dao
interface LigaDAO {

    // --- ESCRITURA (SUSPEND) ---

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarLigas(ligas: List<Liga>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEquipos(equipos: List<Equipo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarClasificacion(items: List<Clasificacion>)

    // Actualizar Ligas/Equipos (para marcar/desmarcar favoritos)
    @Update
    suspend fun actualizarLiga(liga: Liga)

    @Update
    suspend fun actualizarEquipo(equipo: Equipo)

    // --- LECTURA (LIVEDATA) ---

    // Consulta para CompeticionesActivity
    @Query("SELECT * FROM liga ORDER BY pais_nombre, nombre ASC")
    fun obtenerTodasLasLigas(): LiveData<List<Liga>>

    @Query("SELECT * FROM liga WHERE nombre LIKE '%' || :query || '%' OR pais_nombre LIKE '%' || :query || '%' ORDER BY pais_nombre, nombre ASC")
    fun buscarLigas(query: String): LiveData<List<Liga>>

    // Consulta para ClasificacionFragment
    @Query("SELECT * FROM clasificacion WHERE liga_id = :ligaId ORDER BY posicion ASC")
    fun obtenerClasificacionPorLiga(ligaId: String): LiveData<List<Clasificacion>>

    // Consultas para FavoritosActivity
    @Query("SELECT * FROM liga WHERE es_favorita = 1")
    fun obtenerLigasFavoritas(): LiveData<List<Liga>>

    @Query("SELECT * FROM equipo WHERE es_favorito = 1")
    fun obtenerEquiposFavoritos(): LiveData<List<Equipo>>

    @Query("SELECT * FROM liga WHERE id = :ligaId LIMIT 1")
    fun obtenerLigaPorId(ligaId: String): LiveData<Liga>

    @Query("SELECT COUNT(*) FROM liga")
    suspend fun getLigaCount(): Int
}