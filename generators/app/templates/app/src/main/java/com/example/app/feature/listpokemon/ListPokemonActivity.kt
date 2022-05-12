package <%= package %>.feature.listpokemon

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import <%= package %>.base.BaseActivity
import <%= package %>.databinding.ActivityListPokemonBinding
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

class ListPokemonActivity : BaseActivity<ListPokemonViewModel>(){

    override val viewModelClass: Class<ListPokemonViewModel> get() = ListPokemonViewModel::class.java
    private lateinit var binding: ActivityListPokemonBinding

    private var adapter: ListPokemonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListPokemonAdapter()
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter

        adapter?.addLoadStateListener { loadStates ->
            when (loadStates.refresh) {
                is LoadState.Loading -> {
                    // show progressbar
                    binding.pb.visibility = View.VISIBLE
                    binding.rv.visibility = View.GONE
                }
                !is LoadState.Loading -> {
                    // show data and can retry
                    binding.pb.visibility = View.GONE
                    binding.rv.visibility = View.VISIBLE
                }
                is LoadState.Error -> {
                    val errorState = loadStates.refresh as LoadState.Error
                    Timber.e(Throwable(errorState.error.cause))
                    Toast.makeText(this, errorState.error.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.dataFlow.collectLatest { pagingData ->
                adapter?.submitData(pagingData)
            }
        }

        viewModel.loadData()
    }

}