package xyz.hannanshaikh.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import xyz.hannanshaikh.rickandmorty.BR
import xyz.hannanshaikh.rickandmorty.data.model.CharacterItem
import xyz.hannanshaikh.rickandmorty.databinding.ItemCharacterBinding

class CharacterPagingAdapter() : PagingDataAdapter<CharacterItem,CharacterPagingAdapter.ViewHolder>(DIFF_UTIL) {

    inner class ViewHolder(private val binding:ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(character: CharacterItem?){
            binding.setVariable(BR.character,character)
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCharacter = getItem(position)
        holder.bind(currentCharacter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    companion object{
         val DIFF_UTIL = object : DiffUtil.ItemCallback<CharacterItem>(){
            override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
                return oldItem == newItem
            }

        }
    }

}