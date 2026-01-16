package viewModel

import android.app.Application
import android.util.Log
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
    
    private val TAG = "InicioViewModel"
    private val repository: InfoSportRepository
    private val _searchQuery = MutableLiveData<String>("")
    private val fechaHoy = getFechaActual()

    val partidosFiltrados: LiveData<List<Partido>>
    val ligasPrincipales: LiveData<List<Liga>>

    init {
        Log.d(TAG, "Inicializando InicioViewModel")
        val db = AppDataBase.getDatabase(application)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao())

        ligasPrincipales = repository.obtenerTodasLasLigas()
        Log.d(TAG, "Fecha de hoy para consulta: $fechaHoy")

        partidosFiltrados = _searchQuery.switchMap { query ->
            Log.d(TAG, "Buscando partidos con query: $query")
            if (query.isNullOrBlank()) {
                repository.obtenerPartidosDeHoy(fechaHoy)
            } else {
                repository.buscarPartidosDeHoy(fechaHoy, query)
            }
        }
    }

    fun setSearchQuery(query: String) {
        Log.d(TAG, "Query de búsqueda actualizada: $query")
        _searchQuery.value = query
    }

    private fun getFechaActual(): String {
        // Para pruebas usar la fecha de los datos simulados
        // En producción usar: SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        return "20/11/2025" // Fecha de los datos de prueba
        // return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
    }
}