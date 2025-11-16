package viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import db.AppDataBase
import model.Clasificacion
import model.Partido
import respository.InfoSportRepository
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Liga

class LigaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: InfoSportRepository
    private val _ligaId = MutableLiveData<String>()

    private var ligaActual: Liga? = null // Para guardar el objeto actual

    // --- DATOS OBSERVABLES ---

    // LiveData de la LIGA actual (para saber si es favorita)
    val liga: LiveData<Liga> = _ligaId.switchMap { ligaId ->
        repository.obtenerLigaPorId(ligaId)
    }

    // LiveData de Clasificación
    val clasificacion: LiveData<List<Clasificacion>> = _ligaId.switchMap { ligaId ->
        repository.obtenerClasificacionPorLiga(ligaId)
    }

    // LiveData de Resultados
    val resultados: LiveData<List<Partido>> = _ligaId.switchMap { ligaId ->
        repository.obtenerResultadosPorLiga(ligaId)
    }

    // LiveData de Partidos Próximos
    val partidosProximos: LiveData<List<Partido>> = _ligaId.switchMap { ligaId ->
        repository.obtenerPartidosProximosPorLiga(ligaId)
    }

    init {
        val context = application.applicationContext
        val db = AppDataBase.getDatabase(context)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao())

        // Observamos la liga internamente para tener el objeto
        liga.observeForever { ligaObjeto ->
            ligaActual = ligaObjeto
        }
    }

    // --- ACCIONES ---

    fun setLigaId(ligaId: String) {
        if (_ligaId.value != ligaId) {
            _ligaId.value = ligaId
        }
    }

    // --- NUEVA FUNCIÓN ---
    fun toggleFavorito() {
        ligaActual?.let {
            viewModelScope.launch {
                it.esFavorita = !it.esFavorita // Invierte el estado
                repository.actualizarLiga(it) // Actualiza en BBDD
            }
        }
    }

    override fun onCleared() {
        liga.removeObserver { } // Limpia el observer
        super.onCleared()
    }
}