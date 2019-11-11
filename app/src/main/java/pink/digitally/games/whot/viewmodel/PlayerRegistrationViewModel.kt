package pink.digitally.games.whot.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import pink.digitally.games.whot.R

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
}