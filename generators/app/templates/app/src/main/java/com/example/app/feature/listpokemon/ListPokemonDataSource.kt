package <%= package %>.feature.listpokemon

import androidx.paging.PagingSource
import androidx.paging.PagingState
import <%= package %>.data.DataManager
import <%= package %>.model.api.pokemon.Pokemon

class ListPokemonDataSource(private val dataManager: DataManager) :
    PagingSource<Int, Pokemon>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val pageNumber = params.key ?: 0
            val response = dataManager.reqPokemon(pageNumber, params.loadSize)
            val pagedResponse = response.body()
            val prevKey = if (pageNumber == 0) null else pageNumber - params.loadSize

            LoadResult.Page(
                data = pagedResponse?.results!!,
                prevKey = prevKey,
                nextKey = if (pagedResponse.results.isNotEmpty()) pageNumber.plus(params.loadSize) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int = 1
}