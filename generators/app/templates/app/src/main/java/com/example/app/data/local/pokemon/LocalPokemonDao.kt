package <%= package %>.data.local.pokemon

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface LocalPokemonDao {
    @Query("SELECT * FROM pokemon")
    fun loadPagedData(): PagingSource<Int, LocalPokemon>

    @Query("SELECT * FROM pokemon")
    suspend fun loadAllPokemon(): List<LocalPokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(vararg pokemon: LocalPokemon)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPokemon(pokemons: List<LocalPokemon>)

    @Query("DELETE FROM pokemon")
    suspend fun clearALl()

    @Query("SELECT COUNT(id) FROM pokemon")
    suspend fun count(): Int
}