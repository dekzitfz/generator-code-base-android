package <%= package %>.feature.detailpokemon

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import <%= package %>.feature.detailpokemon.moves.MovesFragment

class TabAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    private var fragments: MutableList<Fragment> = mutableListOf()

    fun addFragments(fragment: Fragment){
        fragments.add(fragment)
    }

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}