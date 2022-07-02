package com.ishwar.notesapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ishwar.notesapp.R
import com.ishwar.notesapp.databinding.ItemRvNotesBinding
import com.ishwar.notesapp.entities.Notes

class NotesAdapter(val arrList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    lateinit var inflater: LayoutInflater


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        inflater = LayoutInflater.from(parent.context)
        return NotesViewHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_rv_notes,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemRvNotesBinding.tvTitle.text = arrList[position].title
        holder.itemRvNotesBinding.tvDesc.text = arrList[position].noteText
        holder.itemRvNotesBinding.tvDateTime.text = arrList[position].dateTime
        if (arrList[position].color != null) {
            holder.itemRvNotesBinding.cardView.setCardBackgroundColor(Color.parseColor(arrList[position].color))
        }else{
           // holder.itemRvNotesBinding.cardView.setCardBackgroundColor(R.color.ColorLightBlack)
        }
    }//R.color.ColorLightBlack

    override fun getItemCount(): Int {
        return arrList.size
    }

    class NotesViewHolder(var itemRvNotesBinding: ItemRvNotesBinding) :
        RecyclerView.ViewHolder(itemRvNotesBinding.root) {

    }


}

