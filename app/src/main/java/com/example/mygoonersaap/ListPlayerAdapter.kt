package com.example.mygoonersaap

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mygoonersaap.databinding.ItemRowPlayerBinding

class ListPlayerAdapter(private val listPlayer: ArrayList<Player>) : RecyclerView.Adapter<ListPlayerAdapter.ListViewHolder>() {

    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowPlayerBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val player = listPlayer[position]
        holder.binding.tvItemName.text = player.name
        holder.binding.tvItemDescription.text = player.description
        holder.binding.imgItemPhoto.setImageResource(player.photo)

        holder.binding.btnShare.setOnClickListener {
            val message = "Arsenal Player :  ${player.name}"
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)
            shareIntent.type = "text/plain"

            val shareIntentChooser = Intent.createChooser(shareIntent, null)
            holder.itemView.context.startActivity(shareIntentChooser)
        }
    }

    override fun getItemCount(): Int {
        return listPlayer.size
    }

    inner class ListViewHolder(val binding: ItemRowPlayerBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                mListener?.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
}