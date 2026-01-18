package com.infosport.compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.infosport.compose.data.preferences.UserPreferences
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AjustesViewModel(private val userPreferences: UserPreferences) : ViewModel() {
    
    val themeMode: StateFlow<UserPreferences.ThemeMode> = userPreferences.themeMode
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserPreferences.ThemeMode.SYSTEM)
    
    fun setThemeMode(mode: UserPreferences.ThemeMode) {
        viewModelScope.launch {
            userPreferences.setThemeMode(mode)
        }
    }
    
    class Factory(private val userPreferences: UserPreferences) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AjustesViewModel::class.java)) {
                return AjustesViewModel(userPreferences) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
