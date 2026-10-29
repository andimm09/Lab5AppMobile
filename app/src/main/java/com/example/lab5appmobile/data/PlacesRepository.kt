package com.example.lab5appmobile.data

import androidx.annotation.DrawableRes
import com.example.lab5appmobile.R

enum class Category(val label: String) {
    SPORT("Sport"),
    MUZEE("Muzee"),
    RESTAURANTE("Restaurante"),
}

data class Place(
    val id: Int,
    val name: String,
    val category: Category,
    val town: String,
    val description: String,
    /** Fotografie opțională (Wikimedia Commons, vezi README pentru autori și licențe). */
    @DrawableRes val imageRes: Int? = null,
)

/** Singura sursă de date a aplicației (date scrise direct în cod). */
object PlacesRepository {

    val places: List<Place> = listOf(
        Place(
            id = 1,
            name = "Muzeul Satului Maramureșean",
            category = Category.MUZEE,
            town = "Sighetu Marmației",
            description = "Muzeu în aer liber pe Dealul Dobăieș, cu gospodării tradiționale, " +
                "biserici de lemn și ateliere aduse din satele Maramureșului istoric.",
            imageRes = R.drawable.img_muzeul_satului,
        ),
        Place(
            id = 2,
            name = "Memorialul Victimelor Comunismului și al Rezistenței",
            category = Category.MUZEE,
            town = "Sighetu Marmației",
            description = "Amenajat în fosta închisoare politică din Sighet, unde au fost " +
                "întemnițați membri ai elitei interbelice. Muzeu și centru de cercetare.",
            imageRes = R.drawable.img_memorial_sighet,
        ),
        Place(
            id = 3,
            name = "Muzeul Județean de Istorie și Arheologie Maramureș",
            category = Category.MUZEE,
            town = "Baia Mare",
            description = "Colecții de arheologie, istorie locală și istoria mineritului " +
                "din zona Băii Mari.",
        ),
        Place(
            id = 4,
            name = "Muzeul de Mineralogie „Victor Gorduza”",
            category = Category.MUZEE,
            town = "Baia Mare",
            description = "Una dintre cele mai bogate colecții de minerale din țară, " +
                "cu flori de mină provenite din exploatările din regiune.",
            imageRes = R.drawable.img_mineralogie,
        ),
        Place(
            id = 5,
            name = "Domeniul schiabil Cavnic",
            category = Category.SPORT,
            town = "Cavnic",
            description = "Pârtii de schi de diferite dificultăți, deservite de teleschiuri " +
                "și telescaune, la aproximativ 30 km de Baia Mare.",
            imageRes = R.drawable.img_cavnic,
        ),
        Place(
            id = 6,
            name = "Stațiunea Borșa (schi, drumeții)",
            category = Category.SPORT,
            town = "Borșa",
            description = "Stațiune montană la poalele Munților Rodnei: schi iarna, " +
                "drumeții și cascada Cailor vara.",
            imageRes = R.drawable.img_borsa,
        ),
        Place(
            id = 7,
            name = "Parcul Național Munții Rodnei",
            category = Category.SPORT,
            town = "Borșa / Moisei",
            description = "Trasee montane marcate spre Vârful Pietrosu Rodnei și lacurile " +
                "glaciare din zonă.",
            imageRes = R.drawable.img_rodnei,
        ),
        Place(
            id = 8,
            name = "Restaurant Millennium",
            category = Category.RESTAURANTE,
            town = "Baia Mare",
            description = "Restaurant în centrul vechi, în Piața Libertății, cu bucătărie " +
                "românească și internațională.",
            imageRes = R.drawable.img_baia_mare_centru,
        ),
        Place(
            id = 9,
            name = "Casa Iurca de Călinești",
            category = Category.RESTAURANTE,
            town = "Sighetu Marmației",
            description = "Restaurant cu specific tradițional maramureșean, într-un decor " +
                "de casă țărănească.",
            imageRes = R.drawable.img_sighet_centru,
        ),
        Place(
            id = 10,
            name = "Restaurant tradițional din Baia Sprie",
            category = Category.RESTAURANTE,
            town = "Baia Sprie",
            description = "Mâncăruri locale: balmoș, tocăniță cu mămăligă și plăcinte " +
                "maramureșene.",
            imageRes = R.drawable.img_baia_sprie,
        ),
    )

    fun getPlace(id: Int): Place? = places.find { it.id == id }
}
