package daniellopes.io.newsappstarter.Model.data

import daniellopes.io.newsappstarter.network.RetrofitInstance
import daniellopes.io.newsappstarter.presenter.news.NewsHome
import daniellopes.io.newsappstarter.presenter.search.SeachHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsDataSource {

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
}