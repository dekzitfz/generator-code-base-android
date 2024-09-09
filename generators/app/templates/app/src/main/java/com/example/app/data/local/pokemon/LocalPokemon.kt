package <%= package %>.data.local.pokemon

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * room migration requirements:
 * - enable export schema (already enabled by default)
 *
 * room migration step 1: create new column, for example in this class is 'pokemon_type'
 */
@Entity(tableName = "pokemon")
data class LocalPokemon(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "pokemon_name") val pokemonName: String = "",
    @ColumnInfo(name = "pokemon_image_url") val pokemonImageUrl: String = "",
//    @ColumnInfo(name = "pokemon_type") val pokemonType: String? = null
)