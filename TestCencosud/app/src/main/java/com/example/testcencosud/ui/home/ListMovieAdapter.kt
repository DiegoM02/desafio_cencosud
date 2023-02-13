package com.example.testcencosud.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testcencosud.R
import com.example.testcencosud.domain.model.Movie



class MovieListAdapter(
    val listMovies: List<Movie>,
    private val onClickListener: (movie: Movie) -> Unit

    ) : RecyclerView.Adapter<MoviesViewHolder>() {
    override fun getItemCount(): Int = listMovies.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MoviesViewHolder(layoutInflater.inflate(R.layout.movie_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = listMovies[position]
        holder.renderView(item, onClickListener)
    }


}

class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val image = view.findViewById<ImageView>(R.id.imageMovie)
    val title = view.findViewById<TextView>(R.id.titleMovie)
    val dateRelease = view.findViewById<TextView>(R.id.dateReleaseMovie)
    val cardView = view.findViewById<CardView>(R.id.cardView)

    fun renderView(movie: Movie, onClickListener: (movie: Movie) -> Unit) {
        Glide.with(itemView).load(movie.urlImage).into(image)
        title.setText(movie.title)
        dateRelease.setText(movie.dateRelease)
        cardView.setOnClickListener {
            onClickListener(movie)
        }
    }
}