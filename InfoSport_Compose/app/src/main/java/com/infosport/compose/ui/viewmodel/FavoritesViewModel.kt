package com.infosport.compose.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.infosport.compose.data.model.Liga
import com.infosport.compose.data.model.Noticia
import com.infosport.compose.data.model.Partido
import com.infosport.compose.data.repository.InfoSportRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: InfoSportRepository) : ViewModel() {
    
    private val _favoriteLeagues = MutableStateFlow<List<Liga>>(emptyList())
    val favoriteLeagues: StateFlow<List<Liga>> = _favoriteLeagues.asStateFlow()
    
    private val _favoriteMatches = MutableStateFlow<List<Partido>>(emptyList())
    val favoriteMatches: StateFlow<List<Partido>> = _favoriteMatches.asStateFlow()
    
    private val _favoriteNews = MutableStateFlow<List<Noticia>>(emptyList())
    val favoriteNews: StateFlow<List<Noticia>> = _favoriteNews.asStateFlow()
    
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadFavorites()
    }
    
    private fun loadFavorites() {
        viewModelScope.launch {
            repository.obtenerLigasFavoritas()
                .catch { e -> Log.e("FavoritesVM", "Error: ${e.message}") }
                .collect { _favoriteLeagues.value = it }
        }
        
        viewModelScope.launch {
            repository.obtenerPartidosFavoritos()
                .catch { e -> Log.e("FavoritesVM", "Error: ${e.message}") }
                .collect { _favoriteMatches.value = it }
        }
        
        viewModelScope.launch {
            repository.obtenerNoticiasFavoritas()
                .catch { e -> Log.e("FavoritesVM", "Error: ${e.message}") }
                .collect { 
                    _favoriteNews.value = it
                    _isLoading.value = false
                }
        }
    }
    
    fun toggleLeagueFavorite(liga: Liga) {
        viewModelScope.launch {
            repository.toggleLigaFavorita(liga.id, !liga.esFavorita)
        }
    }
    
    fun toggleMatchFavorite(partido: Partido) {
        viewModelScope.launch {
            repository.togglePartidoFavorito(partido.id, !partido.esFavorito)
        }
    }
    
    fun toggleNewsFavorite(noticia: Noticia) {
        viewModelScope.launch {
            repository.toggleNoticiaFavorita(noticia.id, !noticia.esFavorita)
        }
    }
    
    class Factory(private val repository: InfoSportRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
                return FavoritesViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
