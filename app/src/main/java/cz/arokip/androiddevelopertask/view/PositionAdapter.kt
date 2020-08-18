package cz.arokip.androiddevelopertask.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

        holder.positionText.text = positionItem.company
    }

    override fun getItemCount(): Int {
        return positions.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        internal var positionText: TextView =
            itemView.findViewById(R.id.positionText)

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