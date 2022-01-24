package xyz.hannanshaikh.rickandmorty.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import xyz.hannanshaikh.rickandmorty.api.ApiService
import xyz.hannanshaikh.rickandmorty.data.model.CharacterItem
import java.io.IOException

class CharacterPagingDataSource(private val apiService: ApiService) : PagingSource<Int,CharacterItem>(){
    override fun getRefreshKey(state: PagingState<Int, CharacterItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterItem> {
        val page: Int = params.key ?: 1
        return try {
            val response = apiService.getCharacters(page)
            val pagedResponse = response.body()
            val data = pagedResponse?.results
            val nextKey = if(pagedResponse?.info?.next != null) page + 1 else null
            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = if (page == 1) null else page-1,
                nextKey = nextKey
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }

}