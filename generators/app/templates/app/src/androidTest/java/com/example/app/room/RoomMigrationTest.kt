package <%= package %>.room

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import <%= package %>.data.AppDatabase
import <%= package %>.data.local.pokemon.LocalPokemon
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoomMigrationTest {
    private val TEST_DB = "migration-test"

    @get:Rule
    val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDatabase::class.java
    )

    /**
     * room migration step 3: test auto migration
     *
     * rebuild your project to generate new schema and run './gradlew connectedAndroidTest'
     *
     * reference:
     * - https://developer.android.com/training/data-storage/room/migrating-db-versions#single-migration-test
     * - https://stackoverflow.com/questions/69026958/when-testing-room-automigrations-what-do-i-pass-to-runmigrationsandvalidate
     */
    @Test
    @Throws(IOException::class)
    fun migrate1To2() {
        var db = helper.createDatabase(TEST_DB, 1)
        populateData(3).forEach {
            insertData(it, db)
        }
        db.close()

        // Re-open the database with version 2
        db = helper.runMigrationsAndValidate(TEST_DB, 2, true)

        // MigrationTestHelper automatically verifies the schema changes,
        // but you need to validate that the data was migrated properly.
    }

    private fun insertData(pokemon: LocalPokemon, db: SupportSQLiteDatabase) {
        val values = ContentValues()
        values.put("id", pokemon.id)
        values.put("pokemon_name", pokemon.pokemonName)
        values.put("pokemon_image_url", pokemon.pokemonImageUrl)
        db.insert("pokemon", SQLiteDatabase.CONFLICT_REPLACE, values)
    }

    private fun populateData(count: Int): List<LocalPokemon> {
        val result = mutableListOf<LocalPokemon>()

        for (i in 1 until count + 1) {
            val lf = LocalPokemon(
                i,
                "pokemon $i",
                "url $i"
            )
            result.add(lf)
        }

        return result
    }

}