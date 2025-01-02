package com.webprog26.coroutinesplaygroundapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.webprog26.coroutinesplaygroundapp.users.UsersListActivity

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var mBtnUsersListActivity: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        this.mBtnUsersListActivity = findViewById(R.id.btn_start_users_list_activity)
        mBtnUsersListActivity.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_start_users_list_activity -> {
                startActivity(
                    Intent(this, UsersListActivity::class.java)
                )
            }
        }
    }
}