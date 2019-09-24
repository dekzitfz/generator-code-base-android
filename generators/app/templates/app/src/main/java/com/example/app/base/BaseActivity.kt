package <%= package %>.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity(){

    @Inject lateinit var factory: ViewModelProvider.Factory
    protected lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        viewModel = ViewModelProviders.of(this, factory).get(viewModelClass)
    }

    protected abstract val viewModelClass: Class<T>
    protected abstract fun getLayout(): Int
}