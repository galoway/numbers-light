package com.tapptic.numberslight.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tapptic.numberslight.R
import com.tapptic.numberslight.model.Number
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter(private val clickListener: OnItemClickListener): RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    private var mList: List<Number> = emptyList()
    private var lastSelectedPos: Int? = null

    fun updateList(list: List<Number>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private val image: ImageView = view.image
        private val name: TextView = view.name
        private lateinit var number: Number

        fun bind(number: Number) {
            this.number = number
            name.text = number.name
            Picasso.get().load(number.image).into(image)
            view.isSelected = false
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (lastSelectedPos != null) {
                notifyItemChanged(lastSelectedPos!!)
            }
            lastSelectedPos = mList.indexOf(number)
            v!!.isSelected = true
            clickListener.onItemClick(number.name)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(name: String)
    }
}