package <%= package %>.feature.detailpokemon.moves

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import <%= package %>.base.BaseFragment
import <%= package %>.databinding.FragmentMovesBinding

class MovesFragment: BaseFragment<MovesViewModel>() {
    override val viewModelClass: Class<MovesViewModel> = MovesViewModel::class.java
    private var _binding: FragmentMovesBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MovesAdapter

    companion object {
        const val KEY_MOVES = "MOVES"
        fun newInstance(data: String) = MovesFragment().apply {
            arguments = bundleOf(
                KEY_MOVES to data
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(KEY_MOVES)?.let {
            adapter = MovesAdapter(viewModel.generateMoves(it))
            _binding?.let { v ->
                v.rv.layoutManager = LinearLayoutManager(context)
                v.rv.adapter = adapter
            }
        }
    }
}