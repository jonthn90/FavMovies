package xyz.jonthn.favmovies.view.fragments.favs

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import kotlinx.android.synthetic.main.item_movie.view.*
import xyz.jonthn.favmovies.FavMoviesApp
import xyz.jonthn.favmovies.R
import xyz.jonthn.favmovies.databinding.ItemMovieBinding
import xyz.jonthn.favmovies.model.data.Movie

class FavsAdapter(
    private val favListener: (Int) -> Unit,
    private val detailListener: (Movie) -> Unit
) :
    RecyclerView.Adapter<FavsAdapter.ViewHolder>() {

    private val moviesList = AsyncListDiffer(this, ITEM_COMPARATOR)

    fun submitList(list: List<Movie>) {
        moviesList.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return moviesList.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(moviesList.currentList.get(position), favListener, detailListener)
    }

    class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, favListener: (Int) -> Unit, detailListener: (Movie) -> Unit) {

            binding.textMovieTitle.text = movie.title
            val uri = Uri.parse("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            binding.imagePoster.setImageURI(uri, null)

            val hierarchy =
                GenericDraweeHierarchyBuilder.newInstance(FavMoviesApp.appContext!!.resources)
                    .setPlaceholderImage(R.drawable.ic_oscar_fill).build()

            binding.imageFavIcon.hierarchy = hierarchy

            binding.imageFavIcon.setOnClickListener {
                favListener(movie.id)
            }

            binding.imagePoster.setOnClickListener {
                detailListener(movie)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
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
