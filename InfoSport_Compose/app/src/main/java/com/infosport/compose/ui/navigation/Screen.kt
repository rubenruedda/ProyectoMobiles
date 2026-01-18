package com.infosport.compose.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : Screen("home", "Inicio", Icons.Default.Home)
    data object Leagues : Screen("leagues", "Ligas", Icons.Default.EmojiEvents)
    data object News : Screen("news", "Noticias", Icons.Default.Newspaper)
    data object Favorites : Screen("favorites", "Favoritos", Icons.Default.Favorite)
    data object Settings : Screen("settings", "Ajustes", Icons.Default.Settings)
    
    // Pantallas de detalle (sin icono en nav bar)
    data object LeagueDetail : Screen("league/{ligaId}", "Liga", Icons.Default.EmojiEvents) {
        fun createRoute(ligaId: String) = "league/$ligaId"
    }
    data object MatchDetail : Screen("match/{partidoId}", "Partido", Icons.Default.SportsSoccer) {
        fun createRoute(partidoId: Int) = "match/$partidoId"
    }
    data object NewsDetail : Screen("newsDetail/{noticiaId}", "Noticia", Icons.Default.Newspaper) {
        fun createRoute(noticiaId: Int) = "newsDetail/$noticiaId"
    }
    
    companion object {
        val bottomNavItems = listOf(Home, Leagues, News, Favorites, Settings)
    }
}
