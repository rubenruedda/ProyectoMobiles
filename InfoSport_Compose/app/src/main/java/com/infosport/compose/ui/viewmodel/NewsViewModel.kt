package com.infosport.compose.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.infosport.compose.data.model.Noticia
import com.infosport.compose.data.repository.InfoSportRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: InfoSportRepository) : ViewModel() {
    
    private val _news = MutableStateFlow<List<Noticia>>(emptyList())
    val news: StateFlow<List<Noticia>> = _news.asStateFlow()
    
    private val _selectedNews = MutableStateFlow<Noticia?>(null)
    val selectedNews: StateFlow<Noticia?> = _selectedNews.asStateFlow()
    
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    init {
        loadNews()
    }
    
    private fun loadNews() {
        viewModelScope.launch {
            repository.obtenerTodasLasNoticias()
                .catch { e ->
                    Log.e("NewsViewModel", "Error loading news: ${e.message}")
                    _isLoading.value = false
                }
                .collect { newsList ->
                    _news.value = newsList
                    _isLoading.value = false
                }
        }
    }
    
    fun searchNews(query: String) {
        _searchQuery.value = query
        if (query.isBlank()) {
            loadNews()
        } else {
            viewModelScope.launch {
                repository.buscarNoticias(query).collect { results ->
                    _news.value = results
                }
            }
        }
    }
    
    fun selectNews(noticiaId: Int) {
        viewModelScope.launch {
            repository.obtenerNoticiaPorId(noticiaId).collect { noticia ->
                _selectedNews.value = noticia
            }
        }
    }
    
    fun toggleFavorite(noticia: Noticia) {
        viewModelScope.launch {
            repository.toggleNoticiaFavorita(noticia.id, !noticia.esFavorita)
        }
    }
    
    class Factory(private val repository: InfoSportRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
                return NewsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
