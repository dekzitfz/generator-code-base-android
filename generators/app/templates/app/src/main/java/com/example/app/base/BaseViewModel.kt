package <%= package %>.base

import io.reactivex.disposables.CompositeDisposable
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable


abstract class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    protected abstract val locale: String

    override fun onCleared() {
        disposables.clear()
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}