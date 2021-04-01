package com.example.newsapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.domain.News
import com.example.newsapp.utils.Utility
import com.example.newsapp.utils.loadImage

class NewsListRecyclerViewAdapter constructor(context: Context, data: List<News>) :
    RecyclerView.Adapter<NewsListRecyclerViewAdapter.ViewHolder>() {
    private val mData: List<News> = data
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.news_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mData[position].apply {
            holder.tvTitle.text = title
            holder.tvDescription.text = description
            holder.ivBanner.loadImage(bannerUrl)
            holder.tvCreationTime.text = Utility.getReadableTime(this.creationTime)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var tvTitle = itemView.findViewById(R.id.tvTitle) as TextView
        var tvDescription = itemView.findViewById(R.id.tvDescription) as TextView
        var ivBanner = itemView.findViewById(R.id.ivBanner) as ImageView
        var tvCreationTime = itemView.findViewById(R.id.tvCreationTime) as TextView

        override fun onClick(view: View) {
            mClickListener?.onItemClick(view, mData[adapterPosition])
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, news: News)
    }
}