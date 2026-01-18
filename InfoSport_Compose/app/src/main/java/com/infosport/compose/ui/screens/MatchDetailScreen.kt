package com.infosport.compose.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.infosport.compose.R
import com.infosport.compose.data.model.Evento
import com.infosport.compose.data.model.Partido
import com.infosport.compose.data.repository.InfoSportRepository
import com.infosport.compose.ui.theme.*
import kotlinx.coroutines.flow.catch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailScreen(
    partidoId: Int,
    repository: InfoSportRepository,
    onBackClick: () -> Unit
) {
    var partido by remember { mutableStateOf<Partido?>(null) }
    var eventos by remember { mutableStateOf<List<Evento>>(emptyList()) }
    
    LaunchedEffect(partidoId) {
        repository.obtenerPartidoPorId(partidoId)
            .catch { e -> Log.e("MatchDetail", "Error: ${e.message}") }
            .collect { partido = it }
    }
    
    LaunchedEffect(partidoId) {
        repository.obtenerEventosPorPartido(partidoId)
            .catch { e -> Log.e("MatchDetail", "Error eventos: ${e.message}") }
            .collect { eventos = it }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.match_detail_title),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        partido?.let { match ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header con equipos y marcador
                item {
                    MatchHeader(partido = match)
                }
                
                // Información del partido
                item {
                    MatchInfo(partido = match)
                }
                
                // Eventos
                if (eventos.isNotEmpty()) {
                    item {
                        Text(
                            text = stringResource(R.string.match_events),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    
                    items(eventos, key = { it.id }) { evento ->
                        EventoItem(evento = evento)
                    }
                }
            }
        } ?: Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun MatchHeader(partido: Partido) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Equipo Local
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(partido.escudoLocal)
                            .crossfade(true)
                            .build(),
                        contentDescription = partido.nombreLocal,
                        modifier = Modifier.size(72.dp),
                        contentScale = ContentScale.Fit,
                        error = painterResource(R.drawable.ic_launcher_foreground)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = partido.nombreLocal,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
                
                // Marcador
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (partido.marcadorLocal != null && partido.marcadorVisitante != null) {
                        Text(
                            text = "${partido.marcadorLocal} - ${partido.marcadorVisitante}",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Final",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        Text(
                            text = partido.hora,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                
                // Equipo Visitante
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(partido.escudoVisitante)
                            .crossfade(true)
                            .build(),
                        contentDescription = partido.nombreVisitante,
                        modifier = Modifier.size(72.dp),
                        contentScale = ContentScale.Fit,
                        error = painterResource(R.drawable.ic_launcher_foreground)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = partido.nombreVisitante,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun MatchInfo(partido: Partido) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = partido.fecha,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = partido.hora,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun EventoItem(evento: Evento) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Minuto
            Text(
                text = "${evento.minuto}'",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(48.dp)
            )
            
            // Icono según tipo
            Icon(
                imageVector = when (evento.tipo) {
                    "Gol" -> Icons.Default.SportsSoccer
                    "Tarjeta Amarilla" -> Icons.Default.Square
                    "Tarjeta Roja" -> Icons.Default.Square
                    "Sustitución" -> Icons.Default.SwapHoriz
                    else -> Icons.Default.Info
                },
                contentDescription = evento.tipo,
                tint = when (evento.tipo) {
                    "Gol" -> ScoreWin
                    "Tarjeta Amarilla" -> Warning
                    "Tarjeta Roja" -> Error
                    else -> MaterialTheme.colorScheme.primary
                },
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Jugador
            Text(
                text = evento.nombreJugador,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
