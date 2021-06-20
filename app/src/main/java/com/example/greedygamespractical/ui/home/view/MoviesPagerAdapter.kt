package com.example.greedygamespractical.ui.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greedygamespractical.databinding.ItemHomeViewpagerMovieBinding
import com.example.greedygamespractical.model.Movie

class MoviesPagerAdapter(private val images: List<Movie?>) :
    RecyclerView.Adapter<MoviesPagerAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemHomeViewpagerMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount() = images.size

    inner class Holder(private var binding: ItemHomeViewpagerMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie?) {
            binding.movie = movie
        }
    }
}