package viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import db.AppDataBase
import model.Liga
import model.Partido
import respository.InfoSportRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InicioViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: InfoSportRepository
    private val _searchQuery = MutableLiveData<String>("")
    private val fechaHoy = getFechaActualFormatoBBDD()

    // LiveData que expone los partidos filtrados
    val partidosFiltrados: LiveData<List<Partido>>

    // LiveData para las ligas (sin cambios)
    val ligasPrincipales: LiveData<List<Liga>>

    init {
        val context = application.applicationContext
        val db = AppDataBase.getDatabase(context)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao())

        // Las ligas no necesitan filtrarse
        ligasPrincipales = repository.obtenerTodasLasLigas()

        // Los partidos reaccionan a la búsqueda
        partidosFiltrados = _searchQuery.switchMap { query ->
            if (query.isBlank()) {
                repository.obtenerPartidosDeHoy(fechaHoy)
            } else {
                repository.buscarPartidosDeHoy(fechaHoy, query)
            }
        }
    }

    private fun getFechaActualFormatoBBDD(): String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
    }

    // Función llamada por el Fragment
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}