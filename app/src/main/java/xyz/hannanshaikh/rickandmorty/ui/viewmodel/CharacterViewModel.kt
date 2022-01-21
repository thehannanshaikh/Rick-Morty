package xyz.hannanshaikh.rickandmorty.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import xyz.hannanshaikh.rickandmorty.data.repository.RickMortyRepository
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: RickMortyRepository) : ViewModel() {
}