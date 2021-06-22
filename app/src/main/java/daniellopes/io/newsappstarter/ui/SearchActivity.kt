package daniellopes.io.newsappstarter.ui

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import daniellopes.io.newsappstarter.Model.data.NewsDataSource
import daniellopes.io.newsappstarter.Model.entity.Article
import daniellopes.io.newsappstarter.R
import daniellopes.io.newsappstarter.adapter.MainAdapter
import daniellopes.io.newsappstarter.presenter.ViewHome
import daniellopes.io.newsappstarter.presenter.search.SearchPresenter
import daniellopes.io.newsappstarter.utils.UtilQueryTextListener
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AbstractActivity(), ViewHome.View{
    override fun getLayout(): Int =  R.layout.activity_search
    private val mainAdapter by lazy { MainAdapter() }
    private lateinit var presenter: SearchPresenter

    override fun onInject() {
        val dataSource = NewsDataSource()
        presenter = SearchPresenter(this,dataSource)
        configRecycle()
        search()
    }

    fun search(){
        searchNewsID.setOnQueryTextListener(
            UtilQueryTextListener(
                this.lifecycle
            ){ newText ->
                newText?.let { query ->
                    if(query.isNotEmpty()){
                        presenter.search(query)
                        rvProgressBarSearchID.visibility = View.VISIBLE
                    }
                }

            }
        )
    }

    fun configRecycle(){
        with(rvSearchNewsID){
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(DividerItemDecoration(this@SearchActivity,DividerItemDecoration.VERTICAL))
        }
    }

    override fun showProgressBar() {
        rvProgressBarSearchID.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        rvProgressBarSearchID.visibility = View.INVISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun showArticles(artitles: List<Article>) {
        mainAdapter.differ.submitList(artitles.toList())
    }
}