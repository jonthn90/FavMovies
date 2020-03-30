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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import xyz.jonthn.favmovies.FavMoviesApp
import xyz.jonthn.favmovies.R
import xyz.jonthn.favmovies.model.data.Movie

class MoviesAdapter(
    private val favListener: (Movie, Int) -> Unit,
    private val detailListener: (Movie, Int) -> Unit
) : PagedListAdapter<Movie, MoviesAdapter.ViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindMovie(getItem(position)!!, position, favListener, detailListener)
        }
    }

    class ViewHolder(val binding: View) : RecyclerView.ViewHolder(binding) {

        fun bindMovie(
            movie: Movie,
            position: Int,
            favListener: (Movie, Int) -> Unit,
            detailListener: (Movie, Int) -> Unit
        ) {
            binding.textMovieTitle.text = movie.title
            val uri = Uri.parse("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            binding.imagePoster.setImageURI(uri, null)

            var resourceFav = R.drawable.ic_oscar_outline

            if (movie.isFav) {
                resourceFav = R.drawable.ic_oscar_fill
            }

            val hierarchy =
                GenericDraweeHierarchyBuilder.newInstance(FavMoviesApp.appContext!!.resources)
                    .setPlaceholderImage(resourceFav)
                    .build()
            binding.imageFavIcon.hierarchy = hierarchy

            binding.imageFavIcon.setOnClickListener {

                var resourceFav = R.drawable.ic_oscar_fill

                if (movie.isFav) {
                    resourceFav = R.drawable.ic_oscar_outline
                }

                val hierarchy =
                    GenericDraweeHierarchyBuilder.newInstance(FavMoviesApp.appContext!!.resources)
                        .setPlaceholderImage(resourceFav)
                        .build()
                binding.imageFavIcon.hierarchy = hierarchy

                favListener(movie, position)
            }

            binding.imagePoster.setOnClickListener {
                detailListener(movie, position)
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