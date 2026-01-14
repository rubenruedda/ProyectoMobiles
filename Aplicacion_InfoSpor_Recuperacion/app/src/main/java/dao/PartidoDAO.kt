package dao

import androidx.lifecycle.LiveData
import androidx.room.*
import model.Alineacion
import model.Evento
import model.Liga
import model.Partido

@Dao
interface PartidoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarPartidos(partidos: List<Partido>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEventos(eventos: List<Evento>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAlineaciones(alineaciones: List<Alineacion>)

    @Update
    suspend fun actualizarPartido(partido: Partido)

    // Consultas Básicas
    @Query("SELECT * FROM partido WHERE fecha = :fechaHoy ORDER BY hora ASC")
    fun obtenerPartidosDeHoy(fechaHoy: String): LiveData<List<Partido>>

    // Consulta de Búsqueda (Filtrar partidos de hoy por nombre de equipo)
    @Query("SELECT * FROM partido WHERE fecha = :fechaHoy AND (nombreLocal LIKE '%' || :query || '%' OR nombreVisitante LIKE '%' || :query || '%') ORDER BY hora ASC")
    fun buscarPartidosDeHoy(fechaHoy: String, query: String): LiveData<List<Partido>>

    // Filtrar por Liga (Importante para que no salgan mezclados)
    @Query("SELECT * FROM partido WHERE liga_id = :ligaId AND marcador_local IS NOT NULL ORDER BY fecha DESC")
    fun obtenerResultadosPorLiga(ligaId: String): LiveData<List<Partido>>

    @Query("SELECT * FROM partido WHERE liga_id = :ligaId AND marcador_local IS NULL ORDER BY fecha ASC")
    fun obtenerPartidosProximosPorLiga(ligaId: String): LiveData<List<Partido>>

    @Query("SELECT * FROM partido WHERE id = :partidoId LIMIT 1")
    fun obtenerPartidoPorId(partidoId: Int): LiveData<Partido>

    @Query("SELECT * FROM evento WHERE partido_id = :partidoId ORDER BY minuto ASC")
    fun obtenerEventosPorPartido(partidoId: Int): LiveData<List<Evento>>

    @Query("SELECT * FROM alineacion WHERE partido_id = :partidoId AND equipo_id = :equipoId ORDER BY es_titular DESC, dorsal ASC")
    fun obtenerAlineacionPorEquipo(partidoId: Int, equipoId: Int): LiveData<List<Alineacion>>

    @Query("SELECT * FROM partido WHERE es_favorito = 1")
    fun obtenerPartidosFavoritos(): LiveData<List<Partido>>
}