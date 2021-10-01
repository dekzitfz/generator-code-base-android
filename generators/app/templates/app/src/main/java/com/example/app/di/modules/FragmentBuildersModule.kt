package <%= package %>.di.modules

import <%= package %>.feature.detailpokemon.basestat.BaseStatFragment
import <%= package %>.feature.detailpokemon.moves.MovesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseStatFragment(): BaseStatFragment

    @ContributesAndroidInjector
    abstract fun contributeMovesFragment(): MovesFragment

}