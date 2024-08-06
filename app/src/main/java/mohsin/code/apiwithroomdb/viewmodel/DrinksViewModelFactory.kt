package mohsin.code.apiwithroomdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mohsin.code.apiwithroomdb.repository.DrinkRepository

class DrinksViewModelFactory(private val drinksRepository: DrinkRepository) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DrinksViewModel(drinksRepository) as T
    }

}