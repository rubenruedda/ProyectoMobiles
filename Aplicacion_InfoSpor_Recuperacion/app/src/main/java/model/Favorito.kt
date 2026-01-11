package model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritos", primaryKeys = ["tipo", "refId"])
data class Favorito(
    val tipo: String,
    val refId: String
)