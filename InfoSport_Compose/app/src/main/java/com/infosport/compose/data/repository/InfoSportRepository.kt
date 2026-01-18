package com.infosport.compose.data.repository

import com.infosport.compose.data.dao.LigaDao
import com.infosport.compose.data.dao.NoticiaDao
import com.infosport.compose.data.dao.PartidoDao
import com.infosport.compose.data.model.*
import kotlinx.coroutines.flow.Flow

class InfoSportRepository(
    private val ligaDao: LigaDao,
    private val partidoDao: PartidoDao,
    private val noticiaDao: NoticiaDao
) {
    // Ligas
    fun obtenerTodasLasLigas(): Flow<List<Liga>> = ligaDao.obtenerTodasLasLigas()
    fun obtenerLigasFavoritas(): Flow<List<Liga>> = ligaDao.obtenerLigasFavoritas()
    fun obtenerLigaPorId(ligaId: String): Flow<Liga?> = ligaDao.obtenerLigaPorId(ligaId)
    suspend fun toggleLigaFavorita(ligaId: String, esFavorita: Boolean) = ligaDao.actualizarFavorito(ligaId, esFavorita)

    // Partidos
    fun obtenerTodosLosPartidos(): Flow<List<Partido>> = partidoDao.obtenerTodosLosPartidos()
    fun obtenerPartidosPorFecha(fecha: String): Flow<List<Partido>> = partidoDao.obtenerPartidosPorFecha(fecha)
    fun obtenerPartidosPorLiga(ligaId: String): Flow<List<Partido>> = partidoDao.obtenerPartidosPorLiga(ligaId)
    fun obtenerResultadosPorLiga(ligaId: String): Flow<List<Partido>> = partidoDao.obtenerResultadosPorLiga(ligaId)
    fun obtenerProximosPartidosPorLiga(ligaId: String): Flow<List<Partido>> = partidoDao.obtenerProximosPartidosPorLiga(ligaId)
    fun obtenerPartidosFavoritos(): Flow<List<Partido>> = partidoDao.obtenerPartidosFavoritos()
    fun obtenerPartidoPorId(partidoId: Int): Flow<Partido?> = partidoDao.obtenerPartidoPorId(partidoId)
    suspend fun togglePartidoFavorito(partidoId: Int, esFavorito: Boolean) = partidoDao.actualizarFavorito(partidoId, esFavorito)

    // Eventos
    fun obtenerEventosPorPartido(partidoId: Int): Flow<List<Evento>> = partidoDao.obtenerEventosPorPartido(partidoId)

    // Clasificación
    fun obtenerClasificacionPorLiga(ligaId: String): Flow<List<Clasificacion>> = partidoDao.obtenerClasificacionPorLiga(ligaId)

    // Noticias
    fun obtenerTodasLasNoticias(): Flow<List<Noticia>> = noticiaDao.obtenerTodasLasNoticias()
    fun obtenerNoticiasFavoritas(): Flow<List<Noticia>> = noticiaDao.obtenerNoticiasFavoritas()
    fun obtenerNoticiaPorId(noticiaId: Int): Flow<Noticia?> = noticiaDao.obtenerNoticiaPorId(noticiaId)
    fun buscarNoticias(query: String): Flow<List<Noticia>> = noticiaDao.buscarNoticias(query)
    suspend fun toggleNoticiaFavorita(noticiaId: Int, esFavorita: Boolean) = noticiaDao.actualizarFavorito(noticiaId, esFavorita)
}
