package com.zemoga.mobiletest.ui.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zemoga.mobiletest.R
import com.zemoga.mobiletest.persistence.entity.CommentEntity

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private var commentList: MutableList<CommentEntity> = mutableListOf()
    private lateinit var layoutInflater: LayoutInflater
    private var callback: AdapterCallback? = null
    private var clicked = false

    interface AdapterCallback {
        fun onItemClicked(position: Int, commentEntity: CommentEntity, itemView: View)
    }

    fun adapterList(commentEntity: MutableList<CommentEntity>?, context: Context?, callback: AdapterCallback?){
        if (commentEntity != null) {
            this.commentList = commentEntity
        }

        if (callback != null) {
            this.callback = callback
        }

        if (context != null) {
            this.layoutInflater = LayoutInflater.from(context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(this.layoutInflater.inflate(R.layout.comment, parent, false))
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = commentList[position]
        val itemView = holder.itemView
        holder.bind(comment)

        //Avoid multiples clicks over item
        itemView.setOnClickListener {
            if (callback != null && !clicked){
                clicked = true
                Handler(Looper.getMainLooper()).postDelayed({
                    clicked = false
                }, 500)

                callback!!.onItemClicked(position, commentList[position], itemView)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvComment = view.findViewById(R.id.tvComment) as TextView
        private val tvCommentEmail = view.findViewById(R.id.tvCommentEmail) as TextView

        fun bind(commentEntity: CommentEntity){
            tvComment.text = commentEntity.body
            tvCommentEmail.text = commentEntity.email
        }
    }
}