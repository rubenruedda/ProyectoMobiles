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
    private val fechaHoy = getFechaActual()

    val partidosFiltrados: LiveData<List<Partido>>
    val ligasPrincipales: LiveData<List<Liga>>

    init {
        val db = AppDataBase.getDatabase(application)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao())

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
        // Usa la fecha de tu sistema o una fecha fija para pruebas si no tienes datos de hoy
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        // return "20/11/2025" // Descomenta esto si quieres forzar la fecha de los datos de prueba
    }
}