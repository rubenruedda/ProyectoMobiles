package com.infosport.compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.infosport.compose.data.repository.InfoSportRepository
import com.infosport.compose.ui.screens.*
import com.infosport.compose.ui.viewmodel.*

@Composable
fun NavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    leaguesViewModel: LeaguesViewModel,
    newsViewModel: NewsViewModel,
    favoritesViewModel: FavoritesViewModel,
    settingsViewModel: SettingsViewModel,
    repository: InfoSportRepository,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        // Pantalla de Inicio
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = homeViewModel,
                onMatchClick = { partidoId ->
                    navController.navigate(Screen.MatchDetail.createRoute(partidoId))
                }
            )
        }
        
        // Pantalla de Ligas
        composable(Screen.Leagues.route) {
            LeaguesScreen(
                viewModel = leaguesViewModel,
                onLeagueClick = { ligaId ->
                    navController.navigate(Screen.LeagueDetail.createRoute(ligaId))
                }
            )
        }
        
        // Detalle de Liga
        composable(
            route = Screen.LeagueDetail.route,
            arguments = listOf(navArgument("ligaId") { type = NavType.StringType })
        ) { backStackEntry ->
            val ligaId = backStackEntry.arguments?.getString("ligaId") ?: ""
            LeagueDetailScreen(
                ligaId = ligaId,
                viewModel = leaguesViewModel,
                onBackClick = { navController.popBackStack() },
                onMatchClick = { partidoId ->
                    navController.navigate(Screen.MatchDetail.createRoute(partidoId))
                }
            )
        }
        
        // Detalle partido
        composable(
            route = Screen.MatchDetail.route,
            arguments = listOf(navArgument("partidoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val partidoId = backStackEntry.arguments?.getInt("partidoId") ?: 0
            MatchDetailScreen(
                partidoId = partidoId,
                repository = repository,
                onBackClick = { navController.popBackStack() }
            )
        }
        
        // Pantalla de Noticias
        composable(Screen.News.route) {
            NewsScreen(
                viewModel = newsViewModel,
                onNewsClick = { noticiaId ->
                    navController.navigate(Screen.NewsDetail.createRoute(noticiaId))
                }
            )
        }
        
        // Detalle noticia
        composable(
            route = Screen.NewsDetail.route,
            arguments = listOf(navArgument("noticiaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noticiaId = backStackEntry.arguments?.getInt("noticiaId") ?: 0
            NewsDetailScreen(
                noticiaId = noticiaId,
                viewModel = newsViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
        
        // Pantalla de Favoritos
        composable(Screen.Favorites.route) {
            FavoritesScreen(
                viewModel = favoritesViewModel,
                onLeagueClick = { ligaId ->
                    navController.navigate(Screen.LeagueDetail.createRoute(ligaId))
                },
                onMatchClick = { partidoId ->
                    navController.navigate(Screen.MatchDetail.createRoute(partidoId))
                },
                onNewsClick = { noticiaId ->
                    navController.navigate(Screen.NewsDetail.createRoute(noticiaId))
                }
            )
        }
        
        // Pantalla de Ajustes
        composable(Screen.Settings.route) {
            SettingsScreen(viewModel = settingsViewModel)
        }
    }
}
