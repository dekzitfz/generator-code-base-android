package <%= package %>.data

import <%= package %>.data.local.pokemon.LocalPokemon
import <%= package %>.model.api.pokemon.Pokemon
import <%= package %>.model.api.pokemon.PokemonResponse
import <%= package %>.network.APIService
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Singleton
import javax.inject.Inject
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import android.R.attr.category
import androidx.lifecycle.LiveData


@Singleton
class DataManager
@Inject constructor(
    private val api: APIService,
    private val prefs: PreferencesHelper,
    private val localDatabase: AppDatabase){

    /* ---------------------------------------- SQLite ------------------------------------------ */

    fun saveAllPokemonToLocal(listPokemon: List<LocalPokemon>): Completable {
        return Completable.fromAction {
            localDatabase.PokemonDao().saveAllPokemon(listPokemon)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun saveAllPokemonToLocalFromStart(listPokemon: List<LocalPokemon>): Completable {
        return Completable.fromAction {
            localDatabase.PokemonDao().renewAllData(listPokemon)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun loadAllPokemonFromLocal(): LiveData<List<LocalPokemon>> {
        return localDatabase.PokemonDao().loadAllPokemon()
    }

    /* ---------------------------------------- Network ----------------------------------------- */

    fun reqPokemon(page: Int, limit: Int): Single<Response<PokemonResponse>> {
        return api.requestListPokemon(limit, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}