package com.example.unrd.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unrd.R
import com.example.unrd.data.model.Characters
import kotlinx.android.synthetic.main.character_recycler_item.view.*

class CharacterAdapter(private var characterList: List<Characters>) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var characterImage = itemView.characterImage
        var characterName = itemView.characterName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return CharacterAdapter.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character_recycler_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = characterList[position]

        holder.apply {
            context?.let {
                Glide
                    .with(it)
                    .load(current.image.characterImage)
                    .circleCrop()
                    .into(characterImage)
            }

            holder.characterName.text = current.name

//            thumbnail.setOnClickListener { listener?.onThumbnailClicked(current.browserUrl) }
        }
    }
}
