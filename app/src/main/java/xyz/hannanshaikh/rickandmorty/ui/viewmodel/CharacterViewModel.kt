package xyz.hannanshaikh.rickandmorty.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import xyz.hannanshaikh.rickandmorty.data.repository.RickMortyRepository
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(repository: RickMortyRepository) : ViewModel() {

    val characters = repository.characters.cachedIn(viewModelScope)

}