package <%= package %>.data

import <%= package %>.model.api.pokemon.PokemonResponse
import <%= package %>.network.APIService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Singleton
import javax.inject.Inject
import io.reactivex.schedulers.Schedulers
import retrofit2.Response


@Singleton
class DataManager
@Inject constructor(private val api: APIService, private val prefs: PreferencesHelper){

    fun reqPokemon(page: Int, limit: Int): Single<Response<PokemonResponse>> {
        return api.requestListPokemon(limit, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}