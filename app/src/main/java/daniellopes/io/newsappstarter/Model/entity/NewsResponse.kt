package daniellopes.io.newsappstarter.Model.entity

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)