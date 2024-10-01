package asdigital.myberryapp

data class Berry(
    val name: String,
)

data class BerryResponse(
    val results: List<Berry>
    //val count: Int,
    //val next: String?,
    // val previous: String?,
)

/*data class Berry(
    val id: Int,
    val name: String,
    val growthtime: Int,
    val maxharvest:Int,
    val naturalgiftpower: Int,
    val size: Int,
    val smoothness:Int,
    val soildryness: Int,
    val firmness: Firmness,
    val flavors: Flavors,
    val item: Item,
    val naturalgifttype: NaturalGiftType
)

data class PokeBerryResponse( var berry: List<Berry>)


data class Firmness (
   val name: String,
   val url: String
)

data class Flavors (
    val potency: Int,
    val flavor: Flavor
)
data class Flavor(
    val name: String,
    val url: String
)

data class Item (
    val name:String,
    val url: String
)

data class NaturalGiftType(
    val name: String,
    val url: String
)

*/