package com.infosport.compose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.infosport.compose.R
import com.infosport.compose.data.model.Partido
import com.infosport.compose.ui.theme.FavoriteActive
import com.infosport.compose.ui.theme.FavoriteInactive
import com.infosport.compose.ui.theme.ScoreDraw
import com.infosport.compose.ui.theme.ScoreLoss
import com.infosport.compose.ui.theme.ScoreWin

@Composable
fun MatchCard(
    partido: Partido,
    onClick: () -> Unit,
    onFavoriteClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Hora del partido
            Text(
                text = partido.hora,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
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
                        modifier = Modifier.size(48.dp),
                        contentScale = ContentScale.Fit,
                        error = painterResource(R.drawable.ic_launcher_foreground)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = partido.nombreLocal,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                
                // Marcador
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    if (partido.marcadorLocal != null && partido.marcadorVisitante != null) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val localScore = partido.marcadorLocal.toIntOrNull() ?: 0
                            val visitanteScore = partido.marcadorVisitante.toIntOrNull() ?: 0
                            
                            Text(
                                text = partido.marcadorLocal,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = when {
                                    localScore > visitanteScore -> ScoreWin
                                    localScore < visitanteScore -> ScoreLoss
                                    else -> ScoreDraw
                                }
                            )
                            Text(
                                text = " - ",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = partido.marcadorVisitante,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = when {
                                    visitanteScore > localScore -> ScoreWin
                                    visitanteScore < localScore -> ScoreLoss
                                    else -> ScoreDraw
                                }
                            )
                        }
                        Text(
                            text = "FT",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        Text(
                            text = "vs",
                            style = MaterialTheme.typography.headlineSmall,
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
                        modifier = Modifier.size(48.dp),
                        contentScale = ContentScale.Fit,
                        error = painterResource(R.drawable.ic_launcher_foreground)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = partido.nombreVisitante,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Fecha y botón de favorito
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = partido.fecha,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                if (onFavoriteClick != null) {
                    IconButton(onClick = onFavoriteClick) {
                        Icon(
                            imageVector = if (partido.esFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = if (partido.esFavorito) FavoriteActive else FavoriteInactive
                        )
                    }
                }
            }
        }
    }
}
