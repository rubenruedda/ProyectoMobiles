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
    private val repository: InfoSportRepository

    init {
        val db = AppDataBase.getDatabase(application)
        repository = InfoSportRepository(
            db.partidoDao(),
            db.ligaDao(),
            db.noticiaDao(),
            db.favoritoDao()
        )
    }

    val ligasFavoritas = repository.ligasFavoritas
    val partidosFavoritos = repository.partidosFavoritos
    val noticiasFavoritas = repository.noticiasFavoritas
}