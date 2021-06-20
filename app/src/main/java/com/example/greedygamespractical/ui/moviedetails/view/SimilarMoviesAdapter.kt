package com.example.greedygamespractical.ui.moviedetails.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greedygamespractical.databinding.ItemSimilarMoviesBinding
import com.example.greedygamespractical.model.Movie

class SimilarMoviesAdapter(
    var movieList: List<Movie>,
    private val onItemClickListener: (Movie) -> Unit
) :
    RecyclerView.Adapter<SimilarMoviesAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemSimilarMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount() = movieList.size

    inner class Holder(val binding: ItemSimilarMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            itemView.setOnClickListener {
                onItemClickListener(movie)
            }
        }
    }
}