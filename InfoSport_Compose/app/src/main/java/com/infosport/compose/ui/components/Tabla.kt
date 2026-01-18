package com.infosport.compose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.infosport.compose.R
import com.infosport.compose.data.model.Clasificacion

@Composable
fun StandingsTable(
    clasificaciones: List<Clasificacion>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "#",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(28.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Equipo",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "PJ",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(32.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "G",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(32.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "E",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(32.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "P",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(32.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Pts",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(40.dp),
                    textAlign = TextAlign.Center
                )
            }
            
            HorizontalDivider()
            
            // Filas
            clasificaciones.forEach { clasificacion ->
                StandingsRow(clasificacion = clasificacion)
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun StandingsRow(
    clasificacion: Clasificacion,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Posición
        Text(
            text = clasificacion.posicion.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(28.dp),
            textAlign = TextAlign.Center,
            color = when (clasificacion.posicion) {
                in 1..4 -> MaterialTheme.colorScheme.primary
                else -> MaterialTheme.colorScheme.onSurface
            }
        )
        
        // Escudo
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(clasificacion.escudoUrl)
                .crossfade(true)
                .build(),
            contentDescription = clasificacion.nombreEquipo,
            modifier = Modifier.size(28.dp),
            contentScale = ContentScale.Fit,
            error = painterResource(R.drawable.ic_launcher_foreground)
        )
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // Nombre
        Text(
            text = clasificacion.nombreEquipo,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        
        // Estadísticas
        Text(
            text = clasificacion.partidosJugados.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.width(32.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = clasificacion.partidosGanados.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.width(32.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = clasificacion.partidosEmpatados.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.width(32.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = clasificacion.partidosPerdidos.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.width(32.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = clasificacion.puntos.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(40.dp),
            textAlign = TextAlign.Center
        )
    }
}
