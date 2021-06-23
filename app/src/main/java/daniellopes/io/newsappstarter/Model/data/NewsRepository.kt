package daniellopes.io.newsappstarter.Model.data

import daniellopes.io.newsappstarter.Model.db.ArticleDatabase
import daniellopes.io.newsappstarter.Model.entity.Article

class NewsRepository(private val db: ArticleDatabase) {

    suspend fun updateInsert(article: Article) = db.getArticle().updateInset(article)

    fun getAll(): List<Article> = db.getArticle().getAll()

    suspend fun delete(article: Article) = db.getArticle().delete(article)
}