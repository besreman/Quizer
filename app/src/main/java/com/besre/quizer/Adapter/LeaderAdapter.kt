package com.besre.quizer.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.besre.quizer.Domain.User
import com.besre.quizer.databinding.ViewHolderLeaderBinding
import com.bumptech.glide.Glide

class LeaderAdapter: RecyclerView.Adapter<LeaderAdapter.ViewHolder>() {
    lateinit var binding:ViewHolderLeaderBinding
    inner class ViewHolder: RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderAdapter.ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        binding = ViewHolderLeaderBinding.inflate(inflator, parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: LeaderAdapter.ViewHolder, position: Int) {
        val binding = ViewHolderLeaderBinding.bind(holder.itemView)
        binding.txtName.text = differ.currentList[position].name
        val drawableResId:Int = binding.root.resources.getIdentifier(differ.currentList[position].pic, "drawable", binding.root.context.packageName)

        Glide.with(binding.root.context)
            .load(drawableResId)
            .into(binding.pic)

        binding.txtRank.text= (position+4).toString()
        binding.txtScore.text = differ.currentList[position].score.toString()
    }

    override fun getItemCount() = differ.currentList.size

    private val differCallback = object:DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

}