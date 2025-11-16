package respository

import androidx.lifecycle.LiveData
import dao.LigaDAO
import dao.NoticiaDAO
import dao.PartidoDAO
import model.Alineacion
import model.Clasificacion
import model.Equipo
import model.Evento
import model.Liga
import model.Noticia
import model.Partido

class InfoSportRepository (
    private val partidoDao: PartidoDAO,
    private val ligaDao: LigaDAO,
    private val noticiaDao: NoticiaDAO
    ) {

        // 2. MÉTODOS DE LECTURA (LIVEDATA)
        // Estos métodos simplemente llaman al DAO y devuelven el LiveData.
        // La Vista (Fragment) observará estos LiveData.

        // --- Métodos para Partidos ---
        fun obtenerPartidosDeHoy(fechaHoy: String): LiveData<List<Partido>> {
            return partidoDao.obtenerPartidosDeHoy(fechaHoy)
        }

        fun buscarPartidosDeHoy(fechaHoy: String, query: String): LiveData<List<Partido>> {
            return partidoDao.buscarPartidosDeHoy(fechaHoy, query)
        }

        fun obtenerResultadosPorLiga(ligaId: String): LiveData<List<Partido>> {
            return partidoDao.obtenerResultadosPorLiga(ligaId)
        }

        fun obtenerPartidosProximosPorLiga(ligaId: String): LiveData<List<Partido>> {
            return partidoDao.obtenerPartidosProximosPorLiga(ligaId)
        }

        // --- Métodos para Detalle de Partido (InfoPartidoActivity) ---
        fun obtenerPartidoPorId(partidoId: Int): LiveData<Partido> {
            return partidoDao.obtenerPartidoPorId(partidoId)
        }

        fun obtenerEventosPorPartido(partidoId: Int): LiveData<List<Evento>> {
            return partidoDao.obtenerEventosPorPartido(partidoId)
        }

        fun obtenerAlineacionPorEquipo(partidoId: Int, equipoId: Int): LiveData<List<Alineacion>> {
            return partidoDao.obtenerAlineacionPorEquipo(partidoId, equipoId)
        }

        // --- Métodos para Ligas y Clasificación ---
        fun obtenerTodasLasLigas(): LiveData<List<Liga>> {
            return ligaDao.obtenerTodasLasLigas()
        }

        fun buscarLigas(query: String): LiveData<List<Liga>> {
            return ligaDao.buscarLigas(query)
        }

        fun obtenerLigaPorId(ligaId: String): LiveData<Liga> {
            return ligaDao.obtenerLigaPorId(ligaId)
        }

        fun obtenerClasificacionPorLiga(ligaId: String): LiveData<List<Clasificacion>> {
            return ligaDao.obtenerClasificacionPorLiga(ligaId)
        }

        // --- Métodos para Noticias ---
        fun obtenerTodasLasNoticias(): LiveData<List<Noticia>> {
            return noticiaDao.obtenerTodasLasNoticias()
        }

        fun buscarNoticias(query: String): LiveData<List<Noticia>> {
            return noticiaDao.buscarNoticias(query)
        }

        fun obtenerNoticiaPorId(noticiaId: Int): LiveData<Noticia> {
            return noticiaDao.obtenerNoticiaPorId(noticiaId)
        }

        // --- Métodos para Favoritos ---
        fun obtenerLigasFavoritas(): LiveData<List<Liga>> {
            return ligaDao.obtenerLigasFavoritas()
        }

        fun obtenerEquiposFavoritos(): LiveData<List<Equipo>> {
            return ligaDao.obtenerEquiposFavoritos()
        }

        // 3. MÉTODOS DE ESCRITURA (SUSPEND)
        // Estas funciones deben ser llamadas desde una Coroutine (viewModelScope).

        @Suppress("RedundantSuspendModifier") // Room maneja 'suspend' en sus DAOs
        suspend fun insertarPartidos(partidos: List<Partido>) {
            partidoDao.insertarPartidos(partidos)
        }

        @Suppress("RedundantSuspendModifier")
        suspend fun insertarLigas(ligas: List<Liga>) {
            ligaDao.insertarLigas(ligas)
        }

        @Suppress("RedundantSuspendModifier")
        suspend fun actualizarLiga(liga: Liga) {
            ligaDao.actualizarLiga(liga)
        }

        @Suppress("RedundantSuspendModifier")
        suspend fun actualizarEquipo(equipo: Equipo) {
            ligaDao.actualizarEquipo(equipo)
        }

        // ... (Añade aquí el resto de funciones suspend de escritura que necesites,
        // como insertarNoticias, insertarClasificacion, borrarPartido, etc.)
    }