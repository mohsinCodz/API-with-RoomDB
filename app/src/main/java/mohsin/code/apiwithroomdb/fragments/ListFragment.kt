package mohsin.code.apiwithroomdb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mohsin.code.apiwithroomdb.MyApplication
import mohsin.code.apiwithroomdb.R
import mohsin.code.apiwithroomdb.adapters.ItemAdapter
import mohsin.code.apiwithroomdb.api.ApiInterface
import mohsin.code.apiwithroomdb.api.ApiUtilities
import mohsin.code.apiwithroomdb.model.Drink
import mohsin.code.apiwithroomdb.repository.DrinkRepository
import mohsin.code.apiwithroomdb.viewmodel.DrinksViewModel
import mohsin.code.apiwithroomdb.viewmodel.DrinksViewModelFactory
import java.util.Locale

class ListFragment : Fragment() {

    private var mList = ArrayList<Drink>()
    private lateinit var adapter: ItemAdapter

    private lateinit var drinksViewModel: DrinksViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize mList with actual data


        // Initialize the adapter and set it to the fragment's adapter property
        adapter = ItemAdapter(mList) { drink ->
            // Handle item click
            val bundle = Bundle().apply {
                putString("idDrink", drink.idDrink)
                putString("strDrink", drink.strDrink)
            }
            findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }

        val rvItem: RecyclerView = view.findViewById(R.id.rvItem)
        rvItem.layoutManager = LinearLayoutManager(context)
        rvItem.adapter = adapter

        val searchView: SearchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterText(newText)
                return true
            }
        })

        val repository = (requireActivity().application as MyApplication).drinkRepository

        drinksViewModel = ViewModelProvider(this, DrinksViewModelFactory(repository)).get(DrinksViewModel::class.java)

        drinksViewModel.drinks.observe(viewLifecycleOwner) { drinks ->
            //Log.d("Mohsin", drinks.toString())

            mList.addAll(drinks)
            adapter.notifyDataSetChanged()

        }
    }

    private fun filterText(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<Drink>()
            for (i in mList) {
                if (i.idDrink.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))
                    || i.strDrink.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }
}