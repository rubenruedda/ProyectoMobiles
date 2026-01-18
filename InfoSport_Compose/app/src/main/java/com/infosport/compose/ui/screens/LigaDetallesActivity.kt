package com.infosport.compose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.infosport.compose.R
import com.infosport.compose.ui.components.MatchCard
import com.infosport.compose.ui.components.StandingsTable
import com.infosport.compose.ui.theme.FavoriteActive
import com.infosport.compose.ui.theme.FavoriteInactive
import com.infosport.compose.ui.viewmodel.LigasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeagueDetailScreen(
    ligaId: String,
    viewModel: LigasViewModel,
    onBackClick: () -> Unit,
    onMatchClick: (Int) -> Unit
) {
    LaunchedEffect(ligaId) {
        viewModel.selectLeague(ligaId)
    }
    
    val selectedLeague by viewModel.selectedLeague.collectAsState()
    val standings by viewModel.standings.collectAsState()
    val matches by viewModel.leagueMatches.collectAsState()
    
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        stringResource(R.string.league_standings),
        stringResource(R.string.league_matches)
    )
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        selectedLeague?.logoUrl?.let { url ->
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(url)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                modifier = Modifier.size(32.dp),
                                contentScale = ContentScale.Fit,
                                error = painterResource(R.drawable.ic_launcher_foreground)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                        Text(
                            text = selectedLeague?.nombre ?: "",
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                actions = {
                    selectedLeague?.let { liga ->
                        IconButton(onClick = { viewModel.toggleFavorite(liga) }) {
                            Icon(
                                imageVector = if (liga.esFavorita) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorito",
                                tint = if (liga.esFavorita) FavoriteActive else FavoriteInactive
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Tabs de clasificacion y partidos
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }
            
            // Muestro clasificacion o partidos segun el tab
            when (selectedTab) {
                0 -> {
                    // Clasificacion
                    if (standings.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.loading),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            item {
                                StandingsTable(clasificaciones = standings)
                            }
                        }
                    }
                }
                1 -> {
                    // Partidos de la liga
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(matches, key = { it.id }) { partido ->
                            MatchCard(
                                partido = partido,
                                onClick = { onMatchClick(partido.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}
