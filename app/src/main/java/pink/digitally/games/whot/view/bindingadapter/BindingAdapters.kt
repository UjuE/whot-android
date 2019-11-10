package pink.digitally.games.whot.view.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapters {
    @BindingAdapter("adapter")
    @JvmStatic fun <T : RecyclerView.ViewHolder> setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<T>) {
        view.adapter = adapter
    }
}