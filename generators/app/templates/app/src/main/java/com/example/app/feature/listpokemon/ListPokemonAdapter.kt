package <%= package %>.feature.listpokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import <%= package %>.R
import <%= package %>.data.local.pokemon.LocalPokemon
import <%= package %>.databinding.ItemPokemonBinding

class ListPokemonAdapter:
    PagedListAdapter<LocalPokemon, ListPokemonAdapter.PokemonVH>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonVH =
        PokemonVH(ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
    ))

    override fun onBindViewHolder(holder: PokemonVH, position: Int) {
        with(holder){
            val pokemon = getItem(position)
            Glide.with(this.itemView.context)
                .load(pokemon?.pokemonImageUrl)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(binding.image)
            binding.name.text = pokemon?.pokemonName
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LocalPokemon>(){
            override fun areItemsTheSame(oldItem: LocalPokemon, newItem: LocalPokemon): Boolean
                    = oldItem.pokemonName == newItem.pokemonName
            override fun areContentsTheSame(oldItem: LocalPokemon, newItem: LocalPokemon): Boolean
                    = oldItem == newItem
        }
    }

    inner class PokemonVH(val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root)

}