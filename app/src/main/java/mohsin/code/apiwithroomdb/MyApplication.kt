package mohsin.code.apiwithroomdb

import android.app.Application
import mohsin.code.apiwithroomdb.api.ApiInterface
import mohsin.code.apiwithroomdb.api.ApiUtilities
import mohsin.code.apiwithroomdb.repository.DrinkRepository
import mohsin.code.apiwithroomdb.room.DrinkDatabase

class MyApplication: Application() {

    lateinit var drinkRepository: DrinkRepository
    override fun onCreate() {
        super.onCreate()

        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

        val database = DrinkDatabase.getDatabase(applicationContext)

        drinkRepository = DrinkRepository(apiInterface, database, applicationContext)

    }
}