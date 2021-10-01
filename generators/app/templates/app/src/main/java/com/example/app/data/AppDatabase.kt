package <%= package %>.data

import androidx.room.Database
import androidx.room.RoomDatabase
import <%= package %>.data.local.pokemon.LocalPokemon
import <%= package %>.data.local.pokemon.LocalPokemonDao
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration


@Database(entities = [LocalPokemon::class], version = 1) //TODO bump version to 2 to test migration from v1 to v2
abstract class AppDatabase : RoomDatabase() {

    abstract fun PokemonDao(): LocalPokemonDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE pokemon ADD COLUMN pokemon_type TEXT")
            }
        }
    }



}