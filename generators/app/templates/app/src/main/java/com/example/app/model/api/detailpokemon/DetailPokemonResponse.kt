package <%= package %>.model.api.detailpokemon

import com.google.gson.annotations.SerializedName

data class DetailPokemonResponse(

	@field:SerializedName("abilities")
	val abilities: List<AbilitiesItem> = mutableListOf(),

	@field:SerializedName("types")
	val types: List<TypesItem> = mutableListOf(),

	@field:SerializedName("stats")
	val stats: List<StatsItem> = mutableListOf(),

	@field:SerializedName("base_experience")
	val baseExperience: Int? = null,

	@field:SerializedName("moves")
	val moves: List<MovesItem> = mutableListOf(),

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("is_default")
	val isDefault: Boolean? = null,

	@field:SerializedName("sprites")
	val sprites: Sprites? = null,

	@field:SerializedName("height")
	val height: Int? = null,

	@field:SerializedName("order")
	val order: Int? = null
)

data class Move(

	@field:SerializedName("name")
	val name: String = "",

	@field:SerializedName("url")
	val url: String? = null
)

data class OfficialArtwork(

	@field:SerializedName("front_default")
	val frontDefault: String? = null
)

data class Other(

	@field:SerializedName("official-artwork")
	val officialArtwork: OfficialArtwork? = null
)

data class Stat(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class TypesItem(

	@field:SerializedName("slot")
	val slot: Int? = null,

	@field:SerializedName("type")
	val type: Type? = null
)

data class Sprites(

	@field:SerializedName("other")
	val other: Other? = null
)

data class AbilitiesItem(

	@field:SerializedName("is_hidden")
	val isHidden: Boolean? = null,

	@field:SerializedName("ability")
	val ability: Ability? = null,

	@field:SerializedName("slot")
	val slot: Int? = null
)

data class Type(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class StatsItem(

	@field:SerializedName("stat")
	val stat: Stat? = null,

	@field:SerializedName("base_stat")
	val baseStat: Int = 0,

	@field:SerializedName("effort")
	val effort: Int? = null
)

data class MovesItem(

	@field:SerializedName("version_group_details")
	val versionGroupDetails: List<VersionGroupDetailsItem> = mutableListOf(),

	@field:SerializedName("move")
	val move: Move = Move()
)

data class Ability(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class VersionGroup(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class VersionGroupDetailsItem(

	@field:SerializedName("level_learned_at")
	val levelLearnedAt: Int? = null,

	@field:SerializedName("version_group")
	val versionGroup: VersionGroup? = null,

	@field:SerializedName("move_learn_method")
	val moveLearnMethod: MoveLearnMethod? = null
)

data class MoveLearnMethod(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
