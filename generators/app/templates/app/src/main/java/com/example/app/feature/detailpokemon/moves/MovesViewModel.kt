package <%= package %>.feature.detailpokemon.moves

import <%= package %>.base.BaseViewModel
import <%= package %>.data.DataManager
import <%= package %>.model.api.detailpokemon.MovesItem
import com.google.gson.Gson
import javax.inject.Inject

class MovesViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel(){

    fun generateMoves(data: String): MutableList<String> {
        val result: MutableList<String> = mutableListOf()
        val moves = Gson().fromJson(data, Array<MovesItem>::class.java).asList()
        for(m in moves){
            result.add(m.move.name)
        }

        return result
    }

}