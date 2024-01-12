package ihor.kalaur.pokedex.repository

import dagger.hilt.android.scopes.ActivityScoped
import ihor.kalaur.pokedex.data.remote.PokeApi
import ihor.kalaur.pokedex.data.remote.responses.PokemonDetails
import ihor.kalaur.pokedex.data.remote.responses.PokemonList
import ihor.kalaur.pokedex.util.Resource
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
){

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonDetail(pokemonName: String): Resource<PokemonDetails> {
        val response = try {
            api.getPokemonDetails(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }
}