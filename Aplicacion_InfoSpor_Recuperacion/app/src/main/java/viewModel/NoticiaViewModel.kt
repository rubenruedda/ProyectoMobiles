package viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import db.AppDataBase
import model.Noticia
import respository.InfoSportRepository

class NoticiaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: InfoSportRepository
    private val _searchQuery = MutableLiveData<String>("")

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
}