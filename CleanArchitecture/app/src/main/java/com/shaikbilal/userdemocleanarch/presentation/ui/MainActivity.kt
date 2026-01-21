package com.shaikbilal.usermvvmdemo.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaikbilal.userdemocleanarch.R
import com.shaikbilal.userdemocleanarch.data.repository.UserRepositoryImpl
import com.shaikbilal.userdemocleanarch.databinding.ActivityMainBinding
import com.shaikbilal.userdemocleanarch.di.MainViewModelFactory
import com.shaikbilal.userdemocleanarch.domain.model.User
import com.shaikbilal.userdemocleanarch.domain.usecase.GetUsersUseCase
import com.shaikbilal.userdemocleanarch.presentation.state.MainUiState
import com.shaikbilal.userdemocleanarch.presentation.ui.UserAdapter
import com.shaikbilal.userdemocleanarch.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
//    private val viewmodel : MainViewModel by viewModels()
    private lateinit var viewmodel : MainViewModel
    private val adapter = UserAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1️⃣ Inflate binding FIRST
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 2️⃣ Set content view
        setContentView(binding.root)

        // 3️⃣ Edge-to-edge AFTER content view
        enableEdgeToEdge()

        // 4️⃣ Apply insets on a REAL view
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        // 🔥 MANUAL DEPENDENCY CREATION
        val repository = UserRepositoryImpl()
        val useCase = GetUsersUseCase(repository)
        val factory = MainViewModelFactory(useCase)

        viewmodel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        // 5️⃣ Rest of your logic
        setupRecyclerView()
        observeUiState()
        viewmodel.loadUsers()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                // here we have to call the viewmodel
                viewmodel.uiState.collect { state ->
                    Log.d("UI_STATE",state.toString())
                    when (state){
                        is MainUiState.Loading -> showLoading()
                        is MainUiState.Success -> showUsers(state.user)
                        is MainUiState.Error -> showError(state.msg)
                    }
                }
            }
        }
    }

    private fun showError(msg: String) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = "Error During load..."
    }

    private fun showUsers(user: List<User>) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        binding.tvError.visibility = View.GONE
        adapter.submitList(user)
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.tvError.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}