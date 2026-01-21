package com.shaikbilal.userdemocleanarch.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shaikbilal.userdemocleanarch.R
import com.shaikbilal.userdemocleanarch.databinding.UserItemBinding
import com.shaikbilal.userdemocleanarch.domain.model.User

class UserAdapter: RecyclerView.Adapter<UserAdapter.userVH>() {

    private var user = mutableListOf<User>()

    fun submitList(list: List<User>){
        user.clear()
        user.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): userVH {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return userVH(binding)
    }

    override fun onBindViewHolder(
        holder: userVH,
        position: Int
    ) {
        val bind = user[position]
        holder.binding.imgUser.setImageResource(R.drawable.ic_launcher_foreground)
        holder.binding.tvName.text = "Name : ${bind.name}"
        holder.binding.tvAge.text = "Experience : ${bind.age}"
    }

    override fun getItemCount()= user.size

    class userVH(val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root)
}