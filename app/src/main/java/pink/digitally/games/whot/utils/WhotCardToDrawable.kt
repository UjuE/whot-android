package pink.digitally.games.whot.utils

import pink.digitally.games.whot.R
import pink.digitally.games.whot.whotcore.WhotCard
import pink.digitally.games.whot.whotcore.WhotCardWithNumberAndShape
import pink.digitally.games.whot.whotcore.WhotNumber
import pink.digitally.games.whot.whotcore.WhotShape

object WhotCardToDrawable {

    private val mappings = mapOf<WhotCardWithNumberAndShape, Int>(
        WhotCard.whotCard(WhotNumber.TWENTY, WhotShape.WHOT) to R.mipmap.twenty_whot,

        WhotCard.whotCard(WhotNumber.ONE, WhotShape.STAR) to R.mipmap.one_star,
        WhotCard.whotCard(WhotNumber.TWO, WhotShape.STAR) to R.mipmap.two_star,
        WhotCard.whotCard(WhotNumber.THREE, WhotShape.STAR) to R.mipmap.three_star,
        WhotCard.whotCard(WhotNumber.FOUR, WhotShape.STAR) to R.mipmap.four_star,
        WhotCard.whotCard(WhotNumber.FIVE, WhotShape.STAR) to R.mipmap.five_star,
        WhotCard.whotCard(WhotNumber.SEVEN, WhotShape.STAR) to R.mipmap.seven_star,
        WhotCard.whotCard(WhotNumber.EIGHT, WhotShape.STAR) to R.mipmap.eight_star,
        
        WhotCard.whotCard(WhotNumber.ONE, WhotShape.SQUARE) to R.mipmap.one_square,
        WhotCard.whotCard(WhotNumber.TWO, WhotShape.SQUARE) to R.mipmap.two_square,
        WhotCard.whotCard(WhotNumber.THREE, WhotShape.SQUARE) to R.mipmap.three_square,
        WhotCard.whotCard(WhotNumber.FIVE, WhotShape.SQUARE) to R.mipmap.five_square,
        WhotCard.whotCard(WhotNumber.SEVEN, WhotShape.SQUARE) to R.mipmap.seven_square,
        WhotCard.whotCard(WhotNumber.TEN, WhotShape.SQUARE) to R.mipmap.ten_square,
        WhotCard.whotCard(WhotNumber.ELEVEN, WhotShape.SQUARE) to R.mipmap.eleven_square,
        WhotCard.whotCard(WhotNumber.THIRTEEN, WhotShape.SQUARE) to R.mipmap.thirteen_square,
        WhotCard.whotCard(WhotNumber.FOURTEEN, WhotShape.SQUARE) to R.mipmap.fourteen_square

    )

    fun drawableOf(whotCardWithNumberAndShape: WhotCardWithNumberAndShape): Int {
        val drawableResource = mappings.get(whotCardWithNumberAndShape)
        return drawableResource ?: R.mipmap.whotback
    }
}