package daniellopes.io.newsappstarter.presenter.news

import daniellopes.io.newsappstarter.Model.data.NewsDataSource
import daniellopes.io.newsappstarter.Model.entity.NewsResponse
import daniellopes.io.newsappstarter.presenter.ViewHome
import javax.sql.DataSource

class NewsPresenter(
    val view: ViewHome.View,
    val dataSource: NewsDataSource): NewsHome.Presenter {

    override fun requestAll() {
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