package com.example.madarapp.ui.input_screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.validator.FieldType
import com.example.domain.validator.ValidationResult
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

        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.btnSave.setOnClickListener {

            clearErrors()

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

        binding.btnGoToList.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

    }

    private fun observeViewModel() {
        viewModel.saveStatus.observe(this) { result ->
            when (result) {
                is ValidationResult.Success -> handleSuccess()
                is ValidationResult.Error -> handleError(result)
            }
        }
    }

    private fun handleSuccess() {
        Toast.makeText(this, "User saved successfully", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, ListActivity::class.java))
        finish()
    }

    private fun handleError(error: ValidationResult.Error) {
        when (error.field) {
            FieldType.NAME -> binding.tilName.error = error.message
            FieldType.AGE -> binding.tilAge.error = error.message
            FieldType.JOB -> binding.tilJob.error = error.message
            FieldType.GENDER -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearErrors() {
        binding.tilName.error = null
        binding.tilAge.error = null
        binding.tilJob.error = null
    }
}