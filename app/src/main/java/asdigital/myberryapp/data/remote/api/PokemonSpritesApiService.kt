package asdigital.myberryapp.data.remote.api

fun getBerrySpriteUrl(name: String): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/$name-berry.png"
}
