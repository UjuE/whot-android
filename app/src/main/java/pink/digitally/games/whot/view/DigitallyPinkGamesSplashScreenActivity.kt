package pink.digitally.games.whot.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pink.digitally.games.whot.MainActivity
import pink.digitally.games.whot.R


class DigitallyPinkGamesSplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val intent = Intent(
            applicationContext,
            MainActivity::class.java
        )
        startActivity(intent)
        finish()
    }
}