package <%= package %>.feature.detailpokemon.basestat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import <%= package %>.base.BaseFragment
import <%= package %>.databinding.FragmentBaseStatBinding
import <%= package %>.model.api.detailpokemon.DetailPokemonResponse
import com.google.gson.Gson

class BaseStatFragment: BaseFragment<BaseStatViewModel>() {
    override val viewModelClass: Class<BaseStatViewModel> = BaseStatViewModel::class.java
    private var _binding: FragmentBaseStatBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: BaseStatAdapter

    companion object{
        const val KEY_STAT = "STAT"
        fun newInstance(data: String) = BaseStatFragment().apply {
            arguments = bundleOf(
                KEY_STAT to data
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBaseStatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(KEY_STAT)?.let {
            val data = Gson().fromJson(it, DetailPokemonResponse::class.java)
            adapter = BaseStatAdapter(viewModel.generateBaseStat(data))
            _binding?.let { v->
                v.rv.layoutManager = LinearLayoutManager(context)
                v.rv.adapter = adapter
            }
        }
    }
}