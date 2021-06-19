package daniellopes.io.newsappstarter.presenter

import daniellopes.io.newsappstarter.Model.entity.Article

interface ViewHome {

    interface View{
        fun showProgressBar()
        fun hideProgressBar()
        fun showFailure(message:String)
        fun showArticles(artitles : List<Article>)
    }
}