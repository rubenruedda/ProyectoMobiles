package com.infosport.compose.data.dao

import androidx.room.*
import com.infosport.compose.data.model.Noticia
import kotlinx.coroutines.flow.Flow

@Dao
interface NoticiaDao {
    @Query("SELECT * FROM noticia ORDER BY fecha_publicacion DESC")
    fun obtenerTodasLasNoticias(): Flow<List<Noticia>>

    @Query("SELECT * FROM noticia WHERE es_favorita = 1 ORDER BY fecha_publicacion DESC")
    fun obtenerNoticiasFavoritas(): Flow<List<Noticia>>

    @Query("SELECT * FROM noticia WHERE id = :noticiaId LIMIT 1")
    fun obtenerNoticiaPorId(noticiaId: Int): Flow<Noticia?>

    @Query("SELECT * FROM noticia WHERE titulo LIKE '%' || :query || '%' OR contenido LIKE '%' || :query || '%' ORDER BY fecha_publicacion DESC")
    fun buscarNoticias(query: String): Flow<List<Noticia>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarNoticias(noticias: List<Noticia>)

    @Update
    suspend fun actualizarNoticia(noticia: Noticia)

    @Query("UPDATE noticia SET es_favorita = :esFavorita WHERE id = :noticiaId")
    suspend fun actualizarFavorito(noticiaId: Int, esFavorita: Boolean)
}
