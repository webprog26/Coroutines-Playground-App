package com.webprog26.coroutinesplaygroundapp.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.webprog26.coroutinesplaygroundapp.R
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var usersList: List<User>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(user: User?) {
            if (user != null) {
                itemView.findViewById<TextView>(R.id.tvUserId).text = user.id.toString()
                itemView.findViewById<TextView>(R.id.tvUserName).text = user.name
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