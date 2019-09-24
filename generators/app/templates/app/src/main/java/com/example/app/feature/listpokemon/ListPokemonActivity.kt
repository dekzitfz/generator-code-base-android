package <%= package %>.feature.listpokemon

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import <%= package %>.R
import <%= package %>.base.BaseActivity
import <%= package %>.util.NetworkState
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

        viewModel.loadPokemon()

        viewModel.dataFactory?.dataSource?.observe(this, Observer { dataSource->
            dataSource?.getNetworkState()?.observe(this, Observer {
                updateUI(it)
            })
        })

        viewModel.data?.observe(this, Observer {
            adapter?.submitList(it)
        })
    }

    private fun updateUI(state: NetworkState){
        if(state.status == NetworkState.Status.RUNNING){
            rv.visibility = View.VISIBLE
            pb.visibility = View.VISIBLE
        }else if(state.status == NetworkState.Status.SUCCESS){
            rv.visibility = View.VISIBLE
            pb.visibility = View.GONE
        }else if(state.status == NetworkState.Status.FAILED){
            rv.visibility = View.GONE
            pb.visibility = View.GONE
            Toast.makeText(this, state.msg, Toast.LENGTH_SHORT).show()
        }
    }

}