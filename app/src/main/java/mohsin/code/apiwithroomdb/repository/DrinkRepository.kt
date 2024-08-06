package mohsin.code.apiwithroomdb.repository

import android.content.Context
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mohsin.code.apiwithroomdb.model.Drink
import mohsin.code.apiwithroomdb.api.ApiInterface
import mohsin.code.apiwithroomdb.model.Drinks
import mohsin.code.apiwithroomdb.room.DrinkDatabase
import mohsin.code.apiwithroomdb.util.MyUtil

class DrinkRepository(
    private val apiInterface: ApiInterface,
    private val drinkDatabase: DrinkDatabase,
    private val applicationContext: Context
) {

    private val drinksLiveData = MutableLiveData<List<Drink>>()

    val drinks: LiveData<List<Drink>>
        get() = drinksLiveData

    suspend fun getDrinks() {
        if(MyUtil.isInternetAvailable(applicationContext)){
            val result = apiInterface.getDrinks()
            if (result.drinks != null) {

                drinkDatabase.drinkDao().insertDrink(result.drinks)
                drinksLiveData.postValue(result.drinks)
            }
        }else{
            val drinks = drinkDatabase.drinkDao().getDrink()

            // Post the entire list to LiveData directly
            drinksLiveData.postValue(drinks)
        }
    }
}