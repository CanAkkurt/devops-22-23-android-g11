package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.auth0.android.Auth0
import com.example.test.databinding.FragmentHomeBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var account: Auth0

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)





        return binding.root
    }







    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {







        super.onViewCreated(view, savedInstanceState)

        val chart: LineChart = view.findViewById(R.id.chart)

        val entries = mutableListOf<Entry>()
        entries.add(Entry(0f, 4f))
        entries.add(Entry(1f, 8f))
        entries.add(Entry(2f, 6f))
        entries.add(Entry(3f, 2f))
        // Add more data points as needed

        val dataSet = LineDataSet(entries, "My Data Set")
        val lineData = LineData(dataSet)
        chart.data = lineData






        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_listUsersFragment)
        }
        binding.goToListButton.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_virtualMachineListFragment)
        }
        binding.goToListProjectsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_projectListFragment)
        }


    }
}