package daniellopes.io.newsappstarter.presenter.search

import daniellopes.io.newsappstarter.Model.data.NewsDataSource
import daniellopes.io.newsappstarter.Model.entity.NewsResponse
import daniellopes.io.newsappstarter.presenter.ViewHome

class SearchPresenter(
    val view : ViewHome.View,
    private val dataSource: NewsDataSource)
    :SeachHome.Presenter{

    override fun search(term: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }

    override fun onComplete() {
        TODO("Not yet implemented")
    }
}