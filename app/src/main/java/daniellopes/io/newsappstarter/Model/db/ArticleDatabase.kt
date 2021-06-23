package daniellopes.io.newsappstarter.Model.db

import android.content.Context
import androidx.room.*
import daniellopes.io.newsappstarter.Model.entity.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDatabase :RoomDatabase() {

    abstract fun getArticle():ArticleDao

    companion object{

        @Volatile
        private var instance: ArticleDatabase? = null
        private val lock = Any()
        const val databaseName = "article_db.db"

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createdDatabase(context).also { articleDatabase ->
                instance = articleDatabase
            }
        }

        private fun createdDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ArticleDatabase::class.java,
                 databaseName
            ).build()

    }
}
