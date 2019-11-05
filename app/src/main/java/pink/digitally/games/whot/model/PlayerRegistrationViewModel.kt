package pink.digitally.games.whot.model

import android.content.Intent
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import pink.digitally.games.whot.R
import pink.digitally.games.whot.view.PlayGameActivity
import java.util.Arrays.asList


class PlayerRegistrationViewModel : BaseObservable() {

    @Bindable("playerOne")
    var playerOne: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(R.id.player1)
        }


    @Bindable("playerTwo")
    var playerTwo: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(R.id.player2)
        }

    fun clicked(v: View) {
        if (allNotEmpty(playerOne, playerTwo)){
            val context = v.getContext()
            val playGameIntent = Intent(context, PlayGameActivity::class.java)
            playGameIntent.putExtra("playerOneName", playerOne)
            playGameIntent.putExtra("playerTwoName", playerTwo)
            context.startActivity(playGameIntent)
        } else {
            //Transition to another Activity. Maybe a popup activity
            println("Set at least 2 players")
        }
    }

    private fun allNotEmpty(vararg manyStrings: String?): Boolean {
        println("Did I get here")
        return asList(*manyStrings)
            .all { !it.isNullOrBlank() }
    }

}