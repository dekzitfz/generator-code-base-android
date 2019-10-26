package <%= package %>.data.local.pokemon

import androidx.lifecycle.LiveData
import androidx.room.*
import <%= package %>.model.api.pokemon.Pokemon

@Dao
interface LocalPokemonDao {
    @Query("SELECT * FROM pokemon")
    fun loadAllPokemon(): LiveData<List<LocalPokemon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPokemon(pokemonList: List<LocalPokemon>)

    @Query("DELETE FROM pokemon")
    fun deleteAllPokemon()

    @Transaction
    fun renewAllData(pokemonList: List<LocalPokemon>) {
        deleteAllPokemon()
        saveAllPokemon(pokemonList)
    }
}