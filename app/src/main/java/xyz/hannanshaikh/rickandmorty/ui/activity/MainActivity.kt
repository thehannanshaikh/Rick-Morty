package xyz.hannanshaikh.rickandmorty.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import xyz.hannanshaikh.rickandmorty.R
import xyz.hannanshaikh.rickandmorty.adapter.CharacterPagingAdapter
import xyz.hannanshaikh.rickandmorty.adapter.LoadingStateAdapter
import xyz.hannanshaikh.rickandmorty.databinding.ActivityMainBinding
import xyz.hannanshaikh.rickandmorty.ui.viewmodel.CharacterViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CharacterViewModel
    private lateinit var adapter:CharacterPagingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]

        setUpRecyclerView()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.characters.collect {
                    adapter.submitData(it)
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.rvCharacters.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        adapter = CharacterPagingAdapter()

        val loadStateAdapter = LoadingStateAdapter{adapter.retry()}

        binding.rvCharacters.adapter = adapter.withLoadStateFooter(loadStateAdapter)

        adapter.addLoadStateListener { loadState ->

            when(loadState.refresh){
                is LoadState.NotLoading -> {
                    if(binding.refreshing == true){
                        binding.refreshing = false
                    }
                }
                is LoadState.Loading -> {
                    binding.refreshing = true
                }
                is LoadState.Error -> {
                    binding.refreshing = false
                    Toast.makeText(this, (loadState.refresh as LoadState.Error).error.toString(),Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}