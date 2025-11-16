package dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import model.Alineacion
import model.Evento
import model.Partido

@Dao
interface PartidoDAO {

    // --- FUNCIONES DE ESCRITURA (SUSPEND) ---

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarPartidos(partidos: List<Partido>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEventos(eventos: List<Evento>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAlineaciones(alineaciones: List<Alineacion>)

    @Update
    suspend fun actualizarPartido(partido: Partido)

    @Delete
    suspend fun borrarPartido(partido: Partido)

    // --- FUNCIONES DE LECTURA (LIVEDATA) ---

    // Consulta para InicioActivity (Ej: partidos de hoy)
    // Deberás adaptar 'YYYY-MM-DD' al formato de fecha que uses
    @Query("SELECT * FROM partido WHERE fecha = :fechaHoy ORDER BY hora ASC")
    fun obtenerPartidosDeHoy(fechaHoy: String): LiveData<List<Partido>>

    @Query("SELECT * FROM partido WHERE fecha = :fechaHoy AND (nombreLocal LIKE '%' || :query || '%' OR nombreVisitante LIKE '%' || :query || '%') ORDER BY hora ASC")
    fun buscarPartidosDeHoy(fechaHoy: String, query: String): LiveData<List<Partido>>

    // Consulta para LigaActivity (Resultados) - Marcador NO es nulo
    @Query("SELECT * FROM partido WHERE liga_id = :ligaId AND marcador_local IS NOT NULL ORDER BY fecha DESC")
    fun obtenerResultadosPorLiga(ligaId: String): LiveData<List<Partido>>

    // Consulta para LigaActivity (Próximos Partidos) - Marcador ES nulo
    @Query("SELECT * FROM partido WHERE liga_id = :ligaId AND marcador_local IS NULL ORDER BY fecha ASC")
    fun obtenerPartidosProximosPorLiga(ligaId: String): LiveData<List<Partido>>

    // Consulta para InfoPartidoActivity (Detalle Básico)
    @Query("SELECT * FROM partido WHERE id = :partidoId LIMIT 1")
    fun obtenerPartidoPorId(partidoId: Int): LiveData<Partido>

    // Consulta para InfoPartidoActivity (Eventos)
    @Query("SELECT * FROM evento WHERE partido_id = :partidoId ORDER BY minuto ASC")
    fun obtenerEventosPorPartido(partidoId: Int): LiveData<List<Evento>>

    // Consulta para InfoPartidoActivity (Alineaciones)
    @Query("SELECT * FROM alineacion WHERE partido_id = :partidoId AND equipo_id = :equipoId ORDER BY es_titular DESC, dorsal ASC")
    fun obtenerAlineacionPorEquipo(partidoId: Int, equipoId: Int): LiveData<List<Alineacion>>
}