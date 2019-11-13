package <%= package %>.room

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.room.testing.MigrationTestHelper
import androidx.test.platform.app.InstrumentationRegistry
import <%= package %>.data.AppDatabase

object MigrationUtil {
    fun getDatabaseAfterPerformingMigrations(
        migrationTestHelper: MigrationTestHelper,
        databaseClass: Class<AppDatabase>,
        databaseName: String,
        vararg migrations: Migration
    ): RoomDatabase {
        val roomDatabase = Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            databaseClass, databaseName
        )
            .addMigrations(*migrations)
            .build()
        migrationTestHelper.closeWhenFinished(roomDatabase)
        return roomDatabase

    }
}