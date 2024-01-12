package ihor.kalaur.pokedex.data.remote.responses

data class PokemonDetails(
    val abilities: List<Ability>,
    val baseExperience: Int,
    val forms: List<Form>,
    val gameIndices: List<GameIndice>,
    val height: Int,
    val heldItems: List<Any>,
    val id: Int,
    val isDefault: Boolean,
    val locationAreaEncounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val pastAbilities: List<Any>,
    val pastTypes: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)