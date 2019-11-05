package pink.digitally.games.whot.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import pink.digitally.games.whot.R
import pink.digitally.games.whot.databinding.ActivityPlayGameBinding
import pink.digitally.games.whot.model.AndroidGameStateObserver
import pink.digitally.games.whot.model.WhotPlayer
import pink.digitally.games.whot.whotcore.WhotGamePlay

class PlayGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = getIntent()
        val playerOneName = intent.getStringExtra("playerOneName")
        val playerTwoName = intent.getStringExtra("playerTwoName")
        val binding: ActivityPlayGameBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_play_game
        )

        binding.setLifecycleOwner(this)

        val androidGameStateObserver = AndroidGameStateObserver(this)

        WhotGamePlay.withDefaults()
            .withPlayers(WhotPlayer(playerOneName), WhotPlayer(playerTwoName))
            .withGameStateObserver(androidGameStateObserver)
            .build()
            .startGame()

        binding.gameObserver = androidGameStateObserver

    }
}