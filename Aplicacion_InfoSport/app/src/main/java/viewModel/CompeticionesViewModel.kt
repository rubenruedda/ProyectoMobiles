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

    // LiveData que expone la lista agrupada y filtrada
    val listaFiltradaYAgrupada: LiveData<List<CompeticionItem>>

    init {
        val db = AppDataBase.getDatabase(application)
        repository = InfoSportRepository(db.partidoDao(), db.ligaDao(), db.noticiaDao())

        // switchMap reacciona a los cambios en _searchQuery
        listaFiltradaYAgrupada = _searchQuery.switchMap { query ->
            // 1. Obtiene el LiveData de Ligas (filtrado o completo)
            val ligasLiveData = if (query.isBlank()) {
                repository.obtenerTodasLasLigas()
            } else {
                repository.buscarLigas(query)
            }

            // 2. Transforma (map) la List<Liga> en List<CompeticionItem>
            ligasLiveData.map { ligas ->
                val itemsAgrupados = mutableListOf<CompeticionItem>()
                val ligasPorPais = ligas.groupBy { it.paisNombre }

                ligasPorPais.forEach { (pais, ligasDelPais) ->
                    itemsAgrupados.add(CompeticionItem.PaisHeader(pais))
                    ligasDelPais.forEach { liga ->
                        itemsAgrupados.add(CompeticionItem.LigaItem(liga))
                    }
                }
                itemsAgrupados
            }
        }
    }

    // Función llamada por el Fragment
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}
