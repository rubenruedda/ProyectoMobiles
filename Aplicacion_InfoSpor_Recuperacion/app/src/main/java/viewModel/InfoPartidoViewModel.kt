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
                noticiaDao = db.noticiaDao()
            )
        }

    private val repository: InfoSportRepository

    // ID interno del partido a consultar
    private val _partidoId = MutableLiveData<Int>()

    // LiveData que obtiene el Partido básico al cambiar _partidoId
    val partido: LiveData<Partido> = _partidoId.switchMap { id ->
        repository.obtenerPartidoPorId(id)
    }

    // LiveData combinado: contendrá el objeto PartidoDetalle completo
    val partidoDetalleCompleto = MediatorLiveData<PartidoDetalle>()

    init {
        val context = application.applicationContext
        val db = AppDataBase.getDatabase(context)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao())

        // Lógica de combinación de LiveData:
        // Cuando el LiveData del Partido básico cambia, usamos su información (IDs de equipo)
        // para lanzar las consultas de Eventos y Alineaciones, y construir el objeto final.
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

    // Función llamada por la Activity para establecer el partido a consultar
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

    fun toggleFavorito() {
        partido.value?.let { partidoActual ->
            viewModelScope.launch {
                partidoActual.esFavorita = !partidoActual.esFavorita
                repository.actualizarPartido(partidoActual)
            }
        }
    }
}