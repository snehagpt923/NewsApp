package com.example.newsapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.NewsListItemBinding
import com.example.newsapp.domain.News
import com.example.newsapp.utils.Utility
import com.example.newsapp.utils.loadImage

class NewsListRecyclerViewAdapter constructor(context: Context, data: List<News>) :
    RecyclerView.Adapter<NewsListRecyclerViewAdapter.NewsViewHolder>() {
    private val mData: List<News> = data
    private var mClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        with(holder) {
            with(mData[position]) {
                binding.tvTitle.text = this.title
                binding.tvDescription.text = description
                binding.ivBanner.loadImage(bannerUrl)
                binding.tvCreationTime.text = Utility.getReadableTime(this.creationTime)
            }
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class NewsViewHolder(val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
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