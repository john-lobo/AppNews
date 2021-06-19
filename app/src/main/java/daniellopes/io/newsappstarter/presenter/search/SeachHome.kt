package daniellopes.io.newsappstarter.presenter.search

import daniellopes.io.newsappstarter.Model.entity.NewsResponse

interface SeachHome {

    interface Presenter{
        fun search(term:String)
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()
    }
}