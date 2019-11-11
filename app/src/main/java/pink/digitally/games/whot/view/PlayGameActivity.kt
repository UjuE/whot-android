package pink.digitally.games.whot.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import pink.digitally.games.whot.R
import pink.digitally.games.whot.data.AndroidGameStateObserver
import pink.digitally.games.whot.data.PassThePhoneGameStateDetails
import pink.digitally.games.whot.databinding.ActivityPassPhonePlayGameBinding
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
        val players = intent.getStringArrayExtra("players")
            .map { name -> WhotPlayer(name) }
            .toList()

        val binding: ActivityPassPhonePlayGameBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_pass_phone_play_game)

        binding.lifecycleOwner = this
        binding.gameObserver = passThePhoneGamePlayViewModel

        passThePhoneGamePlayViewModel.gameStateModel.value = PassThePhoneGameStateDetails()

        passThePhoneGamePlayViewModel.gameStateModel.observe(
            this,
            GameStateDetailsChangeObserver(binding, passThePhoneGamePlayViewModel))

        WhotGamePlay.withDefaults()
            .withPlayers(players)
            .withGameStateObserver(AndroidGameStateObserver(passThePhoneGamePlayViewModel.gameStateModel))
            .build()
            .startGame()

        binding.executePendingBindings()

    }


}