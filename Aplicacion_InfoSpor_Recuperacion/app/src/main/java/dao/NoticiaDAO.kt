package dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import model.Noticia

@Dao
interface NoticiaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarNoticias(noticias: List<Noticia>)

    @Query("SELECT * FROM noticia ORDER BY fecha_publicacion DESC")
    fun obtenerTodasLasNoticias(): LiveData<List<Noticia>>

    // Búsqueda de Noticias
    @Query("SELECT * FROM noticia WHERE titulo LIKE '%' || :query || '%' ORDER BY fecha_publicacion DESC")
    fun buscarNoticias(query: String): LiveData<List<Noticia>>

    @Query("SELECT * FROM noticia WHERE id = :noticiaId LIMIT 1")
    fun obtenerNoticiaPorId(noticiaId: Int): LiveData<Noticia>

    @Update
    suspend fun actualizarNoticia(noticia: Noticia)
}