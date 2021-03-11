package com.britshbroadcast.cafe.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.britshbroadcast.cafe.model.data.Result
import com.britshbroadcast.cafe.databinding.CafeItemLayoutBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CafeItemAdapter(private var resultList: List<Result> ): RecyclerView.Adapter<CafeItemAdapter.CafeItemViewHolder>() {



    inner class CafeItemViewHolder(val binding: CafeItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeItemViewHolder {
        val binding = CafeItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context),parent, false)
        return CafeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CafeItemViewHolder, position: Int) {
        with(holder){
            with(resultList[position]){
                binding.businessNameTextView.text = this.name
                binding.businessAddressTextView.text = this.vicinity
                Glide.with(binding.businessIconImageView)
                    .setDefaultRequestOptions(RequestOptions.circleCropTransform())
                    .load(this.icon)
                    .into(binding.businessIconImageView)
                binding.businessOpenTextView.text = if(this.opening_hours.open_now) "OPEN" else "CLOSED"
                binding.businessRatingTextView.text = this.rating.toString()
            }
        }
    }

    override fun getItemCount(): Int = resultList.size

    fun updateDate(newList: List<Result>){
        resultList = newList
        notifyDataSetChanged()
    }
}