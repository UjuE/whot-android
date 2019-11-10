package pink.digitally.games.whot.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import pink.digitally.games.whot.R
import pink.digitally.games.whot.data.AndroidGameStateObserver
import pink.digitally.games.whot.data.PassThePhoneGameStateDetails
import pink.digitally.games.whot.databinding.ActivityPlayGameBinding
import pink.digitally.games.whot.model.WhotPlayer
import pink.digitally.games.whot.view.observer.GameStateDetailsChangeObserver
import pink.digitally.games.whot.viewmodel.PassThePhoneGamePlayViewModel
import pink.digitally.games.whot.whotcore.WhotGamePlay

class PlayGameActivity : AppCompatActivity() {
    private val passThePhoneGamePlayViewModel by lazy {
        ViewModelProviders.of(this)
            .get(PassThePhoneGamePlayViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = getIntent()
        val playerOneName = intent.getStringExtra("playerOneName")
        val playerTwoName = intent.getStringExtra("playerTwoName")

        val binding: ActivityPlayGameBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_play_game)

        binding.lifecycleOwner = this
        binding.gameObserver = passThePhoneGamePlayViewModel

        passThePhoneGamePlayViewModel.gameStateModel.value = PassThePhoneGameStateDetails()

        passThePhoneGamePlayViewModel.gameStateModel.observe(
            this,
            GameStateDetailsChangeObserver(binding, passThePhoneGamePlayViewModel))

        WhotGamePlay.withDefaults()
            .withPlayers(WhotPlayer(playerOneName), WhotPlayer(playerTwoName))
            .withGameStateObserver(AndroidGameStateObserver(passThePhoneGamePlayViewModel.gameStateModel))
            .build()
            .startGame()

        binding.executePendingBindings()

    }


}