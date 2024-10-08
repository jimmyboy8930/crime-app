package com.bignerdranch.android.crime

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.crime.databinding.FragmentCrimeListBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

private const val TAG="CrimeListFragment"
class CrimeListFragment : Fragment (){
    private val crimeListViewModel : CrimeListViewModel by viewModels()
    private var _binding: FragmentCrimeListBinding? = null
    private val binding
        get()= checkNotNull(_binding){
            "error: can we see the view?"
        }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d(TAG, "Total crimes: ${crimeListViewModel.crimes.size}")
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCrimeListBinding.inflate(layoutInflater, container, false)
        binding.crimeRecyclerView.layoutManager = LinearLayoutManager(context)
//        val crimes = crimeListViewModel.crimes
//        val adapter = CrimeListAdapter(crimes)
//        binding.crimeRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.fragment_crime_list, menu)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId){
                    R.id.new_crime -> {
                        showNewCrime()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                //val crimes = crimeListViewModel.loadCrimes()
                crimeListViewModel.crimes.collect(){ crimes ->
                    binding.crimeRecyclerView.adapter = CrimeListAdapter(crimes){ crimeId ->
                        findNavController().navigate(
                                CrimeListFragmentDirections.showCrimeDetail(crimeId)
                        //R.id.show_crime_detail
                        )
                    }
                }

            }
        }
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //setHasOptionsMenu(true)
//
//    }
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        //super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.fragment_crime_list, menu)
//    }
//
//
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when(item.itemId){
//            R.id.new_crime -> {
//                showNewCrime()
//                true
//            }
//            else -> super.onContextItemSelected(item)
//        }
//    }

    private fun showNewCrime(){
        viewLifecycleOwner.lifecycleScope.launch {
            val newCrime = Crime(
                id = UUID.randomUUID(),
                title="",
                date = Date(),
                isSolved = false
            )
            crimeListViewModel.addCrime(newCrime)
            findNavController().navigate(
                CrimeListFragmentDirections.showCrimeDetail(newCrime.id)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
