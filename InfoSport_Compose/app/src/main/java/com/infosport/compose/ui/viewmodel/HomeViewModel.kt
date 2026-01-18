package com.infosport.compose.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.infosport.compose.data.model.Partido
import com.infosport.compose.data.repository.InfoSportRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(private val repository: InfoSportRepository) : ViewModel() {
    
    private val _todayMatches = MutableStateFlow<List<Partido>>(emptyList())
    val todayMatches: StateFlow<List<Partido>> = _todayMatches.asStateFlow()
    
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadMatches()
    }
    
    private fun loadMatches() {
        val today = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        Log.d("HomeViewModel", "Loading matches for: $today")
        
        viewModelScope.launch {
            // First try to get today's matches
            repository.obtenerPartidosPorFecha(today)
                .catch { e ->
                    Log.e("HomeViewModel", "Error loading matches: ${e.message}")
                    _isLoading.value = false
                }
                .collect { matches ->
                    if (matches.isEmpty()) {
                        // If no matches today, load all matches for demo purposes
                        Log.d("HomeViewModel", "No matches today, loading all matches")
                        loadAllMatches()
                    } else {
                        _todayMatches.value = matches
                        _isLoading.value = false
                        Log.d("HomeViewModel", "Loaded ${matches.size} matches for today")
                    }
                }
        }
    }
    
    private fun loadAllMatches() {
        viewModelScope.launch {
            repository.obtenerTodosLosPartidos()
                .catch { e ->
                    Log.e("HomeViewModel", "Error loading all matches: ${e.message}")
                    _isLoading.value = false
                }
                .collect { matches ->
                    _todayMatches.value = matches
                    _isLoading.value = false
                    Log.d("HomeViewModel", "Loaded ${matches.size} total matches")
                }
        }
    }
    
    fun toggleFavorite(partido: Partido) {
        viewModelScope.launch {
            repository.togglePartidoFavorito(partido.id, !partido.esFavorito)
        }
    }
    
    class Factory(private val repository: InfoSportRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
