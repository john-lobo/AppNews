package daniellopes.io.newsappstarter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import daniellopes.io.newsappstarter.Model.entity.Article
import daniellopes.io.newsappstarter.R
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AbstractActivity() {
    override fun getLayout(): Int = R.layout.activity_article
    private lateinit var article: Article

    override fun onInject() {
        extrasItemArticle()
        webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { url ->
                loadUrl(url)
            }
        }
    }

    private fun extrasItemArticle(){
      article = intent.extras?.get("article") as Article
    }
}