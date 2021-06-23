package daniellopes.io.newsappstarter.Model.db

import androidx.room.*
import daniellopes.io.newsappstarter.Model.entity.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInset(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAll(): List<Article>

    @Delete
    suspend fun delete(article: Article)
}