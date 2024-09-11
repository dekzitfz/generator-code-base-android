package <%= package %>.feature.listpokemon

import androidx.paging.*
import <%= package %>.base.BaseViewModel
import <%= package %>.data.DataManager
import <%= package %>.model.api.pokemon.Pokemon
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.viewModelScope
import <%= package %>.data.local.pokemon.LocalPokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@OptIn(ExperimentalPagingApi::class)
class ListPokemonViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel(){

    lateinit var dataFlow: Flow<PagingData<Pokemon>>
    lateinit var localDataFlow: Flow<PagingData<LocalPokemon>>

    fun loadData() {
        localDataFlow = Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = ListPokemonRemoteMediator(dataManager)
        ) {
            dataManager.loadPagedCachedPokemon()
        }.flow.cachedIn(viewModelScope)

        /**
         * use this to fetch data from network without insert to local db
         * need to update adapter and using [dataFlow] instead of [localDataFlow]
         */
        /*dataFlow = Pager(
            config = PagingConfig(pageSize = 10, initialLoadSize = 10),
            pagingSourceFactory = { ListPokemonDataSource(dataManager) }
        ).flow.cachedIn(viewModelScope)*/

    }

    /***
     * an example to load data from local db
     */
    fun loadPokemonFromLocal(){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val localPokemon = dataManager.loadAllCachedPokemon()
                Timber.i("total local pokemon: ${localPokemon.size}")
            }
        }
    }

}