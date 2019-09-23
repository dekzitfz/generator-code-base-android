package <%= package %>.di.modules

import android.content.Context
import <%= package %>.App
import dagger.Provides
import <%= package %>.di.scopes.ApplicationContext
import dagger.Module


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @ApplicationContext
    internal fun provideContext(application: App): Context = application.applicationContext

}