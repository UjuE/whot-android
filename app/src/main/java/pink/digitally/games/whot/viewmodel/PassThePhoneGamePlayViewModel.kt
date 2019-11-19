package pink.digitally.games.whot.viewmodel

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pink.digitally.games.whot.R
import pink.digitally.games.whot.model.GameStateDetails
import pink.digitally.games.whot.utils.WhotCardToDrawable
import pink.digitally.games.whot.view.adapter.WhotCardAdapter
import pink.digitally.games.whot.whotcore.events.TakeCardPlayerEvent

class PassThePhoneGamePlayViewModel() : Observable, ViewModel() {
    private val TAG = "PassPhoneViewModel"
    val observableDelegate = BaseObservable()
    val gameStateModel: MutableLiveData<GameStateDetails> by lazy {
        MutableLiveData<GameStateDetails>()
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        observableDelegate.removeOnPropertyChangedCallback(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        observableDelegate.addOnPropertyChangedCallback(callback);
    }

    fun notifyPropertyChanged(fieldId: Int) {
        observableDelegate.notifyPropertyChanged(fieldId)
    }

    fun takeACard() {
        gameStateModel.value!!.currentPlayer!!
            .play(TakeCardPlayerEvent())
        println("After take card the player is ${gameStateModel.value!!.currentPlayer!!.playerName}")
        gameStateModel.postValue(gameStateModel.value)
        observableDelegate.notifyPropertyChanged(R.id.topOfPlayPile)
        observableDelegate.notifyPropertyChanged(R.id.currentPlayerName)
        observableDelegate.notifyPropertyChanged(R.id.playerCards)
    }

    @get:Bindable
    val currentPlayerName: String
        get() {
            Log.i(TAG,"Current Player Name = ${gameStateModel.value!!.currentPlayer!!.playerName}")
            return gameStateModel.value!!.currentPlayer!!.playerName
        }

    //This could be image details
    @Bindable
    fun getTopOfPlayDeck(): Int {
        Log.i(TAG,"Top of Card = ${gameStateModel.value!!.getTopOfPlayDeck()}")
        return WhotCardToDrawable.drawableOf(gameStateModel.value!!.getTopOfPlayDeck())
    }

    fun recyclerViewAdaptor(): WhotCardAdapter {
        return WhotCardAdapter(gameStateModel, observableDelegate,
            gameStateModel.value!!.currentPlayer!!.cards.toList())
    }

}