package pink.digitally.games.whot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import pink.digitally.games.whot.databinding.ActivityPlayerRegistrationBinding
import pink.digitally.games.whot.viewmodel.PlayerRegistrationViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityPlayerRegistrationBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_player_registration)

        dataBinding.lifecycleOwner = this
        dataBinding.playerRegistration =
            PlayerRegistrationViewModel()

        //Where was this code earlier?
        dataBinding.executePendingBindings()
//        setSupportActionBar(toolbar)

    }
}
