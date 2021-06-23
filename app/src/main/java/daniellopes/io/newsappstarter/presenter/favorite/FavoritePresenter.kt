package daniellopes.io.newsappstarter.presenter.favorite

import daniellopes.io.newsappstarter.Model.data.NewsDataSource
import daniellopes.io.newsappstarter.Model.entity.Article
import daniellopes.io.newsappstarter.presenter.ViewHome

class FavoritePresenter(
    private val view: ViewHome.Favorite,
    private val dataSource: NewsDataSource): FavoriteHome.Presenter{

    fun saveArticle(article: Article){
        this.dataSource.saveArticle(article)
    }

    override fun onSucess(articles: List<Article>) {
        this.view.showArticles(articles)
    }

    fun getAll(){
        this.dataSource.getAllArticles(this)
    }

    fun deleleArticle(article: Article){
        this.dataSource.deleteArticle(article)
    }
}