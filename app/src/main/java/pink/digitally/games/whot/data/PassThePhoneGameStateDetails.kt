package pink.digitally.games.whot.data

import pink.digitally.games.whot.model.GameStateDetails
import pink.digitally.games.whot.state.GameState
import pink.digitally.games.whot.whotcore.Board
import pink.digitally.games.whot.whotcore.Player
import pink.digitally.games.whot.whotcore.card.WhotCardWithNumberAndShape

class PassThePhoneGameStateDetails : GameStateDetails {
    private var allPlayers: MutableCollection<Player>? = null

    override var gameState: GameState = GameState.NOT_STARTED
    override var currentPlayer: Player? = null
    override var winner: Player? = null
    override var board: Board? = null

    override fun getTopOfPlayDeck(): WhotCardWithNumberAndShape {
        return board!!.topOfPlayPile
    }

    override fun setAllPlayers(players: MutableCollection<Player>?) {
        allPlayers = players;
    }
}