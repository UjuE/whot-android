package pink.digitally.games.whot.utils

import org.hamcrest.core.Is
import org.junit.Assert.assertThat
import org.junit.Test
import pink.digitally.games.whot.R
import pink.digitally.games.whot.whotcore.WhotCard
import pink.digitally.games.whot.whotcore.WhotNumber
import pink.digitally.games.whot.whotcore.WhotShape

class WhotCardToDrawableTest {

    @Test
    fun unknown_whot_card() {
        val drawableResource =
            WhotCardToDrawable.drawableOf(WhotCard.whotCard(WhotNumber.THIRTEEN, WhotShape.STAR))

        assertThat(drawableResource, Is.`is`(R.mipmap.whotback))
    }

    @Test
    fun twenty_whot(){
        val drawableResource =
            WhotCardToDrawable.drawableOf(WhotCard.whotCard(WhotNumber.TWENTY, WhotShape.WHOT))

        assertThat(drawableResource, Is.`is`(R.mipmap.twenty_whot))
    }
}