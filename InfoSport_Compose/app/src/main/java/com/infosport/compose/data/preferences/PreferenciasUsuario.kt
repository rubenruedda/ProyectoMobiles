package com.infosport.compose.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class UserPreferences(private val context: Context) {

    private object PreferencesKeys {
        val THEME_MODE = stringPreferencesKey("theme_mode")
        val LAST_SELECTED_LEAGUE = stringPreferencesKey("last_selected_league")
    }

    // Tipos de tema disponibles
    enum class ThemeMode { LIGHT, DARK, SYSTEM }

    // Tema actual
    val themeMode: Flow<ThemeMode> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            when (preferences[PreferencesKeys.THEME_MODE]) {
                "LIGHT" -> ThemeMode.LIGHT
                "DARK" -> ThemeMode.DARK
                else -> ThemeMode.SYSTEM
            }
        }

    // Guardo el tema que elija el usuario
    suspend fun setThemeMode(mode: ThemeMode) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.THEME_MODE] = mode.name
        }
    }

    // Ultima liga que miro el usuario
    val lastSelectedLeague: Flow<String?> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.LAST_SELECTED_LEAGUE]
        }

    // Guardar última liga seleccionada
    suspend fun setLastSelectedLeague(leagueId: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.LAST_SELECTED_LEAGUE] = leagueId
        }
    }
}
