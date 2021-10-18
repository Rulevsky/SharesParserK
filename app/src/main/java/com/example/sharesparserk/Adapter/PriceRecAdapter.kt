import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sharesparserk.R
import com.example.sharesparserk.model.OneStockPosition

class PriceRecAdapter(private val dataSet: MutableList<OneStockPosition>):
    RecyclerView.Adapter<PriceRecAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val acronymTextView: TextView
        val stockIdTextView: TextView
        val currentPriceTextView: TextView

        init {
            acronymTextView = view.findViewById(R.id.acronymTextView)
            stockIdTextView = view.findViewById(R.id.stockIdTextView)
            currentPriceTextView = view.findViewById(R.id.currentPriceTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceRecAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_share_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PriceRecAdapter.ViewHolder, position: Int) {
        holder.acronymTextView.text = dataSet.get(position).acronym
        holder.currentPriceTextView.text = dataSet.get(position).currentPrice.toString()
        holder.stockIdTextView.text = dataSet.get(position).stockId.toString()

    }

    override fun getItemCount() = dataSet.size


}