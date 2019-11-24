package pink.digitally.games.whot.view.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * The functions in this class extend the functionality of Layout
 */
@Suppress("unused")
object BindingAdapters {

    /**
     * Assigns Adapter to a given RecyclerView
     */
    @BindingAdapter("adapter")
    @JvmStatic fun <T : RecyclerView.ViewHolder> setRecyclerViewAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<T>) {
        view.adapter = adapter
    }

}