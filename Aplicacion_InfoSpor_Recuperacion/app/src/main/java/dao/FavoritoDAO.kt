package dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import model.Favorito

@Dao
interface FavoritoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorito: Favorito)

    @Delete
    suspend fun delete(favorito: Favorito)

    @Query("SELECT EXISTS(SELECT 1 FROM favoritos WHERE tipo = :tipo AND refId = :refId LIMIT 1)")
    fun esFavorito(tipo: String, refId: String): LiveData<Boolean>
}