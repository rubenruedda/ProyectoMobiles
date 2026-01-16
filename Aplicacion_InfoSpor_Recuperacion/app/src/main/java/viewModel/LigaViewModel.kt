package viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import db.AppDataBase
import kotlinx.coroutines.launch
import model.Clasificacion
import model.Liga
import model.Partido
import respository.InfoSportRepository

class LigaViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "LigaViewModel"
    private val repository: InfoSportRepository
    private val _ligaId = MutableLiveData<String>()
    
    // Flag para saber si es la primera carga (evitar toast en carga inicial)
    private var isInitialLoad = true

    // LiveData de la liga actual (para saber si es favorita)
    val liga: LiveData<Liga> = _ligaId.switchMap { id -> 
        Log.d(TAG, "Obteniendo liga con ID: $id")
        repository.obtenerLigaPorId(id) 
    }

    val clasificacion: LiveData<List<Clasificacion>> = _ligaId.switchMap { id ->
        Log.d(TAG, "Obteniendo clasificación para liga: $id")
        repository.obtenerClasificacionPorLiga(id)
    }

    // Cambiado para obtener TODOS los partidos de la liga
    val resultados: LiveData<List<Partido>> = _ligaId.switchMap { id ->
        Log.d(TAG, "Obteniendo todos los partidos para liga: $id")
        repository.obtenerTodosPartidosPorLiga(id)
    }
    
    // También puedes tener solo los resultados (partidos jugados)
    val partidosJugados: LiveData<List<Partido>> = _ligaId.switchMap { id ->
        repository.obtenerResultadosPorLiga(id)
    }
    
    // Y los próximos partidos
    val partidosProximos: LiveData<List<Partido>> = _ligaId.switchMap { id ->
        repository.obtenerPartidosProximosPorLiga(id)
    }

    init {
        Log.d(TAG, "Inicializando LigaViewModel")
        val db = AppDataBase.getDatabase(application)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao())
    }

    fun setLigaId(id: String) {
        Log.d(TAG, "Estableciendo liga ID: $id")
        if (_ligaId.value != id) {
            isInitialLoad = true
            _ligaId.value = id
        }
    }
    
    fun isInitialLoad(): Boolean {
        val result = isInitialLoad
        isInitialLoad = false
        return result
    }

    fun toggleFavorito() {
        liga.value?.let { ligaActual ->
            Log.d(TAG, "Toggle favorito para liga: ${ligaActual.nombre}")
            viewModelScope.launch {
                ligaActual.esFavorita = !ligaActual.esFavorita
                repository.actualizarLiga(ligaActual)
            }
        }
    }
}