package com.example.madarapp.ui.list_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.madarapp.R
import com.example.madarapp.databinding.ActivityInputBinding
import com.example.madarapp.databinding.ActivityListBinding
import com.example.madarapp.ui.adapter.UserAdapter
import com.example.madarapp.ui.input_screen.InputActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    private val viewModel: UserListViewModel by viewModels()
    private lateinit var binding: ActivityListBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = UserAdapter()

        binding.userRecyclerView.adapter = adapter

        observeUsers()
        viewModel.loadUsers()

        navigateToInputScreen()

    }

    private fun navigateToInputScreen() {
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, InputActivity::class.java))
        }
    }

    private fun observeUsers() {
        viewModel.users.observe(this) { users ->
            adapter.submitList(users)
        }
    }


}