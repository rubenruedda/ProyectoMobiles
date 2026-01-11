package dao

import androidx.lifecycle.LiveData
import androidx.room.*
import model.Clasificacion
import model.Equipo
import model.Liga

@Dao
interface LigaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarLigas(ligas: List<Liga>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEquipos(equipos: List<Equipo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarClasificacion(items: List<Clasificacion>)

    @Update
    suspend fun actualizarLiga(liga: Liga)

    @Update
    suspend fun actualizarEquipo(equipo: Equipo)

    @Query("SELECT * FROM liga ORDER BY pais_nombre, nombre ASC")
    fun obtenerTodasLasLigas(): LiveData<List<Liga>>

    // Búsqueda de Ligas
    @Query("SELECT * FROM liga WHERE nombre LIKE '%' || :query || '%' OR pais_nombre LIKE '%' || :query || '%' ORDER BY pais_nombre, nombre ASC")
    fun buscarLigas(query: String): LiveData<List<Liga>>

    @Query("SELECT * FROM liga WHERE id = :ligaId LIMIT 1")
    fun obtenerLigaPorId(ligaId: String): LiveData<Liga>

    @Query("SELECT * FROM clasificacion WHERE liga_id = :ligaId ORDER BY posicion ASC")
    fun obtenerClasificacionPorLiga(ligaId: String): LiveData<List<Clasificacion>>

    @Query("SELECT * FROM liga WHERE id IN (SELECT refId FROM favoritos WHERE tipo = 'LIGA')")
    fun obtenerLigasFavoritas(): LiveData<List<Liga>>

    @Query("SELECT * FROM equipo WHERE es_favorito = 1")
    fun obtenerEquiposFavoritos(): LiveData<List<Equipo>>
}