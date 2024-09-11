package <%= package %>.feature.listpokemon

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import <%= package %>.data.DataManager
import <%= package %>.data.local.pokemon.LocalPokemon
import <%= package %>.util.convertToLocalModel
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/**
 * used to load fetch paging data from network and insert to local db
 */
@OptIn(ExperimentalPagingApi::class)
class ListPokemonRemoteMediator(
    private val dataManager: DataManager
): RemoteMediator<Int, LocalPokemon>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocalPokemon>
    ): MediatorResult {
        return try {
            val offset: Int = when (loadType) {
                LoadType.REFRESH -> {
                    Timber.d("LoadType is REFRESH")
                    0
                }
                LoadType.PREPEND -> {
                    Timber.d("LoadType is PREPEND")
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    Timber.d("LoadType is APPEND")
                    if (state.lastItemOrNull() == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    dataManager.getCachedPokemonCount()
                }
            }

            Timber.d("reqPokemon with offset $offset")
            val response = dataManager.reqPokemon(
                offset = offset,
                limit = state.config.pageSize //should be 10
            )

            if(loadType == LoadType.REFRESH){
                dataManager.clearCachedPokemon()
            }

            val responseBody = response.body()
            if(responseBody != null){
                val localPokemons = mutableListOf<LocalPokemon>()
                responseBody.results.forEach { pokemon ->
                    localPokemons.add(pokemon.convertToLocalModel())
                }
                dataManager.insertAllPokemon(localPokemons)
                MediatorResult.Success(endOfPaginationReached = responseBody.next == null)
            }else{
                Timber.w("response body is empty")
                MediatorResult.Error(Throwable(message = "response body is empty"))
            }
        } catch (e: IOException) {
            Timber.w(e)
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            Timber.w(e)
            MediatorResult.Error(e)
        }
    }
}