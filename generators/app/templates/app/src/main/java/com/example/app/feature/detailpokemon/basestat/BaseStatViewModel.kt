package <%= package %>.feature.detailpokemon.basestat

import <%= package %>.base.BaseViewModel
import <%= package %>.data.DataManager
import <%= package %>.model.api.detailpokemon.DetailPokemonResponse
import javax.inject.Inject

class BaseStatViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel(){

    fun generateBaseStat(data: DetailPokemonResponse) = data.stats

}