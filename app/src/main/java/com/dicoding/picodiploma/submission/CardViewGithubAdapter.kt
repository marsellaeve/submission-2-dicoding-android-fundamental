package com.dicoding.picodiploma.submission

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*
import kotlin.collections.ArrayList

var userFilterList = ArrayList<Github>()
class CardViewGithubAdapter(private val parent:Activity,private val listUser:ArrayList<Github>) : RecyclerView.Adapter<CardViewGithubAdapter.CardViewViewHolder>(), Filterable {
    init {
        userFilterList = listUser
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
        return listUser.size
    }

    override fun onBindViewHolder(holder: CardViewGithubAdapter.CardViewViewHolder, position: Int) {
        val user = listUser[position]
        Glide.with(holder.itemView.context)
                .load(user.avatar)
                .apply(RequestOptions().override(300, 200))
                .into(holder.imgPhoto)
        holder.tvUsername.text = user.username
        holder.tvName.text = user.name
        holder.tvLocation.text = user.location
        holder.itemView.setOnClickListener { Toast.makeText(holder.itemView.context, listUser[holder.adapterPosition].username, Toast.LENGTH_SHORT).show() }
        holder.itemView.setOnClickListener {
            val dataUser = Github(
                    user.username,
                    user.name,
                    user.avatar,
                    user.follower,
                    user.following,
                    user.company,
                    user.location,
                    user.repository
            )
            Toast.makeText(parent, listUser[position].username, Toast.LENGTH_SHORT).show()
            val myIntent = Intent(parent, DetailActivity::class.java)
            myIntent.putExtra(DetailActivity.EXTRA_USER,dataUser)

            parent.startActivity(myIntent)
        }
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charSearch = constraint.toString()
                userFilterList = if (charSearch.isEmpty()) {
                    listUser
                } else {
                    val resultList = ArrayList<Github>()
                    for (row in userFilterList) {
                        if ((row.username.toString().toLowerCase(Locale.ROOT)
                                        .contains(charSearch.toLowerCase(Locale.ROOT)))
                        ) {
                            resultList.add(
                                    Github(
                                            row.username,
                                            row.name,
                                            row.avatar,
                                            row.follower,
                                            row.following,
                                            row.company,
                                            row.location,
                                            row.repository
                                    )
                            )
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = userFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                userFilterList = results.values as ArrayList<Github>
                notifyDataSetChanged()
            }
        }
    }
}