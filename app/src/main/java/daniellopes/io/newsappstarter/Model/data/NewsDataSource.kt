package daniellopes.io.newsappstarter.Model.data

import android.content.Context
import daniellopes.io.newsappstarter.Model.db.ArticleDatabase
import daniellopes.io.newsappstarter.Model.entity.Article
import daniellopes.io.newsappstarter.network.RetrofitInstance
import daniellopes.io.newsappstarter.presenter.favorite.FavoriteHome
import daniellopes.io.newsappstarter.presenter.news.NewsHome
import daniellopes.io.newsappstarter.presenter.search.SeachHome
import kotlinx.coroutines.*

class NewsDataSource(context: Context) {

    private val db = ArticleDatabase(context)
    private val newsRepository = NewsRepository(db)

    fun getBreakingNews(callback: NewsHome.Presenter){
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getBreakingNews("br")
            if(response.isSuccessful){
                response.body()?.let { newsResponse ->
                    callback.onSuccess(newsResponse)
                }
                callback.onComplete()
            }else{
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }

  fun searchNews(term:String , callback: SeachHome.Presenter){
      GlobalScope.launch(Dispatchers.Main) {
          val response = RetrofitInstance.api.searchNews(term)
          if(response.isSuccessful){
              response.body()?.let { newsResponse ->
                  callback.onSuccess(newsResponse)
              }
              callback.onComplete()
          }else{
              callback.onError(response.message())
              callback.onComplete()
          }
      }
  }

    fun saveArticle(article: Article){
        GlobalScope.launch(Dispatchers.Main) {
            newsRepository.updateInsert(article)
        }
    }


    fun getAllArticles(callback: FavoriteHome.Presenter){
        var allArticles: List<Article>
        CoroutineScope(Dispatchers.IO).launch {
            allArticles = newsRepository.getAll()
            withContext(Dispatchers.Main){
                callback.onSucess(allArticles)
            }
        }
    }

    fun deleteArticle(article: Article?){
        GlobalScope.launch(Dispatchers.Main) {
            article?.let { articleDeleted ->
                newsRepository.delete(articleDeleted)
            }
        }
    }

}