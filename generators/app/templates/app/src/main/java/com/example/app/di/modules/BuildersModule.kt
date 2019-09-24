package <%= package %>.di.modules

import <%= package %>.feature.listpokemon.ListPokemonActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Module
abstract class BuildersModule{
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindListPokemonActivity(): ListPokemonActivity
}