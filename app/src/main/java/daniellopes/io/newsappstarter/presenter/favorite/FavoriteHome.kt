package daniellopes.io.newsappstarter.presenter.favorite

import daniellopes.io.newsappstarter.Model.entity.Article

interface FavoriteHome {

    interface Home{
        fun showArticles(articles :List<Article>)
    }
}