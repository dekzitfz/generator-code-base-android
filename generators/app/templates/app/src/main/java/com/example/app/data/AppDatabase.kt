package <%= package %>.data

import androidx.room.Database
import androidx.room.RoomDatabase
import <%= package %>.data.local.pokemon.LocalPokemon
import <%= package %>.data.local.pokemon.LocalPokemonDao


/**
 * room migration step 2: update version number of database using auto migrations
 *
 * Whenever database version changes again,
 * update the list of autoMigrations adding the new one like 'AutoMigration(from = 2, to = 3)'
 *
 * reference:
 * - https://medium.com/androiddevelopers/room-auto-migrations-d5370b0ca6eb
 * - https://developer.android.com/training/data-storage/room/migrating-db-versions#kotlin
 */
@Database(
    entities = [LocalPokemon::class],
    version = 1,
    /*autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]*/
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): LocalPokemonDao
}