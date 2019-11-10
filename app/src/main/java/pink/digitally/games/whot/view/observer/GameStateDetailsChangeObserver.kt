package pink.digitally.games.whot.view.observer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import pink.digitally.games.whot.databinding.ActivityPlayGameBinding
import pink.digitally.games.whot.model.GameStateDetails
import pink.digitally.games.whot.state.GameState
import pink.digitally.games.whot.view.adapter.WhotCardAdapter
import pink.digitally.games.whot.viewmodel.PassThePhoneGamePlayViewModel

class GameStateDetailsChangeObserver(val binding: ActivityPlayGameBinding,
                                     val passThePhoneGamePlayViewModel : PassThePhoneGamePlayViewModel
): Observer<GameStateDetails> {
    override fun onChanged(t: GameStateDetails) {
        Log.i("passPhonePlayActivity", "Game State Changed")

        if (!t.gameState.equals(GameState.ENDED)) {
            val currentPlayer = t.currentPlayer!!
            println("The current player should be ${currentPlayer.playerName}")

            binding.currentPlayerName.text = currentPlayer.playerName
            binding.topOfPlayPile.text = t.getTopOfPlayDeck().toString()
            binding.playerCards.adapter = WhotCardAdapter(
                MutableLiveData<GameStateDetails>().apply { value = t },
                passThePhoneGamePlayViewModel.observableDelegate,
                currentPlayer.cards
            )
        } else {
            println("The game should have ended")
        }
    }
}