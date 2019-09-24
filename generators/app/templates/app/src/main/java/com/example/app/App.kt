package <%= package %>

import android.app.Application
import android.app.Activity
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import <%= package %>.di.component.AppComponent
import <%= package %>.di.modules.NetworkModule
import <%= package %>.di.component.DaggerAppComponent
import timber.log.Timber

class App : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .networkModule(NetworkModule())
            .build()

        appComponent?.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>  = dispatchingAndroidInjector
}