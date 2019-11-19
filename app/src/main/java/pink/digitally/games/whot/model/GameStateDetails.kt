package pink.digitally.games.whot.model

import pink.digitally.games.whot.state.GameState
import pink.digitally.games.whot.whotcore.Board
import pink.digitally.games.whot.whotcore.Player
import pink.digitally.games.whot.whotcore.card.WhotCardWithNumberAndShape

interface GameStateDetails {
    var winner: Player?
    var gameState: GameState
    var currentPlayer: Player?
    var board: Board?

    fun getTopOfPlayDeck(): WhotCardWithNumberAndShape
    fun setAllPlayers(players: MutableCollection<Player>?)
}
