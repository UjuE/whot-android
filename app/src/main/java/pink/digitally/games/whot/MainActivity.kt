package pink.digitally.games.whot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pink.digitally.games.whot.view.PlayerRegistrationActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val playerRegistrationActivity = Intent(applicationContext, PlayerRegistrationActivity::class.java)
        applicationContext.startActivity(playerRegistrationActivity)

    }
}
