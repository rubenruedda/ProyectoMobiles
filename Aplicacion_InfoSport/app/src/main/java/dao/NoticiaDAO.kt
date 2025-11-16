package dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import model.Noticia

@Dao
interface NoticiaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarNoticias(noticias: List<Noticia>)

    // Consulta para NoticiasActivity (lista de titulares)
    @Query("SELECT * FROM noticia ORDER BY fecha_publicacion DESC")
    fun obtenerTodasLasNoticias(): LiveData<List<Noticia>>

    @Query("SELECT * FROM noticia WHERE titulo LIKE '%' || :query || '%' ORDER BY fecha_publicacion DESC")
    fun buscarNoticias(query: String): LiveData<List<Noticia>>

    // Consulta para NoticiaExpandidaActivity (detalle)
    @Query("SELECT * FROM noticia WHERE id = :noticiaId LIMIT 1")
    fun obtenerNoticiaPorId(noticiaId: Int): LiveData<Noticia>
}