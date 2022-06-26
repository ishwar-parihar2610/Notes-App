package com.ishwar.notesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ishwar.notesapp.R
import com.ishwar.notesapp.databinding.ItemRvNotesBinding
import com.ishwar.notesapp.entities.Notes

class NotesAdapter(val arrayList: List<Notes>) :
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
       holder.itemRvNotesBinding.tvTitle.text=arrayList[position].title
       holder.itemRvNotesBinding.tvDesc.text=arrayList[position].noteText
       holder.itemRvNotesBinding.tvDateTime.text=arrayList[position].dateTime
    }

    override fun getItemCount(): Int {
      return arrayList.size
    }

    class NotesViewHolder(var itemRvNotesBinding: ItemRvNotesBinding) :
        RecyclerView.ViewHolder(itemRvNotesBinding.root) {

        }


}

