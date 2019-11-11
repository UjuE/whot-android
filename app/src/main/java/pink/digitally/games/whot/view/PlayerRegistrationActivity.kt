package pink.digitally.games.whot.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import pink.digitally.games.whot.R
import pink.digitally.games.whot.databinding.ActivityPlayerRegistrationBinding
import pink.digitally.games.whot.viewmodel.PlayerRegistrationViewModel
import java.util.*


class PlayerRegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityPlayerRegistrationBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_player_registration)

        dataBinding.lifecycleOwner = this
        dataBinding.playerRegistration =
            PlayerRegistrationViewModel()

        dataBinding.start.setOnClickListener { v ->
            run {
                val playerOne = dataBinding.player1.text.toString()
                val playerTwo = dataBinding.player2.text.toString()

                if (allNotEmpty(playerOne, playerTwo)){
                    val context = v.getContext()
                    val playGameIntent = Intent(context, PlayGameActivity::class.java)
                    playGameIntent.putExtra("players", arrayOf(playerOne, playerTwo))
                    context.startActivity(playGameIntent)
                } else {
                    //Transition to another Activity. Maybe a popup activity
                    println("Set at least 2 players")
                }
            }
        }

        dataBinding.executePendingBindings()

    }


    private fun allNotEmpty(vararg manyStrings: String?): Boolean {
        return Arrays.asList(*manyStrings)
            .all { !it.isNullOrBlank() }
    }
}

