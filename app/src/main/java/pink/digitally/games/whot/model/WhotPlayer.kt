package pink.digitally.games.whot.model

import pink.digitally.games.whot.whotcore.GameMediator
import pink.digitally.games.whot.whotcore.Player
import pink.digitally.games.whot.whotcore.WhotCardWithNumberAndShape
import pink.digitally.games.whot.whotcore.events.PlayerEvent

class WhotPlayer(val name: String) : Player {
    private var gameMediator: GameMediator? = null
    private val cards = ArrayList<WhotCardWithNumberAndShape?>()

    override fun getCards(): MutableList<WhotCardWithNumberAndShape?> {
        return cards;
    }

    override fun getMediator(): GameMediator? {
        return gameMediator;
    }

    override fun registerMediator(gameMediator: GameMediator?) {
        this.gameMediator = gameMediator;
    }

    override fun addCard(whotCard: WhotCardWithNumberAndShape?) {
        cards.add(whotCard)
    }

    override fun getPlayerName(): String {
        return name;
    }

    override fun play(playerEvent: PlayerEvent){
        println("${name} is performing event ${playerEvent.playerEventType}")
        this.mediator!!.play(this, playerEvent)
    }

}