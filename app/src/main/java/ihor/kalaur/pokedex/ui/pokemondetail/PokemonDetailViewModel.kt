package ihor.kalaur.pokedex.ui.pokemondetail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ihor.kalaur.pokedex.data.remote.responses.PokemonDetails
import ihor.kalaur.pokedex.repository.PokemonRepository
import ihor.kalaur.pokedex.util.Resource
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {
    suspend fun getPokemonInfo(pokemonName: String): Resource<PokemonDetails> {
        return repository.getPokemonDetail(pokemonName)
    }
}
