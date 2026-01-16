package viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import db.AppDataBase
import kotlinx.coroutines.launch
import model.Equipo
import model.Liga
import model.Noticia
import model.Partido
import respository.InfoSportRepository

class FavoritoViewModel(application: Application) : AndroidViewModel(application) {
    
    private val TAG = "FavoritoViewModel"

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

    val ligasFavoritas: LiveData<List<Liga>>
    val equiposFavoritos: LiveData<List<Equipo>>
    val partidosFavoritos: LiveData<List<Partido>>
    val noticiasFavoritas: LiveData<List<Noticia>>

    init {
        // Inicialización del Repositorio
        val context = application.applicationContext
        val db = AppDataBase.getDatabase(context)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao())

        // Obtiene los LiveData de las listas de favoritos
        ligasFavoritas = repository.obtenerLigasFavoritas()
        equiposFavoritos = repository.obtenerEquiposFavoritos()
        partidosFavoritos = repository.obtenerPartidosFavoritos()
        noticiasFavoritas = repository.obtenerNoticiasFavoritas()
        
        Log.d(TAG, "FavoritoViewModel inicializado")
    }
    
    fun toggleNoticiaFavorita(noticia: Noticia) {
        viewModelScope.launch {
            noticia.esFavorita = !noticia.esFavorita
            repository.actualizarNoticia(noticia)
            Log.d(TAG, "Noticia ${noticia.id} favorito: ${noticia.esFavorita}")
        }
    }
    
    fun togglePartidoFavorito(partido: Partido) {
        viewModelScope.launch {
            partido.esFavorita = !partido.esFavorita
            repository.actualizarPartido(partido)
            Log.d(TAG, "Partido ${partido.id} favorito: ${partido.esFavorita}")
        }
    }
    
    fun toggleLigaFavorita(liga: Liga) {
        viewModelScope.launch {
            liga.esFavorita = !liga.esFavorita
            repository.actualizarLiga(liga)
            Log.d(TAG, "Liga ${liga.id} favorito: ${liga.esFavorita}")
        }
    }
}