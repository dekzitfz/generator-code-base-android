package <%= package %>.model.api.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonResponse(

	@SerializedName("count")
	val count: Int? = null,

	@SerializedName("results")
	val results: List<Pokemon> = mutableListOf(),

	@SerializedName("next")
	val next: String? = null
)