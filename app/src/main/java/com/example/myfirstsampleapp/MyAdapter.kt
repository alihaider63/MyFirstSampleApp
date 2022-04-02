package com.example.myfirstsampleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(
    private var butterfliesList: MutableList<Butterfly>,
    //val onImageClicked: (MutableList<Butterfly>) -> Unit
    private val myInterface: MyInterface
)
    : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(butterfliesList, position, this, myInterface)

    }
    override fun getItemCount(): Int {
        return butterfliesList.size
    }

    fun addItem(butterfly: Butterfly) {
        butterfliesList.add(butterfly)
        notifyItemInserted(butterfliesList.size - 1)
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var butterflyImageView: ImageView? = itemView.findViewById<ImageView>(R.id.butterfly)
        var butterflyName: TextView? = itemView.findViewById<TextView>(R.id.butterflyName)
        var butterflyFamily: TextView? = itemView.findViewById<TextView>(R.id.butterflyFamily)
        var minus: Button? = itemView.findViewById(R.id.minus)
        var plus: Button? = itemView.findViewById(R.id.plus)
        var counter: TextView? = itemView.findViewById(R.id.count)
        var deleteButton: Button? = itemView.findViewById(R.id.delete_Item)

        fun bind(butterfliesList: MutableList<Butterfly>, position: Int, myAdapter: MyAdapter, myInterface: MyInterface) {
            butterflyImageView?.let { butterflyImageView ->
                if (butterfliesList[position].butterfly != null &&
                    butterfliesList[position].image == null) {
                    butterfliesList[position].butterfly?.let {
                        butterflyImageView.setImageResource(
                            it
                        )
                    }
                }else if (butterfliesList[position].image != null &&
                    butterfliesList[position].butterfly == null){
                    butterfliesList[position].image?.let {
                        butterflyImageView.setImageBitmap(it)
                    }
                } else {
                    butterflyImageView.setImageResource(R.drawable.back)
                }
            }

            butterflyName?.let {
                it.text = butterfliesList[position].name
            }

            butterflyFamily?.let {
                it.text = butterfliesList[position].family
            }

            butterflyImageView?.let {
                it.setOnClickListener {
                    myInterface.onButterClicked(position,butterfliesList)
                }
            }
            counter?.let {
                it.text = "${butterfliesList[position].count}"
//                it.text = (butterfliesList[position].count).toString()

            }
            minus?.let {
                it.setOnClickListener {
//                    butterfliesList[position].count -= 1
//                    myAdapter.notifyItemChanged(position)
                    butterfliesList[position].count -= 1
                    counter?.text = (butterfliesList[position].count).toString()
                }
            }
            plus?.let {
                it.setOnClickListener {
                    //butterfliesList[position].count += 1
                    //myAdapter.notifyItemChanged(position)
                    butterfliesList[position].count += 1
                    counter?.text = (butterfliesList[position].count).toString()
                }
            }
            deleteButton?.let {
                it.setOnClickListener {
                    butterfliesList.removeAt(position)
                    myAdapter.notifyItemRemoved(position)
                    myAdapter.notifyItemRangeChanged(position, butterfliesList.size)
                }
            }

        }
    }
}

