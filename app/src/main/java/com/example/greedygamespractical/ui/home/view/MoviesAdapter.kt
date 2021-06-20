package com.example.greedygamespractical.ui.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greedygamespractical.databinding.ItemHomeMovieBinding
import com.example.greedygamespractical.listener.OnLoadMoreListener
import com.example.greedygamespractical.model.Movie
import com.example.greedygamespractical.ui.home.view.MoviesAdapter.*

class MoviesAdapter(
    private val movieList: List<Movie?>,
    recyclerView: RecyclerView,
    private val linearLayoutManager: LinearLayoutManager,
    private val onItemClickListener: (Movie?) -> Unit
) : RecyclerView.Adapter<Holder>() {

    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0
    private var loading: Boolean = false

    private var rvList: RecyclerView = recyclerView
    private var onLoadMoreListener: OnLoadMoreListener? = null

    init {
        this.rvList = recyclerView
        rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(
                recyclerView: RecyclerView,
                dx: Int, dy: Int
            ) {
                if (dy > 0) {
                    totalItemCount = linearLayoutManager.getItemCount()
                    lastVisibleItem = linearLayoutManager
                        .findLastVisibleItemPosition()

                    val endHasBeenReached = lastVisibleItem >= (totalItemCount - 1)

                    if ((!loading && totalItemCount > 0 && endHasBeenReached)) {
                        loading = true
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener!!.onLoadMore()
                        }
                    }
                }

            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemHomeMovieBinding.inflate(
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

    inner class Holder(private var binding: ItemHomeMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie?) {
            binding.movie = movie
            binding.root.setOnClickListener {
                onItemClickListener(movie)
            }
            binding.ratingBar.rating = (movie?.voteAverage?.toFloat())?.div(2) ?: 0.0f
        }
    }

    fun setLoaded() {
        loading = false
    }

    fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener
    }
}