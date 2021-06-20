package com.example.greedygamespractical.ui.cast.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greedygamespractical.databinding.ItemCastBinding
import com.example.greedygamespractical.model.MovieCredits

class CastAdapter(val castList: List<MovieCredits.Cast?>?) :
    RecyclerView.Adapter<CastAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemCastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(castList?.get(position))
    }

    override fun getItemCount() = castList!!.size

    inner class Holder(val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: MovieCredits.Cast?) {
            binding.cast = cast
        }
    }
}