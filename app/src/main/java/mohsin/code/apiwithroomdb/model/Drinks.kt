package mohsin.code.apiwithroomdb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Drinks (
    @SerializedName("drinks")
    val drinks: ArrayList<Drink>
)

@Entity(tableName = "drinks")
data class Drink(
    @PrimaryKey
    @SerializedName("idDrink")
    val idDrink: String,
    @SerializedName("strDrink")
    val strDrink: String
)
