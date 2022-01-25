package xyz.hannanshaikh.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import xyz.hannanshaikh.rickandmorty.databinding.ItemLoadingStateBinding

class LoadingStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadingStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(private val binding: ItemLoadingStateBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(loadState: LoadState){
            binding.btnRetry.isVisible = loadState !is LoadState.Loading
            binding.errorMessage.isVisible = loadState !is LoadState.Loading
            binding.loaderProgress.isVisible = loadState is LoadState.Loading

            if (loadState is LoadState.Error){
                binding.errorMessage.text = loadState.error.localizedMessage
            }

            binding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}