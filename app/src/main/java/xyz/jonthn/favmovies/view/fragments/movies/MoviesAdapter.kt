package xyz.jonthn.favmovies.view.fragments.movies

import android.content.ClipData
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import kotlinx.android.synthetic.main.item_movie.view.*
import xyz.jonthn.favmovies.FavMoviesApp
import xyz.jonthn.favmovies.R
import xyz.jonthn.favmovies.model.data.Movie

class MoviesAdapter() : PagedListAdapter<Movie, MoviesAdapter.ViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindMovie(it)
        }
    }

    class ViewHolder(val binding: View) : RecyclerView.ViewHolder(binding) {

        fun bindMovie(movie: Movie) {
            binding.textMovieTitle.text = movie.title
            val uri = Uri.parse("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            binding.imagePoster.setImageURI(uri, null)

            binding.imageFavIcon.setOnClickListener {

                val hierarchy =
                    GenericDraweeHierarchyBuilder.newInstance(FavMoviesApp.appContext!!.resources)
                        .setPlaceholderImage(R.drawable.ic_oscar_fill)
                        .build()
                binding.imageFavIcon.hierarchy = hierarchy
            }

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = layoutInflater.inflate(R.layout.item_movie, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}