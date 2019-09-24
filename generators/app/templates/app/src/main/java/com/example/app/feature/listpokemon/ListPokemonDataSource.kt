package <%= package %>.feature.listpokemon

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import <%= package %>.data.DataManager
import <%= package %>.model.api.pokemon.Pokemon
import <%= package %>.util.NetworkState
import timber.log.Timber

class ListPokemonDataSource (private var dataManager: DataManager): PageKeyedDataSource<Int, Pokemon>(){

    private val networkState = MutableLiveData<NetworkState>()
    fun getNetworkState(): MutableLiveData<NetworkState> = networkState

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Pokemon>) {
        networkState.postValue(NetworkState.LOADING)
        dataManager.reqPokemon(params.requestedLoadSize, 0)
            .subscribe(
                {res ->
                    if(res.isSuccessful){
                        val response = res.body()
                        response?.results?.let {
                            callback.onResult(it, null, 1)
                            networkState.postValue(NetworkState.LOADED)
                        }
                    }else{
                        val responseCode = res.code()
                        Timber.e(Exception("response code: $responseCode"))
                        networkState.postValue(NetworkState(NetworkState.Status.FAILED, responseCode))
                    }
                },
                {err ->
                    Timber.e(err)
                    networkState.postValue(NetworkState(NetworkState.Status.FAILED))
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Pokemon>) {
        dataManager.reqPokemon(params.requestedLoadSize, 0)
            .subscribe(
                {res ->
                    if(res.isSuccessful){
                        val response = res.body()
                        response?.results?.let {
                            callback.onResult(it, params.key+1)
                        }
                    }else{
                        val responseCode = res.code()
                        Timber.e(Exception("response code: $responseCode"))
                    }
                },
                {err ->
                    Timber.e(err)
                }
            )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Pokemon>) {}

}