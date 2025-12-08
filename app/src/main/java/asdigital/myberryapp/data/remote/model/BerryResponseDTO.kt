package asdigital.myberryapp.data.remote.model

data class BerryResponseDTO( val results: List<BerryDTO>)


fun getBerryDTOList(results: List<BerryDTO>): List<BerryDTO> {
    return results.map { berry ->
        BerryDTO(
            name = berry.name
        )
    }

}
