package <%= package %>.di.modules

import dagger.Module
import androidx.lifecycle.ViewModel
import <%= package %>.di.scopes.ViewModelKey
import <%= package %>.feature.listpokemon.ListPokemonViewModel
import dagger.multibindings.IntoMap
import dagger.Binds
import <%= package %>.base.AppViewModelFactory
import androidx.lifecycle.ViewModelProvider

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListPokemonViewModel::class)
    abstract fun bindListPokemonViewModel(listPokemonViewModel: ListPokemonViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}