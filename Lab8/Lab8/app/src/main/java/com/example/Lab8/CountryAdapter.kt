package com.example.Lab8

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Lab8.databinding.RecycleViewBinding


class CountryAdapter (private val items: List<Pair<String, String?>>,
                      private val onItemClick: (position: Int) -> Unit):
                    RecyclerView.Adapter<CountryAdapter.CountryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding = RecycleViewBinding.inflate(LayoutInflater.from(parent.context))
        return CountryHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        val country = items[position]
        holder.bind(country.first, country.second)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CountryHolder(private val binding: RecycleViewBinding,
                        private val onItemClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
            init {
                binding.buttonCountry.setOnClickListener {
                    onItemClick(adapterPosition)
                }
            }

            fun bind(countryName: String, imageUrl: String?) {
                Glide.with(binding.imageView)
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(binding.imageView.drawable)
                    .error(R.drawable.ic_launcher_background)
                    .fallback(R.drawable.ic_launcher_foreground)
                    .into(binding.imageView)
                binding.buttonCountry.text = countryName
        }
    }
}