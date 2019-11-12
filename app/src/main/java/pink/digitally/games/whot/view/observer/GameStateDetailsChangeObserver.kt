package pink.digitally.games.whot.view.observer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import pink.digitally.games.whot.databinding.ActivityPassPhonePlayGameBinding
import pink.digitally.games.whot.model.GameStateDetails
import pink.digitally.games.whot.state.GameState
import pink.digitally.games.whot.utils.WhotCardToDrawable
import pink.digitally.games.whot.view.adapter.WhotCardAdapter
import pink.digitally.games.whot.viewmodel.PassThePhoneGamePlayViewModel

class GameStateDetailsChangeObserver(
    val binding: ActivityPassPhonePlayGameBinding,
    val passThePhoneGamePlayViewModel: PassThePhoneGamePlayViewModel
) : Observer<GameStateDetails> {

    private val TAG = "GameStateDetailsChange"

    override fun onChanged(gameStateDetails: GameStateDetails) {
        Log.i(TAG, "Game State Changed")

        if (!gameStateDetails.gameState.equals(GameState.ENDED)) {
            val currentPlayer = gameStateDetails.currentPlayer!!
            val whotCard = gameStateDetails.getTopOfPlayDeck()
            Log.i(TAG, "The current player is ${currentPlayer.playerName}")

            binding.currentPlayerName.text = currentPlayer.playerName
            binding.topOfPlayPile.contentDescription = "${whotCard.number.numericValue} ${whotCard.shape}"
            binding.topOfPlayPile.setImageResource(WhotCardToDrawable.drawableOf(whotCard))
            binding.playerCards.adapter = WhotCardAdapter(
                MutableLiveData<GameStateDetails>().apply { value = gameStateDetails },
                passThePhoneGamePlayViewModel.observableDelegate,
                currentPlayer.cards
            )
        } else {
            Log.i(TAG, "The game has ended. The winner is ${gameStateDetails.winner!!.playerName}")
        }
    }
}