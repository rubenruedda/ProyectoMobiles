package respository

import androidx.lifecycle.LiveData
import dao.LigaDAO
import dao.NoticiaDAO
import dao.PartidoDAO
import model.*

class InfoSportRepository (
    private val partidoDao: PartidoDAO,
    private val ligaDao: LigaDAO,
    private val noticiaDao: NoticiaDAO
) {
    // --- Partidos ---
    fun obtenerPartidosDeHoy(fechaHoy: String): LiveData<List<Partido>> = partidoDao.obtenerPartidosDeHoy(fechaHoy)
    fun buscarPartidosDeHoy(fechaHoy: String, query: String): LiveData<List<Partido>> = partidoDao.buscarPartidosDeHoy(fechaHoy, query)
    fun obtenerPartidosFavoritos(): LiveData<List<Partido>> = partidoDao.obtenerPartidosFavoritos()
    fun obtenerResultadosPorLiga(ligaId: String): LiveData<List<Partido>> = partidoDao.obtenerResultadosPorLiga(ligaId)
    fun obtenerPartidosProximosPorLiga(ligaId: String): LiveData<List<Partido>> = partidoDao.obtenerPartidosProximosPorLiga(ligaId)
    fun obtenerPartidoPorId(partidoId: Int): LiveData<Partido> = partidoDao.obtenerPartidoPorId(partidoId)
    fun obtenerEventosPorPartido(partidoId: Int): LiveData<List<Evento>> = partidoDao.obtenerEventosPorPartido(partidoId)
    fun obtenerAlineacionPorEquipo(partidoId: Int, equipoId: Int): LiveData<List<Alineacion>> = partidoDao.obtenerAlineacionPorEquipo(partidoId, equipoId)

    // --- Ligas ---
    fun obtenerTodasLasLigas(): LiveData<List<Liga>> = ligaDao.obtenerTodasLasLigas()
    fun buscarLigas(query: String): LiveData<List<Liga>> = ligaDao.buscarLigas(query)
    fun obtenerLigaPorId(ligaId: String): LiveData<Liga> = ligaDao.obtenerLigaPorId(ligaId)
    fun obtenerClasificacionPorLiga(ligaId: String): LiveData<List<Clasificacion>> = ligaDao.obtenerClasificacionPorLiga(ligaId)
    fun obtenerLigasFavoritas(): LiveData<List<Liga>> = ligaDao.obtenerLigasFavoritas()

    // --- Noticias ---
    fun obtenerTodasLasNoticias(): LiveData<List<Noticia>> = noticiaDao.obtenerTodasLasNoticias()
    fun buscarNoticias(query: String): LiveData<List<Noticia>> = noticiaDao.buscarNoticias(query)
    fun obtenerNoticiaPorId(noticiaId: Int): LiveData<Noticia> = noticiaDao.obtenerNoticiaPorId(noticiaId)
    fun obtenerNoticiasFavoritas(): LiveData<List<Noticia>> = noticiaDao.obtenerNoticiasFavoritas()

    // --- Escritura ---
    suspend fun actualizarLiga(liga: Liga) = ligaDao.actualizarLiga(liga)
    suspend fun actualizarPartido(partido: Partido) = partidoDao.actualizarPartido(partido)
    suspend fun actualizarNoticia(noticia: Noticia) = noticiaDao.actualizarNoticia(noticia)
}