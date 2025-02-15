package com.vinag.animack.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinag.animack.R
import com.vinag.animack.databinding.PopularAnimeItemBinding
import com.vinag.animack.models.popular.Data

class PopularAnimeAdapter : RecyclerView.Adapter<PopularAnimeAdapter.AnimeViewHolder>() {

    var onItemClick : ((Data) -> Unit)? = null

    inner class AnimeViewHolder(val binding : PopularAnimeItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.mal_id == newItem.mal_id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }

    var differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return AnimeViewHolder(
            PopularAnimeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val popularAnime = differ.currentList[position]

        when(position) {
            0 -> holder.binding.tvPopularRank.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.gold
                )
            )
            1 -> holder.binding.tvPopularRank.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.silver
                )
            )
            2 -> holder.binding.tvPopularRank.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.bronze
                )
            )
            else -> holder.binding.tvPopularRank.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.gray
                )
            )
        }
        holder.binding.apply {
            Glide
                .with(holder.itemView)
                .load(popularAnime.images.jpg.image_url)
                .into(ivPopularSrc)

            tvPopularTitle.text = popularAnime.title
            tvPopularRank.text = "${position + 1}"
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(popularAnime)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}