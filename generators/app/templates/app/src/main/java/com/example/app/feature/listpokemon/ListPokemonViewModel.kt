package <%= package %>.feature.listpokemon

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import <%= package %>.base.BaseViewModel
import <%= package %>.data.DataManager
import <%= package %>.model.api.pokemon.Pokemon
import javax.inject.Inject

class ListPokemonViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel(){
    var dataFactory: ListPokemonDataFactory? = null
    var data: LiveData<PagedList<Pokemon>>? = null

    private fun getConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .build()
    }

    fun loadPokemon(){
        dataFactory = ListPokemonDataFactory(dataManager)
        data = LivePagedListBuilder<Int, Pokemon>(dataFactory!!, getConfig()).build()
    }
}