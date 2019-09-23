package <%= package %>.base

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


abstract class BaseFragmentViewModel<T : BaseViewModel> : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    protected abstract var viewModel: T
    protected abstract val viewModelClass: Class<T>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(viewModelClass)
    }


}