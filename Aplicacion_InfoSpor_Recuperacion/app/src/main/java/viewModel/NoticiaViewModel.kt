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
    val noticias: LiveData<List<Noticia>>
    private val _noticiaId = MutableLiveData<Int>()

    val noticia: LiveData<Noticia> = _noticiaId.switchMap { id -> repository.obtenerNoticiaPorId(id)}

    init {
        val db = AppDataBase.getDatabase(application)
        repository = InfoSportRepository(
            db.partidoDao(),
            db.ligaDao(),
            db.noticiaDao(),
            db.favoritoDao()
        )

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

    fun esFavorito(partidoId: Int): LiveData<Boolean> {
        return repository.esFavorito("PARTIDO", partidoId.toString())
    }

    fun toggleFavorito(partidoId: Int, esActualmenteFavorito: Boolean) {
        viewModelScope.launch {
            if (esActualmenteFavorito) {
                repository.eliminarFavorito("NOTICIA", partidoId.toString())
            } else {
                repository.agregarFavorito("NOTICIA", partidoId.toString())
            }
        }
    }
}