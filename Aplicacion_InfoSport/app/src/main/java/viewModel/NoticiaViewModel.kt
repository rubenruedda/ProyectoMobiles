package viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import db.AppDataBase
import model.Noticia
import respository.InfoSportRepository

class NoticiaViewModel(application: Application) : AndroidViewModel(application) {

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

    private val _searchQuery = MutableLiveData<String>("")

    // LiveData que contiene la lista de todas las noticias
    val noticiasFiltradas: LiveData<List<Noticia>>

    init {
        val db = AppDataBase.getDatabase(application.applicationContext)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao())

        // Usamos switchMap: cada vez que _searchQuery cambie,
        // se ejecutará una nueva consulta a la BBDD.
        noticiasFiltradas = _searchQuery.switchMap { query ->
            if (query.isBlank()) {
                // Si la búsqueda está vacía, devuelve todo
                repository.obtenerTodasLasNoticias()
            } else {
                // Si hay búsqueda, devuelve los resultados filtrados
                repository.buscarNoticias(query)
            }
        }
    }

    // Función llamada por el Fragment para actualizar la búsqueda
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    // Función para obtener el detalle de una noticia por su ID (si no usamos switchMap)
    fun obtenerNoticiaPorId(noticiaId: Int): LiveData<Noticia> {
        return repository.obtenerNoticiaPorId(noticiaId)
    }
}