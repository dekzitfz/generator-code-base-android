package <%= package %>.di.modules

import dagger.Module
import androidx.lifecycle.ViewModel
import <%= package %>.di.scopes.ViewModelKey
import <%= package %>.feature.listpokemon.ListPokemonViewModel
import dagger.multibindings.IntoMap
import dagger.Binds
import <%= package %>.base.AppViewModelFactory
import androidx.lifecycle.ViewModelProvider
import <%= package %>.feature.detailpokemon.DetailPokemonViewModel
import <%= package %>.feature.detailpokemon.basestat.BaseStatViewModel
import <%= package %>.feature.detailpokemon.moves.MovesViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BaseStatViewModel::class)
    abstract fun bindBaseStatViewModel(baseStatViewModel: BaseStatViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovesViewModel::class)
    abstract fun bindMovesViewModel(movesViewModel: MovesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailPokemonViewModel::class)
    abstract fun bindDetailPokemonViewModel(detailPokemonViewModel: DetailPokemonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListPokemonViewModel::class)
    abstract fun bindListPokemonViewModel(listPokemonViewModel: ListPokemonViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}