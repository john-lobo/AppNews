package daniellopes.io.newsappstarter.presenter.search

import daniellopes.io.newsappstarter.Model.data.NewsDataSource
import daniellopes.io.newsappstarter.Model.entity.NewsResponse
import daniellopes.io.newsappstarter.presenter.ViewHome

class SearchPresenter(
    val view : ViewHome.View,
    private val dataSource: NewsDataSource)
    :SeachHome.Presenter{

    override fun search(term: String) {
        this.view.showProgressBar()
        this.dataSource.searchNews(term,this)
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        view.showArticles(newsResponse.articles)
    }

    override fun onError(message: String) {
       view.showFailure(message)
    }

    override fun onComplete() {
       view.hideProgressBar()
    }
}