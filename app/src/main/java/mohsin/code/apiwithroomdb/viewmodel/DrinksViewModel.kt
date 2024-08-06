package mohsin.code.apiwithroomdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mohsin.code.apiwithroomdb.model.Drink
import mohsin.code.apiwithroomdb.repository.DrinkRepository

class DrinksViewModel (private val drinkRepository: DrinkRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            drinkRepository.getDrinks()
        }
    }

    val drinks: LiveData<List<Drink>>
        get() = drinkRepository.drinks
}