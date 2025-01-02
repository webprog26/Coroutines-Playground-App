package com.webprog26.coroutinesplaygroundapp.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.webprog26.coroutinesplaygroundapp.R
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User
import java.lang.StringBuilder

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var usersList: List<User>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(user: User?) {
            if (user != null) {
                itemView.findViewById<TextView>(R.id.tv_user_name).text = user.username
                itemView.findViewById<TextView>(R.id.tv_name).text = user.name

                val userAvatarUrlBuilder = StringBuilder()
                userAvatarUrlBuilder.append("https://api.dicebear.com/9.x/personas/png")
                userAvatarUrlBuilder.append("?seed=").append(user.name.substring(0, user.name.indexOf(" ")))
                    .append("?size=48")

                Glide.with(itemView.context)
                    .load(userAvatarUrlBuilder.toString())
                    .into(itemView.findViewById(R.id.iv_user_avatar))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(usersList?.get(position))
    }

    override fun getItemCount(): Int {
        return usersList?.size ?: 0
    }
}