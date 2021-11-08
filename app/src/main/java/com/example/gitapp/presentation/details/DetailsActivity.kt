package com.example.gitapp.presentation.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gitapp.R
import com.example.gitapp.domain.entity.Repository

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        val repositorySelected = intent.getSerializableExtra("repositorySelected") as? Repository

        if (savedInstanceState == null) {
            repositorySelected?.let { DetailsFragment.newInstance(it) }?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, it)
                    .commitNow()
            }
        }
    }
}