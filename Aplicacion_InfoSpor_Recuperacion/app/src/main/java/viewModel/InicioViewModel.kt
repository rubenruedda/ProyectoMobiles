package viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import db.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.Liga
import model.Partido
import respository.InfoSportRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InicioViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: InfoSportRepository
    private val _searchQuery = MutableLiveData<String>("")
    private val fechaHoy = getFechaActual()
    private val db = AppDataBase.getDatabase(application)
    val partidosFiltrados: LiveData<List<Partido>>
    val ligasPrincipales: LiveData<List<Liga>>

    init {
        val db = AppDataBase.getDatabase(application)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao(), db.favoritoDao())

        ligasPrincipales = repository.obtenerTodasLasLigas()

        partidosFiltrados = _searchQuery.switchMap { query ->
            if (query.isNullOrBlank()) {
                repository.obtenerPartidosDeHoy(fechaHoy)
            } else {
                repository.buscarPartidosDeHoy(fechaHoy, query)
            }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private fun getFechaActual(): String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
    }

    fun actualizarFavoritoLiga(liga: Liga) {
        viewModelScope.launch(Dispatchers.IO) {
            db.ligaDao().actualizarLiga(liga)
        }
    }

    fun actualizarFavoritoPartido(partido: Partido){
        viewModelScope.launch(Dispatchers.IO) {
            db.partidoDao().actualizarPartido(partido)
        }
    }
}