package pink.digitally.games.whot.utils

import org.hamcrest.core.Is
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test
import pink.digitally.games.whot.R
import pink.digitally.games.whot.whotcore.card.WhotCard.whotCard
import pink.digitally.games.whot.whotcore.card.WhotCardDeck
import pink.digitally.games.whot.whotcore.card.WhotNumber
import pink.digitally.games.whot.whotcore.card.WhotShape

class WhotCardToDrawableTest {

    @Test
    fun unknown_whot_card() {
        val drawableResource =
            WhotCardToDrawable.drawableOf(whotCard(WhotNumber.THIRTEEN, WhotShape.STAR))

        assertThat(drawableResource, Is.`is`(R.mipmap.whotback))
    }

    @Test
    fun twenty_whot() {
        val drawableResource =
            WhotCardToDrawable.drawableOf(whotCard(WhotNumber.TWENTY, WhotShape.WHOT))

        assertThat(drawableResource, Is.`is`(R.mipmap.twenty_whot))
    }

    @Test
    fun all_cards_in_deck_has_image() {
        val cardsWithoutCustomImage = WhotCardDeck.getCards()
            .map { card ->
                WhotCardToDrawable.drawableOf(card)
            }.filter {
                the_drawable -> R.mipmap.whotback == the_drawable
            }
        assertTrue(cardsWithoutCustomImage.isEmpty())
    }
}