package com.example.myfirstsampleapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstsampleapp.databinding.ItemViewBinding

class MyAdapter(
    private val butterfliesList: ArrayList<Butterfly>,
    private val myInterface: MyInterface
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(butterfliesList[position])
    }

    override fun getItemCount(): Int {
        return butterfliesList.size
    }

    fun addItem(butterfly: Butterfly) {
        butterfliesList.add(butterfly)
        notifyItemInserted(butterfliesList.size - 1)
    }

    inner class MyViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(butterfly: Butterfly) {
            butterfly.butterfly?.let {
                binding.butterfly.setImageResource(it)
            }

            butterfly.image?.let {
                binding.butterfly.setImageBitmap(it)
            }

            binding.butterflyName.text = butterfly.name
            binding.butterflyFamily.text = butterfly.family
            binding.count.text = butterfly.count.toString()

            binding.butterfly.setOnClickListener {
                myInterface.onButterClicked(butterfly)
            }

            binding.minus.setOnClickListener {
                butterfly.count -= 1
                binding.count.text = butterfly.count.toString()
            }

            binding.plus.setOnClickListener {
                butterfly.count += 1
                binding.count.text = butterfly.count.toString()
            }

            binding.deleteItem.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    butterfliesList.removeAt(position)
                    notifyItemRemoved(position)
                }
            }

        }
    }
}

