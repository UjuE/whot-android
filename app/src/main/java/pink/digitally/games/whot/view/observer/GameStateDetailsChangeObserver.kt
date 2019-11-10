package pink.digitally.games.whot.view.observer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import pink.digitally.games.whot.databinding.ActivityPassPhonePlayGameBinding
import pink.digitally.games.whot.model.GameStateDetails
import pink.digitally.games.whot.state.GameState
import pink.digitally.games.whot.view.adapter.WhotCardAdapter
import pink.digitally.games.whot.viewmodel.PassThePhoneGamePlayViewModel

class GameStateDetailsChangeObserver(
    val binding: ActivityPassPhonePlayGameBinding,
    val passThePhoneGamePlayViewModel: PassThePhoneGamePlayViewModel
) : Observer<GameStateDetails> {

    private val TAG = "GameStateDetailsChange"

    override fun onChanged(t: GameStateDetails) {
        Log.i(TAG, "Game State Changed")

        if (!t.gameState.equals(GameState.ENDED)) {
            val currentPlayer = t.currentPlayer!!
            Log.i(TAG, "The current player is ${currentPlayer.playerName}")

            binding.currentPlayerName.text = currentPlayer.playerName
            binding.topOfPlayPile.text = t.getTopOfPlayDeck().toString()
            binding.playerCards.adapter = WhotCardAdapter(
                MutableLiveData<GameStateDetails>().apply { value = t },
                passThePhoneGamePlayViewModel.observableDelegate,
                currentPlayer.cards
            )
        } else {
            Log.i(TAG, "The game has ended. The winner is ${t.winner!!.playerName}")
        }
    }
}