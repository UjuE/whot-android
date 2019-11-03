package pink.digitally.games.whot.model

import androidx.lifecycle.ViewModel
import pink.digitally.games.whot.state.GameState
import pink.digitally.games.whot.whotcore.Board
import pink.digitally.games.whot.whotcore.GameStateObserver
import pink.digitally.games.whot.whotcore.Player
import pink.digitally.games.whot.whotcore.error.ErrorMessage

class AndroidGameStateObserver : GameStateObserver, ViewModel() {
    override fun getCurrentGameState(): GameState {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPlayerTurn(player: Player?, board: Board?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onInvalidPlay(player: Player?, board: Board?, errorMessage: ErrorMessage?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGameStarted(players: MutableCollection<Player>?, boards: Board?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGameEnded(player: Player?, players: MutableCollection<Player>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}