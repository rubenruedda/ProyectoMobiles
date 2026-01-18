package com.infosport.compose

import android.app.Application
import com.infosport.compose.data.database.InfoSportDatabase
import com.infosport.compose.data.preferences.UserPreferences
import com.infosport.compose.data.repository.InfoSportRepository

class InfoSportApp : Application() {
    
    // Database
    val database by lazy { InfoSportDatabase.getDatabase(this) }
    
    // Repository
    val repository by lazy {
        InfoSportRepository(
            ligaDao = database.ligaDao(),
            partidoDao = database.partidoDao(),
            noticiaDao = database.noticiaDao()
        )
    }
    
    // Preferences
    val userPreferences by lazy { UserPreferences(this) }
}
