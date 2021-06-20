package com.example.greedygamespractical.ui.moviedetails.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greedygamespractical.databinding.ItemProductionHouseBinding
import com.example.greedygamespractical.databinding.ItemSimilarMoviesBinding
import com.example.greedygamespractical.model.MovieDetails

class ProductionAdapter(private val productionList: List<MovieDetails.ProductionCompany?>?) :
    RecyclerView.Adapter<ProductionAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemProductionHouseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(productionList?.get(position))
    }

    override fun getItemCount() = productionList!!.size

    inner class Holder(val binding: ItemProductionHouseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieDetails.ProductionCompany?) {
            binding.model = data
        }
    }
}