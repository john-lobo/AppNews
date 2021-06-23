package daniellopes.io.newsappstarter.presenter.favorite

import daniellopes.io.newsappstarter.Model.entity.Article

interface FavoriteHome {

    interface Presenter{
        fun onSucess(articles :List<Article>)
    }
}