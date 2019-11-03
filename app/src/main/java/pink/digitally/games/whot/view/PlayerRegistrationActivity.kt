package pink.digitally.games.whot.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import pink.digitally.games.whot.R
import pink.digitally.games.whot.databinding.ActivityPlayerRegistrationBinding
import pink.digitally.games.whot.model.PlayerRegistrationViewModel


class PlayerRegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("SO NO", "Why oh WHY!!!")
        val binding: ActivityPlayerRegistrationBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_player_registration
        )

        binding.playerRegistration = PlayerRegistrationViewModel()

        Log.i("tag", "OKKKK ")

    }
}

