package com.example.gitapp.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapp.R
import com.example.gitapp.domain.entity.Repository
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class RecyclerViewRepositoiresAdapter(
    private val repositories: ArrayList<Repository>,
    private val listener: OnRepositoryClickListener
) : RecyclerView.Adapter<RecyclerViewRepositoiresAdapter.MyHolder>() {

    class MyHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val nameRepositoryTextView = view.findViewById<TextView>(R.id.nameRepositoryTextView)
        private val starsCountTextView = view.findViewById<TextView>(R.id.starsCountTextView)
        private val forkCountTextView = view.findViewById<TextView>(R.id.forkCountTextView)
        private val ownerImageView = view.findViewById<ShapeableImageView>(R.id.ownerImageView)
        private val ownerNameTextView = view.findViewById<TextView>(R.id.ownerNameTextView)

        fun bind(repository: Repository) {
            nameRepositoryTextView.text = repository.name
            starsCountTextView.text = repository.stargazersCount.toString()
            forkCountTextView.text = repository.forks.toString()
            ownerNameTextView.text = repository.owner.login

            val picasso = Picasso.Builder(view.context).listener { _, _, exception ->
                exception?.printStackTrace()
                println("Picasso loading failed : ${exception?.message}")
                ownerImageView.setImageResource(R.drawable.ic_launcher_background)
            }.build()

            picasso.load(repository.owner.avatar_url)
                .noFade()
                .fit().centerCrop()
                .into(ownerImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder =
        MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false))

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(repositories[position])
        holder.itemView.setOnClickListener {
            listener.onRepositoryClicked(repositories[position])
        }
    }
}