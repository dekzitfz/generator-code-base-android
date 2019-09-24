package <%= package %>.model.api.pokemon

import com.google.gson.annotations.SerializedName

data class Pokemon(

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("url")
	val url: String? = null
){
	fun getImage(): String {
		if(url!=null){
			val pokemonId = url.substringAfter("pokemon/").substringBefore("/")
			return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"
		}
		return ""
	}
}