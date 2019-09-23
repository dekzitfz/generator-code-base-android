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

class App : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .networkModule(NetworkModule())
            .build()

        appComponent?.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>  = dispatchingAndroidInjector
}