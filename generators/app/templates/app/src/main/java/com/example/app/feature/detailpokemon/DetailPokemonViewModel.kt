package <%= package %>.feature.detailpokemon

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import <%= package %>.base.BaseViewModel
import <%= package %>.data.DataManager
import <%= package %>.model.api.detailpokemon.DetailPokemonResponse
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("CheckResult")
class DetailPokemonViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel(){

    internal var data = MutableLiveData<DetailPokemonResponse>()

    fun loadDetail(name: String){
        dataManager.reqDetailPokemon(name)
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                {res->
                    if(res.isSuccessful){
                        val response = res.body()
                        response?.let { data.postValue(it) }
                    }else{
                        Timber.i("response code is ${res.code()}")
                    }
                },
                {err ->
                    Timber.e(err)
                }
            )
    }

}