package daniellopes.io.newsappstarter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import daniellopes.io.newsappstarter.Model.entity.Article
import daniellopes.io.newsappstarter.R
import kotlinx.android.synthetic.main.item_news.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ArticleViewHolder>(){


    private val differCallBack = object : DiffUtil.ItemCallback<Article>(){

        //TODO essa função verifica se os dois representam o mesmo objeto
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {

            return oldItem.url == newItem.url
        }
        //TODO verifca se os dois objetos possuem os mesmos dados
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    //TODO vou usar esse cara pra chamar a lista criada acima, em uma thread separada
    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent,false)
        )

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(article.url).into(ivArticleImage)
            textViewTitle.text = article.title ?: article.source?.name
            tvSource.text = article.source?.name?: article.author
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
        }

        setOnClickListener {
            onItemClickListener?.let {
                it(article)
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnClickListener(listenter: (Article) ->Unit){
        onItemClickListener = listenter
    }
    inner class ArticleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }
}