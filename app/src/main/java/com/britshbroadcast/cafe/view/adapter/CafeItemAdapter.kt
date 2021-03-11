package com.britshbroadcast.cafe.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.britshbroadcast.cafe.R
import com.britshbroadcast.cafe.databinding.CafeItemLayoutBinding
import com.britshbroadcast.cafe.model.data.Result
import com.bumptech.glide.Glide

class CafeItemAdapter(private var resultList: List<Result> ): RecyclerView.Adapter<CafeItemAdapter.CafeItemViewHolder>() {



    inner class CafeItemViewHolder(val binding: CafeItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeItemViewHolder {
        val binding = CafeItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context),parent, false)
        return CafeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CafeItemViewHolder, position: Int) {

        var open = "OPEN"
        with(holder){
            with(resultList[position]){
                binding.businessNameTextView.text = this.name
                binding.businessAddressTextView.text = this.vicinity
                Glide.with(binding.businessIconImageView)
                    .load(this.icon)
                    .into(binding.businessIconImageView)

                if(this.opening_hours != null){
                    open = if(!this.opening_hours.open_now ) "OPEN" else "CLOSED"
                }

                binding.businessOpenTextView.text = open
                binding.businessRatingTextView.text = holder.binding.root.context.getString(R.string.business_rating_text, this.rating.toString())
            }
        }
    }

    override fun getItemCount(): Int = resultList.size

    fun updateDate(newList: List<Result>){
        resultList = newList
        notifyDataSetChanged()
    }
}