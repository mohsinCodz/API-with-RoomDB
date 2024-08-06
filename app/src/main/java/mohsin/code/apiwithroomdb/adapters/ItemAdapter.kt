package mohsin.code.apiwithroomdb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mohsin.code.apiwithroomdb.model.Drink
import mohsin.code.apiwithroomdb.R


class ItemAdapter(
    private var items: ArrayList<Drink>,
    private val onItemClick: (Drink) -> Unit
) : RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_count, parent, false)
        return MyViewHolder(itemView)
    }

    //define the size of item in recycler view
    override fun getItemCount(): Int {
        return items.size
    }

    fun setFilteredList(mList: ArrayList<Drink>){
        this.items = mList
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val drink = items[position]
        holder.idDrink.text = drink.idDrink
        holder.strDrink.text = drink.strDrink
        holder.itemView.setOnClickListener {
            onItemClick(drink)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idDrink: TextView = itemView.findViewById(R.id.tvIdDrink)
        val strDrink: TextView = itemView.findViewById(R.id.tvStrDrink)
    }
}