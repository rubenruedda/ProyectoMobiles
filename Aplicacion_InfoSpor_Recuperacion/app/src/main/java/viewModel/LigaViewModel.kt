package viewModel

import android.app.Application
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

    private val repository: InfoSportRepository
    private val _ligaId = MutableLiveData<String>()

    // LiveData de la liga actual (para saber si es favorita)
    val liga: LiveData<Liga> = _ligaId.switchMap { id -> repository.obtenerLigaPorId(id) }

    val clasificacion: LiveData<List<Clasificacion>> = _ligaId.switchMap { id ->
        repository.obtenerClasificacionPorLiga(id)
    }

    val resultados: LiveData<List<Partido>> = _ligaId.switchMap { id ->
        repository.obtenerResultadosPorLiga(id)
    }

    init {
        val db = AppDataBase.getDatabase(application)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao())
    }

    fun setLigaId(id: String) {
        _ligaId.value = id
    }

    fun toggleFavorito() {
        liga.value?.let { ligaActual ->
            viewModelScope.launch {
                ligaActual.esFavorita = !ligaActual.esFavorita
                repository.actualizarLiga(ligaActual)
            }
        }
    }
}