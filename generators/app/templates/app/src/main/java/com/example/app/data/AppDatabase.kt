package <%= package %>.data

import androidx.room.Database
import androidx.room.RoomDatabase
import <%= package %>.data.local.pokemon.LocalPokemon
import <%= package %>.data.local.pokemon.LocalPokemonDao

@Database(entities = [LocalPokemon::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun PokemonDao(): LocalPokemonDao
}