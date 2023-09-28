package com.example.listofcountriestask.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listofcountriestask.data.model.CountriesModel
import com.example.listofcountriestask.databinding.ItemCountriesBinding

class Adapter(var countries: CountriesModel) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(val binding: ItemCountriesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCountriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        countries[position].let {

            holder.binding.tvName.text = it.name
            holder.binding.tvRegion.text = it.region
            holder.binding.tvCapital.text = it.capital
            holder.binding.tvCode.text = it.code

        }

    }

    override fun getItemCount(): Int = countries.size ?: 0
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: CountriesModel) {
        this.countries.clear()
        this.countries.addAll(newData)
        notifyDataSetChanged()

    }

}
