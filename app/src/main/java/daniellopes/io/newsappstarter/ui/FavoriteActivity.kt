package daniellopes.io.newsappstarter.ui

import android.content.Intent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import daniellopes.io.newsappstarter.Model.data.NewsDataSource
import daniellopes.io.newsappstarter.Model.entity.Article
import daniellopes.io.newsappstarter.R
import daniellopes.io.newsappstarter.adapter.MainAdapter
import daniellopes.io.newsappstarter.presenter.ViewHome
import daniellopes.io.newsappstarter.presenter.favorite.FavoritePresenter
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AbstractActivity(),ViewHome.Favorite{
    override fun getLayout(): Int =  R.layout.activity_favorite
    private lateinit var presenter: FavoritePresenter
    private val mainAdapter by lazy { MainAdapter() }

    override fun onInject() {
        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this,dataSource)
        presenter.getAll()
        configAdapter()
        getItemAdapter()
    }


    private fun configAdapter(){
        with(rvFavorite){
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            addItemDecoration(DividerItemDecoration(this@FavoriteActivity,DividerItemDecoration.VERTICAL))
        }
    }

    private fun getItemAdapter(){
        mainAdapter.setOnClickListener {
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", it)
            startActivity(intent)
        }
    }

    override fun showArticles(artitles: List<Article>) {
        mainAdapter.differ.submitList(artitles.toList())
    }
}
