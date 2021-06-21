package daniellopes.io.newsappstarter.presenter.news

import daniellopes.io.newsappstarter.Model.data.NewsDataSource
import daniellopes.io.newsappstarter.Model.entity.NewsResponse
import daniellopes.io.newsappstarter.presenter.ViewHome
import daniellopes.io.newsappstarter.presenter.search.SeachHome
import javax.sql.DataSource

class NewsPresenter(
    val view: ViewHome.View,
    val dataSource: NewsDataSource): NewsHome.Presenter {

    override fun requestAll() {
        this.view.showProgressBar()
        this.dataSource.getBreakingNews(this)
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        this.view.showArticles(newsResponse.articles)
    }

    override fun onError(message: String) {
       this.view.showFailure(message)
    }

    override fun onComplete() {
       this.view.hideProgressBar()
    }
}