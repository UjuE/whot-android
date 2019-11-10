package pink.digitally.games.whot

//import android.R <-- This line makes it impossible for this test to compile.
import android.widget.Button
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    @Ignore("I have not decided what this should do.")
    fun nameOfAppAndStartArePresent() {
        @StringRes val resIds = intArrayOf(
            R.string.app_name,
            R.string.start
        )
        for (resId in resIds) {
            val text = rule.getActivity().getString(resId)
            onView(withText(text)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun playerOneIsAvailable() {
        val resId = R.string.enter_player_name
        val hint = rule.getActivity().getString(resId)
        onView(withId(R.id.player1))
            .check(selectedDescendantsMatch(isAssignableFrom(EditText::class.java), isDisplayed()))
            .check(hasHint(hint))
    }

    @Test
    fun playerTwoIsAvailable() {
        val resId = R.string.enter_player_name
        val hint = rule.getActivity().getString(resId)

        onView(withId(R.id.player2))
            .check(selectedDescendantsMatch(isAssignableFrom(EditText::class.java), isDisplayed()))
            .check(hasHint(hint))
    }

    @Test
    fun noPlayerNameExists() {
        val playerOneName = rule.getActivity().findViewById<EditText>(R.id.player1)
        val playerTwoName = rule.getActivity().findViewById<EditText>(R.id.player2)
        val startButton = rule.getActivity().findViewById<Button>(R.id.start)


        rule.runOnUiThread {
            playerOneName.setText(null)
            playerTwoName.setText(null)
        }

        assertThat(startButton, isEnabled())
    }

    @Test
    fun onlyOneNameExists() {
        val playerOneName = rule.getActivity().findViewById<EditText>(R.id.player1)
        val playerTwoName = rule.getActivity().findViewById<EditText>(R.id.player2)
        val startButton = rule.getActivity().findViewById<Button>(R.id.start)

        rule.runOnUiThread {
            playerOneName.setText("James")
            playerTwoName.setText(null)
        }

        assertThat(startButton, isEnabled())
    }

    @Test
    fun twoPlayerNamesExists() {
        val playerOneName = rule.getActivity().findViewById<EditText>(R.id.player1)
        val playerTwoName = rule.getActivity().findViewById<EditText>(R.id.player2)
        val startButton = rule.getActivity().findViewById<Button>(R.id.start)

        rule.runOnUiThread {
            playerOneName.setText("James")
            playerTwoName.setText("John")
        }

        assertThat(startButton, isEnabled())
    }



    private fun hasHint(expectedHint: String): ViewAssertion? {
        return ViewAssertion { view, _ -> assertThat(view, withHint(expectedHint)) }

    }
}