package xyz.hannanshaikh.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import xyz.hannanshaikh.rickandmorty.api.ApiService
import xyz.hannanshaikh.rickandmorty.data.datasource.CharacterPagingDataSource
import javax.inject.Inject

class RickMortyRepository @Inject constructor(private val apiService: ApiService) {

    val characters = Pager(
        config = PagingConfig(pageSize = 6, initialLoadSize = 15, enablePlaceholders = false), pagingSourceFactory = {
            CharacterPagingDataSource(apiService)
        }
    ).flow
}