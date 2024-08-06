package mohsin.code.apiwithroomdb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import mohsin.code.apiwithroomdb.R

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // default passed to text view for details screen
        val songName = arguments?.getString("songName")
        val singerName = arguments?.getString("singerName")

        val tvSongNameDetails: TextView = view.findViewById(R.id.tvSongNameDetails)
        val tvSingerNameDetails: TextView = view.findViewById(R.id.tvSingerNameDetails)

        //passed song name and singer name
        tvSongNameDetails.text = songName
        tvSingerNameDetails.text = singerName


        //Back to Home Screen
        val button: Button = view.findViewById(R.id.btnBackToHome)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }
    }
}