package mohsin.code.apiwithroomdb.api

import mohsin.code.apiwithroomdb.model.Drinks
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/json/v1/1/search.php?s=margarita")
    suspend fun getDrinks(): Drinks
}