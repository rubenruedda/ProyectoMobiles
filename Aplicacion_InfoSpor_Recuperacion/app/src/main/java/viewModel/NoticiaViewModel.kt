package viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import db.AppDataBase
import kotlinx.coroutines.launch
import model.Noticia
import respository.InfoSportRepository

class NoticiaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: InfoSportRepository
    private val _searchQuery = MutableLiveData<String>("")
    private val _noticiaId = MutableLiveData<Int>()

    val noticia: LiveData<Noticia> = _noticiaId.switchMap { id -> repository.obtenerNoticiaPorId(id) }
    val noticias: LiveData<List<Noticia>>

    init {
        val db = AppDataBase.getDatabase(application)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao())

        noticias = _searchQuery.switchMap { query ->
            if (query.isBlank()) repository.obtenerTodasLasNoticias()
            else repository.buscarNoticias(query)
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun obtenerNoticiaPorId(noticiaId: Int): LiveData<Noticia> {
        return repository.obtenerNoticiaPorId(noticiaId)
    }

    fun toggleFavorito() {
        noticia.value?.let { noticiaActual ->
            viewModelScope.launch {
                noticiaActual.esFavorita = !noticiaActual.esFavorita
                repository.actualizarNoticia(noticiaActual)
            }
        }
    }
}