package com.girogevoro.material_design.screens.system

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girogevoro.material_design.databinding.FragmentSystemRecyclerNoteBinding
import com.girogevoro.material_design.databinding.FragmentSystemRecyclerTitleBinding

class RecyclerAdapter(private var list: List<DataUser>) :
    RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            DataUser.TYPE_TITLE -> {
                val binding =
                    FragmentSystemRecyclerTitleBinding.inflate(LayoutInflater.from(parent.context))
                TitleViewHolder(binding)
            }

            else -> {
                val binding =
                    FragmentSystemRecyclerNoteBinding.inflate(LayoutInflater.from(parent.context))
                NoteViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class NoteViewHolder(private val binding: FragmentSystemRecyclerNoteBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(dataUser: DataUser) {
            (dataUser as? DataUser.Note)?.also {
                binding.note.text = it.description
            }
        }
    }

    class TitleViewHolder(private val binding: FragmentSystemRecyclerTitleBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(dataUser: DataUser) {
            (dataUser as? DataUser.Title)?.also {
                binding.title.text = it.title
            }
        }
    }

    abstract class BaseViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        abstract fun bind(dataUser: DataUser)
    }
}