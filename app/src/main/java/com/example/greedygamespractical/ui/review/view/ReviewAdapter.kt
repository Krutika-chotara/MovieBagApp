package com.example.greedygamespractical.ui.review.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greedygamespractical.data.remote.MovieReviewResponse
import com.example.greedygamespractical.databinding.ItemReviewBinding
import com.example.greedygamespractical.model.MovieReview

class ReviewAdapter(val reviewList: List<MovieReview?>?) :
    RecyclerView.Adapter<ReviewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(reviewList?.get(position))
    }

    override fun getItemCount() = reviewList!!.size

    inner class Holder(val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieReview: MovieReview?) {
            binding.model = movieReview
            binding.ratingBar.rating =
                (movieReview?.author_details?.rating?.toFloat())?.div(2) ?: 0.0f
            binding.txtContent.setContent(movieReview?.content)
        }
    }
}