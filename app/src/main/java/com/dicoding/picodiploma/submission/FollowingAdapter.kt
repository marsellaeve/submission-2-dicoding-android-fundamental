package com.dicoding.picodiploma.submission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


var followingFilterList = ArrayList<Github>()
class FollowingAdapter( private val listUser:ArrayList<Github>) : RecyclerView.Adapter<FollowingAdapter.CardViewViewHolder>() {
    init {
        followingFilterList = listUser
    }
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(dataUsers: Github)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_github, parent, false)
        return CardViewViewHolder(view)
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvUsername: TextView = itemView.findViewById(R.id.tv_item_username)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvLocation: TextView = itemView.findViewById(R.id.tv_item_location)
    }

    override fun getItemCount(): Int {
        return followingFilterList.size
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val user = followingFilterList[position]
        Glide.with(holder.itemView.context)
                .load(user.avatar)
                .apply(RequestOptions().override(300, 200))
                .into(holder.imgPhoto)
        holder.tvUsername.text = user.username
        holder.tvName.text = user.name
        holder.tvLocation.text = user.location
        holder.itemView.setOnClickListener {
        }
    }
}