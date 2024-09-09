package <%= package %>.room

import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import androidx.sqlite.db.SupportSQLiteDatabase
import <%= package %>.data.AppDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.room.testing.MigrationTestHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import <%= package %>.data.local.pokemon.LocalPokemon
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * old room migration test
 * will be used for future reference only
 */

//@RunWith(AndroidJUnit4::class)
class MigrationTest {

    private val TEST_DB = "migration-test"

    /*@get:Rule val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDatabase::class.java.canonicalName!!,
        FrameworkSQLiteOpenHelperFactory()
    )*/

    /*@Test
    @Throws(IOException::class)
    fun onMigrationFrom1To2_CheckIf_TableContainsExistingData() {
        val db = helper.createDatabase(TEST_DB, 1)
        val fakeData = getFakeData(5)

        for (f in fakeData) {
            insertData(f, db)
        }
        db.close()

        //Re-Open Database in version 2 by providing the necessary migrations and validate the schema
        helper.runMigrationsAndValidate(TEST_DB, 2, true, AppDatabase.MIGRATION_1_2)

        val appDatabase = MigrationUtil
            .getDatabaseAfterPerformingMigrations(
                helper, AppDatabase::class.java, TEST_DB,
                AppDatabase.MIGRATION_1_2
            ) as AppDatabase

        //Now checking if the new database contains the correctly exported data from the previous database
        val migratedDataFeedDao = appDatabase.pokemonDao()
        Assert.assertEquals(
            migratedDataFeedDao.loadPokemonById("pokemon 2").pokemonName,
            fakeData[2].pokemonName
        )
        appDatabase.close()
    }*/


    private fun insertData(pokemon: LocalPokemon, db: SupportSQLiteDatabase) {
        val values = ContentValues()
        values.put("pokemon_id", pokemon.id)
        values.put("pokemon_name", pokemon.pokemonName)
        values.put("pokemon_image_url", pokemon.pokemonImageUrl)
        db.insert("pokemon", SQLiteDatabase.CONFLICT_REPLACE, values)
    }

    private fun getFakeData(count: Int): List<LocalPokemon> {
        val result = mutableListOf<LocalPokemon>()

        for (i in 0 until count) {
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