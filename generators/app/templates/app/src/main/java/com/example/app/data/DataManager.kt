package <%= package %>.data

import <%= package %>.data.local.pokemon.LocalPokemon
import <%= package %>.network.APIService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Singleton
import javax.inject.Inject
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import <%= package %>.model.api.detailpokemon.DetailPokemonResponse


@Singleton
class DataManager
@Inject constructor(
    private val api: APIService,
    private val prefs: PreferencesHelper,
    private val localDatabase: AppDatabase){

    /* ---------------------------------------- SQLite ------------------------------------------ */

    suspend fun insertPokemon(pokemon: LocalPokemon) {
        return localDatabase.pokemonDao().insertPokemon(pokemon)
    }

    suspend fun insertAllPokemon(pokemons: List<LocalPokemon>) {
        return localDatabase.pokemonDao().insertAllPokemon(pokemons)
    }

    suspend fun loadAllCachedPokemon(): List<LocalPokemon> {
        return localDatabase.pokemonDao().loadAllPokemon()
    }

    suspend fun getCachedPokemonCount(): Int = localDatabase.pokemonDao().count()

    suspend fun clearCachedPokemon() { localDatabase.pokemonDao().clearALl() }

    fun loadPagedCachedPokemon() = localDatabase.pokemonDao().loadPagedData()

    /* ---------------------------------------- Network ----------------------------------------- */

    suspend fun reqPokemon(offset: Int, limit: Int) = api.requestListPokemon(limit, offset)

    fun reqDetailPokemon(name: String): Single<Response<DetailPokemonResponse>> {
        return api.requestDetailPokemon(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}