package com.infosport.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.infosport.compose.data.database.InfoSportDatabase
import com.infosport.compose.data.preferences.UserPreferences.ThemeMode
import com.infosport.compose.data.preferences.UserPreferences
import com.infosport.compose.data.repository.InfoSportRepository
import com.infosport.compose.ui.navigation.NavGraph
import com.infosport.compose.ui.navigation.Screen
import com.infosport.compose.ui.theme.InfoSportTheme
import com.infosport.compose.ui.viewmodel.*

class MainActivity : ComponentActivity() {
    
    private lateinit var repository: InfoSportRepository
    private lateinit var userPreferences: UserPreferences
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        Log.d("MainActivity", "Iniciando InfoSport Compose")
        
        // Inicializar base de datos y repositorio
        val database = InfoSportDatabase.getDatabase(applicationContext)
        repository = InfoSportRepository(
            ligaDao = database.ligaDao(),
            partidoDao = database.partidoDao(),
            noticiaDao = database.noticiaDao()
        )
        
        // Inicializar preferencias
        userPreferences = UserPreferences(applicationContext)
        
        setContent {
            val themeMode by userPreferences.themeMode.collectAsState(initial = ThemeMode.SYSTEM)
            
            val darkTheme = when (themeMode) {
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
            }
            
            InfoSportTheme(darkTheme = darkTheme) {         
                MainScreen(
                    repository = repository,
                    userPreferences = userPreferences
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    repository: InfoSportRepository,
    userPreferences: UserPreferences
) {
    val navController = rememberNavController()
    
    // ViewModels
    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModel.Factory(repository)
    )
    val leaguesViewModel: LeaguesViewModel = viewModel(
        factory = LeaguesViewModel.Factory(repository)
    )
    val newsViewModel: NewsViewModel = viewModel(
        factory = NewsViewModel.Factory(repository)
    )
    val favoritesViewModel: FavoritesViewModel = viewModel(
        factory = FavoritesViewModel.Factory(repository)
    )
    val settingsViewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModel.Factory(userPreferences)
    )
    
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    // Bottom Navigation items
    val bottomNavItems = listOf(
        BottomNavItem(
            screen = Screen.Home,
            icon = Icons.Default.Home,
            labelRes = R.string.nav_home
        ),
        BottomNavItem(
            screen = Screen.Leagues,
            icon = Icons.Default.EmojiEvents,
            labelRes = R.string.nav_leagues
        ),
        BottomNavItem(
            screen = Screen.News,
            icon = Icons.Default.Newspaper,
            labelRes = R.string.nav_news
        ),
        BottomNavItem(
            screen = Screen.Favorites,
            icon = Icons.Default.Favorite,
            labelRes = R.string.nav_favorites
        ),
        BottomNavItem(
            screen = Screen.Settings,
            icon = Icons.Default.Settings,
            labelRes = R.string.nav_settings
        )
    )
    
    // Determinar si mostrar la bottom bar
    val showBottomBar = bottomNavItems.any { it.screen.route == currentDestination?.route }
    
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = stringResource(item.labelRes)
                                )
                            },
                            label = { Text(stringResource(item.labelRes)) },
                            selected = currentDestination?.hierarchy?.any { 
                                it.route == item.screen.route 
                            } == true,
                            onClick = {
                                Log.d("MainScreen", "Navegando a: ${item.screen.route}")
                                navController.navigate(item.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavGraph(
            navController = navController,
            homeViewModel = homeViewModel,
            leaguesViewModel = leaguesViewModel,
            newsViewModel = newsViewModel,
            favoritesViewModel = favoritesViewModel,
            settingsViewModel = settingsViewModel,
            repository = repository,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

private data class BottomNavItem(
    val screen: Screen,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val labelRes: Int
)
