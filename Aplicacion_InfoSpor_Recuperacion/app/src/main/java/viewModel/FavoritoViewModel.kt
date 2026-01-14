package viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import db.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.Equipo
import model.Liga
import model.Noticia
import model.Partido
import respository.InfoSportRepository

class FavoritoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: InfoSportRepository
    private val db = AppDataBase.getDatabase(application)


    init {
        val db = AppDataBase.getDatabase(application)
        repository = InfoSportRepository(
            db.partidoDao(),
            db.ligaDao(),
            db.noticiaDao(),
            db.favoritoDao()
        )
    }

    val ligasFavoritas: LiveData<List<Liga>> = db.ligaDao().obtenerLigasFavoritas()
    val partidosFavoritos: LiveData<List<Partido>> = db.partidoDao().obtenerPartidosFavoritos() // Crea este método en su DAO
    val noticiasFavoritas: LiveData<List<Noticia>> = db.noticiaDao().obtenerNoticiasFavoritas() // Crea este método en su DAO

    fun actualizarLiga(liga: Liga) {
        viewModelScope.launch(Dispatchers.IO) {
            db.ligaDao().actualizarLiga(liga)
        }
    }

    fun actualizarPartido(partido: Partido) {
        viewModelScope.launch(Dispatchers.IO) {
            db.partidoDao().actualizarPartido(partido)
        }
    }

    fun actualizarNoticia(noticia: Noticia) {
        viewModelScope.launch(Dispatchers.IO) {
            db.noticiaDao().actualizarNoticia(noticia)
        }
    }
}