package pink.digitally.games.whot.model

import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import pink.digitally.games.whot.R
import java.util.Arrays.asList


class PlayerRegistrationViewModel : BaseObservable() {

    @get:BindingAdapter("playerOne")
    var playerOne: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(R.id.player1)
        }


    @get:BindingAdapter("playerTwo")
    var playerTwo: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(R.id.player2)
        }

    fun clicked() {
        if (allNotEmpty(playerOne, playerTwo)){
            //Maybe stop the transition
        } else {
            //Transition to another Activity
        }
    }

    private fun allNotEmpty(vararg manyStrings: String?): Boolean {
        println("Did I get here")
        return asList(*manyStrings)
            .all { !it.isNullOrBlank() }
    }

}