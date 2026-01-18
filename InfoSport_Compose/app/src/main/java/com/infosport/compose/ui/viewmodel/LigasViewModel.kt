package com.infosport.compose.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.infosport.compose.data.model.Clasificacion
import com.infosport.compose.data.model.Liga
import com.infosport.compose.data.model.Partido
import com.infosport.compose.data.repository.InfoSportRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LigasViewModel(private val repository: InfoSportRepository) : ViewModel() {
    
    private val _leagues = MutableStateFlow<List<Liga>>(emptyList())
    val leagues: StateFlow<List<Liga>> = _leagues.asStateFlow()
    
    private val _selectedLeague = MutableStateFlow<Liga?>(null)
    val selectedLeague: StateFlow<Liga?> = _selectedLeague.asStateFlow()
    
    private val _standings = MutableStateFlow<List<Clasificacion>>(emptyList())
    val standings: StateFlow<List<Clasificacion>> = _standings.asStateFlow()
    
    private val _leagueMatches = MutableStateFlow<List<Partido>>(emptyList())
    val leagueMatches: StateFlow<List<Partido>> = _leagueMatches.asStateFlow()
    
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadLeagues()
    }
    
    private fun loadLeagues() {
        viewModelScope.launch {
            repository.obtenerTodasLasLigas()
                .catch { e ->
                    Log.e("LeaguesViewModel", "Error loading leagues: ${e.message}")
                    _isLoading.value = false
                }
                .collect { leaguesList ->
                    _leagues.value = leaguesList
                    _isLoading.value = false
                }
        }
    }
    
    fun selectLeague(ligaId: String) {
        viewModelScope.launch {
            repository.obtenerLigaPorId(ligaId).collect { liga ->
                _selectedLeague.value = liga
            }
        }
        loadStandings(ligaId)
        loadLeagueMatches(ligaId)
    }
    
    private fun loadStandings(ligaId: String) {
        viewModelScope.launch {
            repository.obtenerClasificacionPorLiga(ligaId).collect { clasificacion ->
                _standings.value = clasificacion
            }
        }
    }
    
    private fun loadLeagueMatches(ligaId: String) {
        viewModelScope.launch {
            repository.obtenerPartidosPorLiga(ligaId).collect { matches ->
                _leagueMatches.value = matches
            }
        }
    }
    
    fun toggleFavorite(liga: Liga) {
        viewModelScope.launch {
            repository.toggleLigaFavorita(liga.id, !liga.esFavorita)
        }
    }
    
    class Factory(private val repository: InfoSportRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LigasViewModel::class.java)) {
                return LigasViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
