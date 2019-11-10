package <%= package %>.feature.listpokemon

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import <%= package %>.R
import <%= package %>.base.BaseActivity
import kotlinx.android.synthetic.main.activity_list_pokemon.*

class ListPokemonActivity : BaseActivity<ListPokemonViewModel>(){

    override val viewModelClass: Class<ListPokemonViewModel> get() = ListPokemonViewModel::class.java
    override fun getLayout(): Int = R.layout.activity_list_pokemon

    private var adapter: ListPokemonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = ListPokemonAdapter()
        rv?.layoutManager = LinearLayoutManager(this)
        rv?.adapter = adapter

        viewModel.pokemonData.observe(this, Observer {
            adapter?.submitList(it)
        })

        viewModel.loadAllPokemonFromNetwork()
    }

}