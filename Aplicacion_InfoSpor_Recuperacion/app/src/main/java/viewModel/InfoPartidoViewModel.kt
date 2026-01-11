package viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import db.AppDataBase
import kotlinx.coroutines.launch
import model.Alineacion
import model.Evento
import model.Partido
import model.PartidoDetalle
import respository.InfoSportRepository

class InfoPartidoViewModel(application: Application) : AndroidViewModel(application) {

    val Context.repository: InfoSportRepository
        get() {
            val db = AppDataBase.getDatabase(this)
            return InfoSportRepository(
                partidoDao = db.partidoDao(),
                ligaDao = db.ligaDao(),
                noticiaDao = db.noticiaDao(),
                favoritoDao = db.favoritoDao()
            )
        }

    private val repository: InfoSportRepository

    private val _partidoId = MutableLiveData<Int>()

    val partido: LiveData<Partido> = _partidoId.switchMap { id ->
        repository.obtenerPartidoPorId(id)
    }

    val partidoDetalleCompleto = MediatorLiveData<PartidoDetalle>()

    init {
        val context = application.applicationContext
        val db = AppDataBase.getDatabase(context)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao(), db.favoritoDao())
        partidoDetalleCompleto.addSource(partido) { partidoActual ->
            partidoActual?.let {
                // 1. Obtener Eventos
                val eventosLiveData = repository.obtenerEventosPorPartido(it.id)

                // 2. Obtener Alineación Local
                val alineacionLocalLiveData = repository.obtenerAlineacionPorEquipo(it.id, it.equipoLocalId)

                // 3. Obtener Alineación Visitante
                val alineacionVisitanteLiveData = repository.obtenerAlineacionPorEquipo(it.id, it.equipoVisitanteId)
            }
        }
    }

    fun esFavorito(partidoId: Int): LiveData<Boolean> {
        return repository.esFavorito("PARTIDO", partidoId.toString())
    }

    fun toggleFavorito(partidoId: Int, esActualmenteFavorito: Boolean) {
        viewModelScope.launch {
            if (esActualmenteFavorito) {
                repository.eliminarFavorito("PARTIDO", partidoId.toString())
            } else {
                repository.agregarFavorito("PARTIDO", partidoId.toString())
            }
        }
    }
    fun setPartidoId(partidoId: Int) {
        if (_partidoId.value != partidoId) {
            _partidoId.value = partidoId
        }
    }

    // El Fragment puede llamar a estos directamente (si no se usa MediatorLiveData)
    fun getEventos(partidoId: Int): LiveData<List<Evento>> {
        return repository.obtenerEventosPorPartido(partidoId)
    }

    fun getAlineacionLocal(partidoId: Int, equipoId: Int): LiveData<List<Alineacion>> {
        return repository.obtenerAlineacionPorEquipo(partidoId, equipoId)
    }

    fun getAlineacionVisitante(partidoId: Int, equipoId: Int): LiveData<List<Alineacion>> {
        return repository.obtenerAlineacionPorEquipo(partidoId, equipoId)
    }
}