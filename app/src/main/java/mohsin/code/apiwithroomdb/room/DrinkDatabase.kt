package mohsin.code.apiwithroomdb.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mohsin.code.apiwithroomdb.model.Drink

@Database(entities = [Drink::class], version = 1)
abstract class DrinkDatabase : RoomDatabase(){

    abstract fun drinkDao(): RoomDao

    companion object{
        private var INSTANCE : DrinkDatabase? = null

        fun getDatabase(context: Context): DrinkDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    DrinkDatabase::class.java,
                    "drinksDB"
                ).build()
            }

            return INSTANCE!!
        }
    }
}