package <%= package %>.feature.detailpokemon.basestat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import <%= package %>.databinding.ViewPokemonStatBinding
import <%= package %>.model.api.detailpokemon.StatsItem

class BaseStatAdapter(val data: List<StatsItem>): RecyclerView.Adapter<BaseStatAdapter.StatHolder>() {
    class StatHolder(val binding: ViewPokemonStatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatHolder {
        return StatHolder(
            ViewPokemonStatBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: StatHolder, position: Int) {
        with(holder){
            binding.statName.text = data[position].stat?.name
            binding.baseStat.text = data[position].baseStat.toString()
        }
    }

    override fun getItemCount(): Int = data.size
}