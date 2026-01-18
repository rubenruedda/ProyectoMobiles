package com.infosport.compose.data.dao

import androidx.room.*
import com.infosport.compose.data.model.Liga
import kotlinx.coroutines.flow.Flow

@Dao
interface LigaDao {
    @Query("SELECT * FROM liga ORDER BY nombre ASC")
    fun obtenerTodasLasLigas(): Flow<List<Liga>>

    @Query("SELECT * FROM liga WHERE es_favorita = 1 ORDER BY nombre ASC")
    fun obtenerLigasFavoritas(): Flow<List<Liga>>

    @Query("SELECT * FROM liga WHERE id = :ligaId LIMIT 1")
    fun obtenerLigaPorId(ligaId: String): Flow<Liga?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarLigas(ligas: List<Liga>)

    @Update
    suspend fun actualizarLiga(liga: Liga)

    @Query("UPDATE liga SET es_favorita = :esFavorita WHERE id = :ligaId")
    suspend fun actualizarFavorito(ligaId: String, esFavorita: Boolean)
}
