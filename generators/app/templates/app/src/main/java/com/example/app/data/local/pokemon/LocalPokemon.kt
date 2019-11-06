package <%= package %>.data.local.pokemon

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class LocalPokemon(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "pokemon_namae") val pokemonName: String = "",
    @ColumnInfo(name = "pokemon_image_url") val pokemonImageUrl: String = ""
)