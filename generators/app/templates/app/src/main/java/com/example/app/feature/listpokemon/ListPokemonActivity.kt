package <%= package %>.feature.listpokemon

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import <%= package %>.R
import <%= package %>.base.BaseActivity
import <%= package %>.databinding.ActivityListPokemonBinding

class ListPokemonActivity : BaseActivity<ListPokemonViewModel>(){

    override val viewModelClass: Class<ListPokemonViewModel> get() = ListPokemonViewModel::class.java
    override fun getLayout(): Int = R.layout.activity_list_pokemon
    private lateinit var binding: ActivityListPokemonBinding

    private var adapter: ListPokemonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListPokemonAdapter()
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter

        viewModel.pokemonData.observe(this, Observer {
            adapter?.submitList(it)
        })

        viewModel.loadAllPokemonFromNetwork()
    }

}