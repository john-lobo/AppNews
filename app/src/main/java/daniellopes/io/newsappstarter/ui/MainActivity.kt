package daniellopes.io.newsappstarter.ui

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import daniellopes.io.newsappstarter.Model.data.NewsDataSource
import daniellopes.io.newsappstarter.Model.entity.Article
import daniellopes.io.newsappstarter.R
import daniellopes.io.newsappstarter.adapter.MainAdapter
import daniellopes.io.newsappstarter.presenter.ViewHome
import daniellopes.io.newsappstarter.presenter.news.NewsPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbstractActivity() ,ViewHome.View{
    override fun getLayout(): Int =  R.layout.activity_main

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter :NewsPresenter


    override fun onInject() {
       val dataSource = NewsDataSource()
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
        configRecycle()
        getItemAdapter()
    }

    private fun getItemAdapter(){
        mainAdapter.setOnClickListener {
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", it)
            startActivity(intent)
        }
    }

    private fun configRecycle(){
        with(rvNews){
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity,DividerItemDecoration.VERTICAL))
        }
    }
    override fun showProgressBar() {
        rvProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
       rvProgressBar.visibility = View.INVISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showArticles(artitles: List<Article>) {
        mainAdapter.differ.submitList(artitles.toList())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search_menu ->{
                Intent(this, SearchActivity::class.java).apply {
                    startActivity(this)
                }
            }
            R.id.favorite_menu ->{
                Intent(this,FavoriteActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
            return super.onOptionsItemSelected(item)
    }
}