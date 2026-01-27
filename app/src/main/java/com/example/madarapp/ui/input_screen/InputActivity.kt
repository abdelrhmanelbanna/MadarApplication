package com.example.madarapp.ui.input_screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.madarapp.databinding.ActivityInputBinding
import com.example.madarapp.ui.list_screen.ListActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputBinding
    private val viewModel: InputViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {

            if (!validateForm()) return@setOnClickListener

            val gender = when (binding.rgGender.checkedRadioButtonId) {
                binding.rbMale.id -> "Male"
                binding.rbFemale.id -> "Female"
                else -> ""
            }

            viewModel.saveUser(
                name = binding.etName.text.toString(),
                age = binding.etAge.text.toString(),
                jobTitle = binding.etJob.text.toString(),
                gender = gender
            )
        }

        // Observe save result
        viewModel.saveSuccess.observe(this) { isSaved ->
            if (isSaved) {
                Toast.makeText(this, "User saved successfully", Toast.LENGTH_SHORT).show()
                // Navigate to ListActivity
                startActivity(Intent(this, ListActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun validateForm(): Boolean {
        var isValid = true

        val name = binding.etName.text.toString().trim()
        val age = binding.etAge.text.toString().trim()
        val job = binding.etJob.text.toString().trim()

        binding.tilName.error = null
        binding.tilAge.error = null
        binding.tilJob.error = null

        if (name.isEmpty()) {
            binding.tilName.error = "Name is required"
            isValid = false
        }

        if (job.isEmpty()) {
            binding.tilJob.error = "Job title is required"
            isValid = false
        }

        if (age.isEmpty()) {
            binding.tilAge.error = "Age is required"
            isValid = false
        } else if (age.toIntOrNull() == null) {
            binding.tilAge.error = "Age must be a number"
            isValid = false
        }

        if (binding.rgGender.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return isValid
    }



}
