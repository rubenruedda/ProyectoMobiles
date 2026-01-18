package com.infosport.compose.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.infosport.compose.R
import com.infosport.compose.ui.components.LeagueCard
import com.infosport.compose.ui.components.MatchCard
import com.infosport.compose.ui.components.NewsCard
import com.infosport.compose.ui.viewmodel.FavoritosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    viewModel: FavoritosViewModel,
    onLeagueClick: (String) -> Unit,
    onMatchClick: (Int) -> Unit,
    onNewsClick: (Int) -> Unit
) {
    val favoriteLeagues by viewModel.favoriteLeagues.collectAsState()
    val favoriteMatches by viewModel.favoriteMatches.collectAsState()
    val favoriteNews by viewModel.favoriteNews.collectAsState()
    val context = LocalContext.current
    
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        stringResource(R.string.tab_leagues),
        stringResource(R.string.tab_matches),
        stringResource(R.string.tab_news)
    )
    
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.nav_favorites),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
                
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { 
                                selectedTabIndex = index
                                Log.d("FavoritesScreen", "Tab seleccionada: $title")
                            },
                            text = { Text(title) }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        when (selectedTabIndex) {
            0 -> FavoriteLeaguesTab(
                leagues = favoriteLeagues,
                onLeagueClick = onLeagueClick,
                onFavoriteClick = { liga ->
                    viewModel.toggleLeagueFavorite(liga)
                    Toast.makeText(
                        context,
                        context.getString(R.string.removed_from_favorites),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.padding(paddingValues)
            )
            1 -> FavoriteMatchesTab(
                matches = favoriteMatches,
                onMatchClick = onMatchClick,
                onFavoriteClick = { partido ->
                    viewModel.toggleMatchFavorite(partido)
                    Toast.makeText(
                        context,
                        context.getString(R.string.removed_from_favorites),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.padding(paddingValues)
            )
            2 -> FavoriteNewsTab(
                news = favoriteNews,
                onNewsClick = onNewsClick,
                onFavoriteClick = { noticia ->
                    viewModel.toggleNewsFavorite(noticia)
                    Toast.makeText(
                        context,
                        context.getString(R.string.removed_from_favorites),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
private fun FavoriteLeaguesTab(
    leagues: List<com.infosport.compose.data.model.Liga>,
    onLeagueClick: (String) -> Unit,
    onFavoriteClick: (com.infosport.compose.data.model.Liga) -> Unit,
    modifier: Modifier = Modifier
) {
    if (leagues.isEmpty()) {
        EmptyFavoritesPlaceholder(
            icon = Icons.Default.EmojiEvents,
            message = stringResource(R.string.no_favorite_leagues),
            modifier = modifier
        )
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(leagues, key = { it.id }) { liga ->
                LeagueCard(
                    liga = liga,
                    onClick = { onLeagueClick(liga.id) },
                    onFavoriteClick = { onFavoriteClick(liga) }
                )
            }
        }
    }
}

@Composable
private fun FavoriteMatchesTab(
    matches: List<com.infosport.compose.data.model.Partido>,
    onMatchClick: (Int) -> Unit,
    onFavoriteClick: (com.infosport.compose.data.model.Partido) -> Unit,
    modifier: Modifier = Modifier
) {
    if (matches.isEmpty()) {
        EmptyFavoritesPlaceholder(
            icon = Icons.Default.SportsSoccer,
            message = stringResource(R.string.no_favorite_matches),
            modifier = modifier
        )
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(matches, key = { it.id }) { partido ->
                MatchCard(
                    partido = partido,
                    onClick = { onMatchClick(partido.id) },
                    onFavoriteClick = { onFavoriteClick(partido) }
                )
            }
        }
    }
}

@Composable
private fun FavoriteNewsTab(
    news: List<com.infosport.compose.data.model.Noticia>,
    onNewsClick: (Int) -> Unit,
    onFavoriteClick: (com.infosport.compose.data.model.Noticia) -> Unit,
    modifier: Modifier = Modifier
) {
    if (news.isEmpty()) {
        EmptyFavoritesPlaceholder(
            icon = Icons.Default.Newspaper,
            message = stringResource(R.string.no_favorite_news),
            modifier = modifier
        )
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(news, key = { it.id }) { noticia ->
                NewsCard(
                    noticia = noticia,
                    onClick = { onNewsClick(noticia.id) },
                    onFavoriteClick = { onFavoriteClick(noticia) }
                )
            }
        }
    }
}

@Composable
private fun EmptyFavoritesPlaceholder(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
