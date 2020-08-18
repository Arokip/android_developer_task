package cz.arokip.androiddevelopertask.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import cz.arokip.androiddevelopertask.R
import cz.arokip.androiddevelopertask.data.Position

class PositionAdapter : RecyclerView.Adapter<PositionAdapter.ViewHolder>() {

    private var mClickListener: ItemClickListener? = null

    private var positions = emptyList<Position>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val positionView = inflater.inflate(R.layout.item_recyclerview_position, parent, false)
        return ViewHolder(positionView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val positionItem = positions[position]

        holder.positionTitle.text = positionItem.title
        holder.positionType.text = positionItem.type
        holder.positionCompany.text =
            holder.positionCompany.context.getString(R.string.company, positionItem.company)
        holder.positionLocation.text = positionItem.location

        Picasso.get().load(positionItem.companyLogo).into(holder.companyLogo)
    }

    override fun getItemCount(): Int {
        return positions.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        internal var companyLogo: ImageView = itemView.findViewById(R.id.companyLogo)

        internal var positionTitle: TextView = itemView.findViewById(R.id.positionTitle)
        internal var positionType: TextView = itemView.findViewById(R.id.positionType)
        internal var positionCompany: TextView = itemView.findViewById(R.id.positionCompany)
        internal var positionLocation: TextView = itemView.findViewById(R.id.positionLocation)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }
    }

    internal fun getItem(id: Int): Position {
        return positions[id]
    }

    internal fun setClickListener(itemClickListener: ItemClickListener) {
        this.mClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    internal fun setPositions(times: List<Position>?) {
        if (times != null) {
            this.positions = times
        }
        notifyDataSetChanged()
    }
}