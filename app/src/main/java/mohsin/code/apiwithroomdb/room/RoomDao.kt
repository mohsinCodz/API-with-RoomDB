package mohsin.code.apiwithroomdb.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mohsin.code.apiwithroomdb.model.Drink

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrink(drinks: List<Drink>)

    @Query("SELECT * FROM drinks WHERE idDrink = :idDrink")
    suspend fun getDrinkById(idDrink: String): Drink?

    @Query("SELECT * FROM drinks")
    suspend fun getDrink(): List<Drink>
}