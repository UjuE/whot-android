package pink.digitally.games.whot.model

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pink.digitally.games.whot.R
import pink.digitally.games.whot.state.GameState
import pink.digitally.games.whot.state.GameState.STARTED
import pink.digitally.games.whot.whotcore.*
import pink.digitally.games.whot.whotcore.WhotCard.whotCard
import pink.digitally.games.whot.whotcore.error.ErrorMessage
import pink.digitally.games.whot.whotcore.events.PlayCardPlayerEvent
import pink.digitally.games.whot.whotcore.events.TakeCardPlayerEvent

class AndroidGameStateObserver(val context: Activity) : GameStateObserver, BaseObservable() {
    private var gameState: GameState = GameState.NOT_STARTED
    private var playersMap: Map<String, Player>? = null

    @Bindable("currentPlayerName")
    var currentPlayerName: LiveData<String> = MutableLiveData<String>().apply { setValue(null) }

    @Bindable("topOfPlayPile")
    var topOfPlayPile: String = ""


    fun takeACard() {
        if(STARTED == gameState){
            Log.i("TAG", "Current Player ${currentPlayerName.value} takes a card")
            playersMap!!.get(currentPlayerName.value)!!
                .play(TakeCardPlayerEvent())
        }
    }

    override fun getCurrentGameState(): GameState {
        return gameState;
    }

    override fun onPlayerTurn(player: Player, board: Board) {
        Log.i("TAG", "It should be ${player.playerName}'s turn")

        setCurrentPlayerData(player)
        setTopOfPlayPile(board.topOfPlayPile)
        println("The thing actually viewed is: ${currentPlayerName.value}")

        //How do I add Images. Should I create a link to the context and manipulate what I see?
        //Do I want Images for each card?
        val viewFlipper = context.findViewById<LinearLayout>(R.id.playerCards)
        viewFlipper.removeAllViewsInLayout()
        for (card in player.cards) {
            try {

                val textView = textView(card, player)
                viewFlipper.addView(textView)

            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    override fun onInvalidPlay(player: Player, board: Board, errorMessage: ErrorMessage) {
        Log.w("WRONG_PLAY", "Maybe have a pop up with message ${errorMessage.getMessage()}")
        onPlayerTurn(player, board)
    }

    override fun onGameStarted(players: MutableCollection<Player>, boards: Board) {
        gameState = STARTED
        playersMap = players.map { it.playerName to it }.toMap()
        //Splash Screen summarising the players maybe or a popup

    }

    override fun onGameEnded(player: Player?, players: MutableCollection<Player>?) {
        gameState = GameState.ENDED
        println("${player!!.playerName} WINs")
        //Send to another activity with the winner name
    }

    private fun textView(card: WhotCardWithNumberAndShape, player: Player): View {
        return TextView(context).apply {
            setId(View.generateViewId())
            append(cardDetails(card))
            setOnClickListener { v ->
                run {
                    val tv: TextView = v as TextView
                    val cardDetails = tv.getText()
                    val cardToPlay = cardFrom(cardDetails)
                    println("Played $cardDetails")
                    player.play(PlayCardPlayerEvent(cardToPlay))
                }
            }
        }
    }

    private fun setCurrentPlayerData(player: Player) {
        currentPlayerName = MutableLiveData<String>().apply { setValue(player.playerName) }
        val suffix = context.getString(R.string.player_turn)
        context.findViewById<TextView>(R.id.currentPlayerName).text =
            "${player.playerName} ${suffix}"
    }

    private fun setTopOfPlayPile(card: WhotCardWithNumberAndShape) {
        topOfPlayPile = cardDetails(card)

        context.findViewById<TextView>(R.id.topOfPlayPile).text = cardDetails(card)
    }

    private fun cardDetails(card: WhotCardWithNumberAndShape): String {
        return String.format("%s,%s", card.number, card.shape)
    }

    private fun cardFrom(cardDetails: CharSequence): WhotCardWithNumberAndShape {
        val cardDetailsParts = cardDetails.split(",")
        return (whotCard(
            WhotNumber.valueOf(cardDetailsParts[0]),
            WhotShape.valueOf(cardDetailsParts[1])
        ))
    }

}