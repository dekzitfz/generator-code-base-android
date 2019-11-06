package <%= package %>.feature.listpokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import <%= package %>.R
import <%= package %>.data.local.pokemon.LocalPokemon
import kotlinx.android.synthetic.main.item_pokemon.view.*

class ListPokemonAdapter:
    PagedListAdapter<LocalPokemon, ListPokemonAdapter.PokemonVH>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonVH =
        PokemonVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false))

    override fun onBindViewHolder(holder: PokemonVH, position: Int) {
        val pokemon = getItem(position)
        Glide.with(holder.itemView.context)
            .load(pokemon?.pokemonImageUrl)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(holder.itemView.image)
        holder.itemView.name.text = pokemon?.pokemonName
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LocalPokemon>(){
            override fun areItemsTheSame(oldItem: LocalPokemon, newItem: LocalPokemon): Boolean
                    = oldItem.pokemonName == newItem.pokemonName
            override fun areContentsTheSame(oldItem: LocalPokemon, newItem: LocalPokemon): Boolean
                    = oldItem == newItem
        }
    }

    inner class PokemonVH(itemView: View): RecyclerView.ViewHolder(itemView)

}