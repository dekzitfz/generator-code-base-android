package <%= package %>

import <%= package %>.model.api.pokemon.Pokemon
import org.junit.Test
import org.junit.Assert.assertEquals

class PokemonModelTest {

    @Test
    fun pokemonIdValidation() {
        val pokemonModel = Pokemon(
            name = "bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/999/"
        )
        assertEquals(
            999,
            pokemonModel.getId()
        )
    }

}