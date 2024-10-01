package com.bignerdranch.android.crime

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bignerdranch.android.crime.databinding.FragmentCrimeDetailBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
//private const val TAG = "crimeDetailFragment"
class CrimeDetailFragment : Fragment() {
    private var _binding: FragmentCrimeDetailBinding? = null
    private val binding
        get()= checkNotNull(_binding){
            "error: can we see the view?"
        }
    //private lateinit var crime : CrimeDetailFragment
    // private lateinit var crime : Crime
    private val args: CrimeDetailFragmentArgs by navArgs()

    private val crimeDetailViewModel : CrimeDetailViewModel by viewModels {
        CrimeDetailViewModelFactory(args.crimeId)
    }
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // no inflating the view, this is job of onCreateView
        crime = Crime(
            id = UUID.randomUUID(),
            title = "",
            date = Date(),
            isSolved = false
        )
        Log.d(TAG, "The crime ID is ${args.crimeId}")
    }

     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCrimeDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            crimeTitle.doOnTextChanged { text, _, _, _ ->
                //crime = crime.copy(title = text.toString())
                crimeDetailViewModel.updateCrime{oldCrime ->
                    oldCrime.copy(title = text.toString())
                }
            }
            // wire button
            /*
            crimeDate.apply {
                //text = crime.date.toString()
                isEnabled = false
            }

             */
            // wire up check box
            crimeSolved.setOnCheckedChangeListener{_, isChecked ->
                //crime = crime.copy(isSolved = isChecked)
                crimeDetailViewModel.updateCrime {oldCrime ->
                    oldCrime.copy(isSolved = isChecked)
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                    crimeDetailViewModel.crime.collect(){crime ->
                        crime?.let { updateUi(it) }
                    }
                }
            }
        }
        setFragmentResultListener(
            DatePickerFragment.REQUEST_KEY_DATE
        ){ _, bundle ->
            val newDate =
                bundle.getSerializable(DatePickerFragment.BUNDLE_KEY_DATE) as Date
            crimeDetailViewModel.updateCrime { it.copy(date = newDate) }

            }
    }//end onViewCreated
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun updateUi(crime: Crime){
        binding.apply {
            if (crimeTitle.text.toString() != crime.title){
                crimeTitle.setText(crime.title)
            }
            crimeDate.text=crime.date.toString()
            crimeSolved.isChecked=crime.isSolved
            crimeDate.setOnClickListener{
                findNavController().navigate(
                    CrimeDetailFragmentDirections.selectDate(crime.date)
                )
            }
        }
    }
}