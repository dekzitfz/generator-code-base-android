package <%= package %>.network

import <%= package %>.model.api.pokemon.PokemonResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {
    @GET("pokemon")
    fun requestListPokemon(
        @Query("limit") limit: Int,
        @Query("offset") page: Int
    ): Single<Response<PokemonResponse>>
}
