package daniellopes.io.newsappstarter.presenter.favorite

import daniellopes.io.newsappstarter.Model.data.NewsDataSource
import daniellopes.io.newsappstarter.Model.entity.Article

class FavoritePresenter(
    private val dataSource: NewsDataSource) {

    fun saveArticle(article: Article){
        this.dataSource.saveArticle(article)
    }
}