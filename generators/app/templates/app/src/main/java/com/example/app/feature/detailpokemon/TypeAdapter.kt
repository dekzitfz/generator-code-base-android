package <%= package %>.feature.detailpokemon

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import <%= package %>.databinding.ViewPokemonTypeBinding
import <%= package %>.model.api.detailpokemon.TypesItem

class TypeAdapter(val data: List<TypesItem>): RecyclerView.Adapter<TypeAdapter.TypeHolder>() {
    class TypeHolder(val binding: ViewPokemonTypeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeHolder {
        return TypeHolder(ViewPokemonTypeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: TypeHolder, position: Int) {
        with(holder){
            binding.type.text = data[position].type?.name
            binding.typeColor.setCardBackgroundColor(Color.parseColor(getColor(data[position].type?.name!!)))
        }
    }

    override fun getItemCount(): Int = data.size

    private fun getColor(type: String): String {
        if(type == "fire"){
            return "#f08030"
        }else if(type == "water"){
            return "#6890f0"
        }else if(type == "grass"){
            return "#78c850"
        }else if(type == "electric"){
            return "#f8d030"
        }else if(type == "ice"){
            return "#98d8d8"
        }else if(type == "fighting"){
            return "#c03028"
        }else if(type == "poison"){
            return "#a040a0"
        }else if(type == "ground"){
            return "#e0c068"
        }else if(type == "flying"){
            return "#a890f0"
        }else if(type == "psychic"){
            return "#f85888"
        }else if(type == "bug"){
            return "#a8b820"
        }else if(type == "rock"){
            return "#b8a038"
        }else if(type == "ghost"){
            return "#705898"
        }else if(type == "dark"){
            return "#705848"
        }else if(type == "dragon"){
            return "#7038f8"
        }else if(type == "steel"){
            return "#b8b8d0"
        }else if(type == "fairy"){
            return "#f0b6bc"
        }else{
            return "#a8a878"
        }
    }
}