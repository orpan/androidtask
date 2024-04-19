package com.example.andoridtask.paging

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.andoridtask.R
import com.example.andoridtask.model.ImageListItem
import java.util.concurrent.Executors

class QuotePagingAdapter : PagingDataAdapter<ImageListItem, QuotePagingAdapter.QuoteViewHolder>(COMPARATOR) {

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listdata_image = itemView.findViewById<ImageView>(R.id.listdata_image)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            val item = getItem(position)
            if (item != null) {
                val executor = Executors.newSingleThreadExecutor()
                // Initializing the image
                var image: Bitmap? = null
                executor.execute {
                    try {
                        val `in` = java.net.URL(item?.urls?.regular).openStream()
                        image = BitmapFactory.decodeStream(`in`)
                        holder.listdata_image.setImageBitmap(image)
                    } catch (e: Exception) {
                        e.printStackTrace()

                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_quote_layout, parent, false)
        return QuoteViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ImageListItem>() {
            override fun areItemsTheSame(oldItem: ImageListItem, newItem: ImageListItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageListItem, newItem: ImageListItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}