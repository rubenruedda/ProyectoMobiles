package viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import db.AppDataBase
import model.Equipo
import model.Liga
import model.Noticia
import model.Partido
import respository.InfoSportRepository

class FavoritoViewModel(application: Application) : AndroidViewModel(application) {

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

    val noticiasFavoritas: LiveData<List<Noticia>>

    val partidosFavoritos: LiveData<List<Partido>>



    init {
        // Inicialización del Repositorio
        val context = application.applicationContext
        val db = AppDataBase.getDatabase(context)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao())

        // Obtiene los LiveData de las listas de favoritos
        ligasFavoritas = repository.obtenerLigasFavoritas()
        noticiasFavoritas = repository.obtenerNoticiasFavoritas()
        partidosFavoritos = repository.obtenerPartidosFavoritos()
    }
}