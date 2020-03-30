package xyz.jonthn.favmovies.model.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import xyz.jonthn.favmovies.model.data.Movie

@Dao
interface MoviesDAO {

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM movie ORDER BY title")
    fun getAllLive(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Movie)

    @Delete
    suspend fun delete(item: Movie)

    @Query("DELETE FROM movie WHERE id LIKE :id")
    suspend fun deleteFavMovie(id: Int)

    @Query("DELETE FROM movie")
    suspend fun nukeTable()

    @Query("SELECT * FROM movie WHERE id LIKE :id")
    suspend fun getItemFromID(id: Int) : Movie
}