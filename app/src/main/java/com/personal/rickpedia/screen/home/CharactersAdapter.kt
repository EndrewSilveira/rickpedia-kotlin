package com.personal.rickpedia.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.personal.rickpedia.databinding.AdapterCharacterBinding
import com.personal.rickpedia.domain.character.Character

class CharactersAdapter(diffUtil: CharacterDiffUtil = CharacterDiffUtil()) :
    PagingDataAdapter<Character, CharactersAdapter.ViewHolder>(diffUtil) {

    companion object {
        const val STATUS_ALIVE = "alive"
        const val STATUS_DEAD = "dead"
    }

    var onItemClick: ((character: Character) -> Unit)? = null

    inner class ViewHolder(val binding: AdapterCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { item: Character ->
            val context = holder.itemView.context

            Glide.with(context)
                .load(item.image)
                .into(holder.binding.ivCharacterImage)

            holder.binding.tvCharacterName.text = item.name

            holder.itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            AdapterCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }
}