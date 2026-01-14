package viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import db.AppDataBase
import model.Liga
import respository.InfoSportRepository

sealed class CompeticionItem {
    data class PaisHeader(val nombre: String) : CompeticionItem()
    data class LigaItem(val liga: Liga) : CompeticionItem()
}

class CompeticionesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: InfoSportRepository
    private val _searchQuery = MutableLiveData<String>("")

    val listaAgrupada: LiveData<List<CompeticionItem>>

    init {
        val db = AppDataBase.getDatabase(application)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao())

        listaAgrupada = _searchQuery.switchMap { query ->
            val ligasLive = if (query.isBlank()) repository.obtenerTodasLasLigas() else repository.buscarLigas(query)

            ligasLive.map { ligas ->
                val items = mutableListOf<CompeticionItem>()
                ligas.groupBy { it.paisNombre }.forEach { (pais, lista) ->
                    items.add(CompeticionItem.PaisHeader(pais))
                    lista.forEach { items.add(CompeticionItem.LigaItem(it)) }
                }
                items
            }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}