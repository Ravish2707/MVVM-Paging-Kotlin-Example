package com.ravish.paging3_mvvm_kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ravish.paging3_mvvm_kotlin.R
import com.ravish.paging3_mvvm_kotlin.adapter.DogsAdapter
import com.ravish.paging3_mvvm_kotlin.adapter.LoadingStateAdapter
import com.ravish.paging3_mvvm_kotlin.databinding.ActivityMainBinding
import com.ravish.paging3_mvvm_kotlin.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels()

    @Inject lateinit var dogsAdapter : DogsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerview()

        lifecycleScope.launchWhenStarted {
            mainViewModel.getAllDogs().collectLatest {response ->
                binding.apply {
                    recyclerview.isVisible = true
                    progressBar.isVisible = false
                }

                dogsAdapter.submitData(response)
            }
        }
    }

    private fun initRecyclerview() {
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(this@MainActivity, 2)
                adapter = dogsAdapter.withLoadStateHeaderAndFooter(
                    header = LoadingStateAdapter{dogsAdapter.retry()},
                    footer = LoadingStateAdapter{dogsAdapter.retry()}
                )

            }
        }
    }
}