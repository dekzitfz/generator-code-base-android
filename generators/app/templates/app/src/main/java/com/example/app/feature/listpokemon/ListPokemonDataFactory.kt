package <%= package %>.feature.listpokemon

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import <%= package %>.data.DataManager
import <%= package %>.model.api.pokemon.Pokemon

class ListPokemonDataFactory
    (var dataManager: DataManager): DataSource.Factory<Int,Pokemon>(){

    var dataSource = MutableLiveData<ListPokemonDataSource>()

    override fun create(): DataSource<Int, Pokemon> {
        val pokemonDataSource = ListPokemonDataSource(dataManager)
        dataSource.postValue(pokemonDataSource)
        return pokemonDataSource
    }

}