package com.zemoga.mobiletest.ui.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zemoga.mobiletest.R
import com.zemoga.mobiletest.ui.data.PostModel

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private var postModel: MutableList<PostModel> = ArrayList()
    private lateinit var layoutInflater: LayoutInflater
    private var callback: AdapterCallback? = null
    private var clicked = false

    interface AdapterCallback {
        fun onItemClicked(position: Int, menuModel: PostModel, itemView: View)
    }

    fun adapterListOptionMenu(menuModel: MutableList<PostModel>?, context: Context?, callback: AdapterCallback?){
        if (menuModel != null) {
            this.postModel = menuModel
        }

        if (callback != null) {
            this.callback = callback
        }

        if (context != null) {
            this.layoutInflater = LayoutInflater.from(context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(this.layoutInflater.inflate(R.layout.post, parent, false))
    }

    override fun getItemCount(): Int {
        return postModel.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = postModel[position]
        val itemView = holder.itemView
        holder.bind(item)

        //Avoid multiples clicks over item
        itemView.setOnClickListener {
            if (callback != null && !clicked){
                clicked = true
                Handler(Looper.getMainLooper()).postDelayed({
                    clicked = false
                }, 500)

                callback!!.onItemClicked(position, postModel[position], itemView)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivIndicator = view.findViewById(R.id.ivIndicator) as ImageView
        private val ivFavorite = view.findViewById(R.id.ivFavorite) as ImageView
        private val tvPost = view.findViewById(R.id.tvPost) as TextView

        fun bind(post: PostModel){

            if(post.indicator)
                ivIndicator.visibility = VISIBLE
            else
                ivIndicator.visibility = INVISIBLE

            if(post.favorite)
                ivFavorite.visibility = VISIBLE
            else
                ivFavorite.visibility = INVISIBLE

            tvPost.text = post.text
        }
    }
}