package asdigital.myberryapp.data.remote.model

import com.google.gson.annotations.SerializedName

//data class BerryDetailResponse(val berryDetail: List<BerryDetailDTO>)

data class BerryDetailDTO(
    val id: Int,
    val name: String,
    @SerializedName("growth_time") val growthTime: Int,
    @SerializedName("max_harvest") val maxHarvest: Int,
    @SerializedName("natural_gift_power") val naturalGiftPower: Int,
    val size: Int,
    val smoothness: Int,
    @SerializedName("soil_dryness") val soilDryness: Int,
    val firmness: Firmness,
    val flavors: List<Flavors>,
    val item: Item,
    @SerializedName("natural_gift_type") val naturalGiftType: NaturalGiftType
)

data class Flavors (
    val potency: Int,
    val flavor: Flavor
)
data class Flavor(
    val name: String,
    val url: String
)

data class Firmness (
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


fun getBerryDetailDTOList(berryDetail: List<BerryDetailDTO>): List<BerryDetailDTO> {
    return berryDetail.map { details ->
        BerryDetailDTO(
            id = details.id,
            name = details.name,
            growthTime = details.growthTime,
            maxHarvest = details.maxHarvest,
            naturalGiftPower = details.naturalGiftPower,
            size = details.size,
            smoothness = details.smoothness,
            soilDryness = details.soilDryness,
            firmness = details.firmness,
            flavors = details.flavors,
            item = details.item,
            naturalGiftType = details.naturalGiftType
        )
    }
}
