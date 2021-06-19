package daniellopes.io.newsappstarter.activity.network

import daniellopes.io.newsappstarter.activity.Model.NewsResponse
import daniellopes.io.newsappstarter.activity.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {


    // QUERY que são parametros
    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("counttry")
        countryCode: String = "br",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse> //o response é o que vai ser devolvido, nesse caso, nossa classe NewsResponse

    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

}