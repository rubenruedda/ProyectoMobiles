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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.infosport.compose.R
import com.infosport.compose.data.model.Liga
import com.infosport.compose.ui.theme.FavoriteActive
import com.infosport.compose.ui.theme.FavoriteInactive

@Composable
fun LeagueCard(
    liga: Liga,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo de la liga
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(liga.logoUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = liga.nombre,
                modifier = Modifier.size(56.dp),
                contentScale = ContentScale.Fit,
                error = painterResource(R.drawable.ic_launcher_foreground)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Info de la liga
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = liga.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = liga.pais,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            // Botón de favorito
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (liga.esFavorita) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorito",
                    tint = if (liga.esFavorita) FavoriteActive else FavoriteInactive
                )
            }
        }
    }
}
