package <%= package %>.di.modules

import android.content.Context
import androidx.room.Room
import <%= package %>.App
import <%= package %>.data.AppDatabase
import <%= package %>.data.AppDatabase.Companion.MIGRATION_1_2
import dagger.Provides
import <%= package %>.di.scopes.ApplicationContext
import dagger.Module
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @ApplicationContext
    fun provideContext(application: App): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideAppDatabase(application: App): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "<%= app_name %>.db")
            //.addMigrations(MIGRATION_1_2) //TODO enable this to test migration from v1 to v2
            .build()
    }

}