package com.infosport.compose.data.dao

import androidx.room.*
import com.infosport.compose.data.model.Clasificacion
import com.infosport.compose.data.model.Evento
import com.infosport.compose.data.model.Partido
import kotlinx.coroutines.flow.Flow

@Dao
interface PartidoDao {
    @Query("SELECT * FROM partido ORDER BY fecha DESC, hora ASC")
    fun obtenerTodosLosPartidos(): Flow<List<Partido>>

    @Query("SELECT * FROM partido WHERE fecha = :fecha ORDER BY hora ASC")
    fun obtenerPartidosPorFecha(fecha: String): Flow<List<Partido>>

    @Query("SELECT * FROM partido WHERE liga_id = :ligaId ORDER BY fecha DESC, hora ASC")
    fun obtenerPartidosPorLiga(ligaId: String): Flow<List<Partido>>

    @Query("SELECT * FROM partido WHERE liga_id = :ligaId AND marcador_local IS NOT NULL ORDER BY fecha DESC")
    fun obtenerResultadosPorLiga(ligaId: String): Flow<List<Partido>>

    @Query("SELECT * FROM partido WHERE liga_id = :ligaId AND marcador_local IS NULL ORDER BY fecha ASC")
    fun obtenerProximosPartidosPorLiga(ligaId: String): Flow<List<Partido>>

    @Query("SELECT * FROM partido WHERE es_favorito = 1 ORDER BY fecha DESC")
    fun obtenerPartidosFavoritos(): Flow<List<Partido>>

    @Query("SELECT * FROM partido WHERE id = :partidoId LIMIT 1")
    fun obtenerPartidoPorId(partidoId: Int): Flow<Partido?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarPartidos(partidos: List<Partido>)

    @Update
    suspend fun actualizarPartido(partido: Partido)

    @Query("UPDATE partido SET es_favorito = :esFavorito WHERE id = :partidoId")
    suspend fun actualizarFavorito(partidoId: Int, esFavorito: Boolean)

    // Eventos
    @Query("SELECT * FROM evento WHERE partido_id = :partidoId ORDER BY minuto ASC")
    fun obtenerEventosPorPartido(partidoId: Int): Flow<List<Evento>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEventos(eventos: List<Evento>)

    // Clasificación
    @Query("SELECT * FROM clasificacion WHERE liga_id = :ligaId ORDER BY posicion ASC")
    fun obtenerClasificacionPorLiga(ligaId: String): Flow<List<Clasificacion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarClasificacion(clasificaciones: List<Clasificacion>)
}
