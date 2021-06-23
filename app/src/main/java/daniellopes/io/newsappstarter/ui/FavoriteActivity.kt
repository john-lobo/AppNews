package daniellopes.io.newsappstarter.ui

import android.content.Intent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import daniellopes.io.newsappstarter.Model.data.NewsDataSource
import daniellopes.io.newsappstarter.Model.entity.Article
import daniellopes.io.newsappstarter.R
import daniellopes.io.newsappstarter.adapter.MainAdapter
import daniellopes.io.newsappstarter.presenter.ViewHome
import daniellopes.io.newsappstarter.presenter.favorite.FavoritePresenter
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AbstractActivity(), ViewHome.Favorite {
    override fun getLayout(): Int = R.layout.activity_favorite
    private lateinit var presenter: FavoritePresenter
    private val mainAdapter by lazy { MainAdapter() }

    override fun onInject() {
        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this, dataSource)
        presenter.getAll()
        configAdapter()
        getItemAdapter()

        val itemTouchPerCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = mainAdapter.differ.currentList[position]
                presenter.deleleArticle(article)
                Snackbar.make(
                    viewHolder.itemView, R.string.article_delete_successful, Snackbar.LENGTH_SHORT
                ).apply {
                    setAction(getString(R.string.undo)) {
                        presenter.saveArticle(article)
                        mainAdapter.notifyDataSetChanged()
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchPerCallBack).apply {
            attachToRecyclerView(rvFavorite)
        }
        presenter.getAll()
    }


    private fun configAdapter() {
        with(rvFavorite) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@FavoriteActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun getItemAdapter() {
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
