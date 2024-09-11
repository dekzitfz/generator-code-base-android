package <%= package %>.feature.listpokemon

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import <%= package %>.R
import <%= package %>.databinding.ItemPokemonBinding
import <%= package %>.feature.detailpokemon.DetailPokemonActivity
import <%= package %>.data.local.pokemon.LocalPokemon
import androidx.paging.PagingDataAdapter

class ListPokemonAdapter:
    PagingDataAdapter<LocalPokemon, ListPokemonAdapter.PokemonVH>(DataComparator) {

    object DataComparator : DiffUtil.ItemCallback<LocalPokemon>() {
        override fun areItemsTheSame(oldItem: LocalPokemon, newItem: LocalPokemon) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: LocalPokemon, newItem: LocalPokemon) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonVH =
        PokemonVH(ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
    ))

    override fun onBindViewHolder(holder: PokemonVH, position: Int) {
        with(holder){
            val pokemon = getItem(position)
            Glide.with(itemView.context)
                .load(pokemon?.pokemonImageUrl)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(binding.image)
            binding.name.text = pokemon?.pokemonName

            binding.root.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, DetailPokemonActivity::class.java)
                        .putExtra(DetailPokemonActivity.POKEMON_NAME, pokemon?.pokemonName)
                )
            }
        }
    }

    inner class PokemonVH(val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root)

}