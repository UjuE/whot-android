package pink.digitally.games.whot.view.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import pink.digitally.games.whot.R
import pink.digitally.games.whot.model.GameStateDetails
import pink.digitally.games.whot.whotcore.WhotCardWithNumberAndShape
import pink.digitally.games.whot.whotcore.events.PlayCardPlayerEvent

class WhotCardAdapter(
    private val gameStateDetails: MutableLiveData<GameStateDetails>,
    private val observable: BaseObservable,
    private val dataSet: List<WhotCardWithNumberAndShape>
) : RecyclerView.Adapter<WhotCardAdapter.WhotCardAdapterViewHolder>() {

    private val TAG = "WhotCardAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhotCardAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val imageView = layoutInflater
            .inflate(R.layout.player_card_view, parent,
                false) as TextView

        return WhotCardAdapterViewHolder(imageView)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: WhotCardAdapterViewHolder, position: Int) {
        val whotCard = dataSet.get(position);
        val currentPlayer = gameStateDetails.value!!.currentPlayer
        val formattedCardDetails = "${whotCard.number.numericValue} ${whotCard.shape}"

        holder.imageView.text = formattedCardDetails


        holder.imageView.setOnClickListener { _ ->
            run {
                Log.i(TAG, "${currentPlayer!!.playerName} Played ${formattedCardDetails}")
                currentPlayer.play(PlayCardPlayerEvent(whotCard))

                gameStateDetails.postValue(gameStateDetails.value)
                observable.notifyPropertyChanged(R.id.topOfPlayPile)
                observable.notifyPropertyChanged(R.id.currentPlayerName)
                observable.notifyPropertyChanged(R.id.playerCards)
            }
        }
    }

    private fun drawableFrom(whotCard: WhotCardWithNumberAndShape): Drawable? {
        println("whotCardAdapter = [${whotCard}]")
        return null
    }

    class WhotCardAdapterViewHolder(val imageView: TextView) : RecyclerView.ViewHolder(imageView)
}