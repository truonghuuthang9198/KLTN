package com.example.kltn.screen

import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.DetailActivity
import com.example.kltn.R
import com.example.kltn.screen.home.model.BookModel
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CommentAdapter internal constructor(var listComment: ArrayList<CommentModel>)
    : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){
    inner class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val tvNameCommet: TextView = itemView.findViewById(R.id.tvNameCommet)
        val tvDate:TextView = itemView.findViewById(R.id.tvDate)
        val star: RatingBar = itemView.findViewById(R.id.rating_comment)
        val tvContentComment: TextView = itemView.findViewById(R.id.tvContentComment)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)
        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_comment,parent,false)
        val CommentViewHolder = CommentViewHolder(cellForRow)
        return CommentViewHolder
    }

    override fun getItemCount() = listComment.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val current = listComment[position]
        holder.tvNameCommet.text = current.name
        holder.tvDate.text = current.date
        holder.star.rating = current.star.toFloat()
        holder.tvContentComment.text = current.content
    }

}