package daniellopes.io.newsappstarter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.google.android.material.snackbar.Snackbar
import daniellopes.io.newsappstarter.Model.data.NewsDataSource
import daniellopes.io.newsappstarter.Model.entity.Article
import daniellopes.io.newsappstarter.R
import daniellopes.io.newsappstarter.presenter.favorite.FavoritePresenter
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AbstractActivity() {
    override fun getLayout(): Int = R.layout.activity_article
    private lateinit var article: Article
    private var dataSource = NewsDataSource(this)
    private var presenter = FavoritePresenter(dataSource)

    override fun onInject() {
        extrasItemArticle()
        webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { url ->
                loadUrl(url)
            }
        }

        btnFab.setOnClickListener {
            presenter.saveArticle(article)
            Snackbar.make(it,R.string.article_saved_successful,Snackbar.LENGTH_LONG).show()
        }
    }

    private fun extrasItemArticle(){
      article = intent.extras?.get("article") as Article
    }

}