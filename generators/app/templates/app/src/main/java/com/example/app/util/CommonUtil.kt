package <%= package %>.util

import <%= package %>.data.local.pokemon.LocalPokemon
import <%= package %>.model.api.pokemon.Pokemon

fun Pokemon.convertToLocalModel(): LocalPokemon {
    return LocalPokemon(
        id = this.getId(),
        pokemonName = this.name,
        pokemonImageUrl = this.getImage()
    )
}