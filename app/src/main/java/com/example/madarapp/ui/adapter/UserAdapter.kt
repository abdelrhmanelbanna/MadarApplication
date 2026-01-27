package com.example.madarapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madarapp.databinding.ItemUserBinding
import com.example.madarapp.model.UserUiModel


class UserAdapter(
    private var users: List<UserUiModel> = emptyList()
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    fun submitList(newList: List<UserUiModel>) {
        users = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    class UserViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserUiModel) {
            binding.binding = item   // matches <variable name="binding" />
        }
    }
}