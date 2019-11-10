package pink.digitally.games.whot.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pink.digitally.games.whot.model.GameStateDetails
import pink.digitally.games.whot.state.GameState
import pink.digitally.games.whot.whotcore.Board
import pink.digitally.games.whot.whotcore.GameStateObserver
import pink.digitally.games.whot.whotcore.Player
import pink.digitally.games.whot.whotcore.error.ErrorMessage

class AndroidGameStateObserver(val gameStateDetails: MutableLiveData<GameStateDetails>) :
    GameStateObserver {
    private val TAG = "GameStateObserver"
    override fun getCurrentGameState(): GameState {
        return gameStateDetails.value!!.gameState
    }

    override fun onPlayerTurn(p0: Player?, p1: Board?) {
        Log.i(TAG, "Player Turn ${p0!!.playerName} and Board ${p1!!.topOfPlayPile}")

        gameStateDetails.value!!.currentPlayer = p0
        gameStateDetails.value!!.board = p1
        gameStateDetails.postValue(gameStateDetails.value)
    }

    override fun onInvalidPlay(p0: Player?, p1: Board?, p2: ErrorMessage?) {
        Log.i(TAG, "Invalid Play by Player ${p0!!.playerName}: ${p2!!.message}")
        onPlayerTurn(p0, p1)
    }

    override fun onGameStarted(p0: MutableCollection<Player>, p1: Board) {
        Log.i(TAG, "Game started with ${p0.size} players")
        gameStateDetails.value!!.gameState = GameState.STARTED
        gameStateDetails.value!!.setAllPlayers(p0);
        gameStateDetails.value!!.board = p1
        gameStateDetails.postValue(gameStateDetails.value)
    }

    override fun onGameEnded(player: Player, otherPlayers: MutableCollection<Player>) {
        Log.i(TAG, "Game ended. ${player.playerName} won.")
        gameStateDetails.value!!.gameState = GameState.ENDED
        gameStateDetails.value!!.winner = player
        gameStateDetails.postValue(gameStateDetails.value)
    }

}