package com.girogevoro.material_design.screens.mars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.girogevoro.material_design.databinding.FragmentMarsBinding

class MarsFragment : Fragment() {
    private var _binding: FragmentMarsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[MarsViewModel::class.java]
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            render(it)
        }

        viewModel.request("2016-6-25")
    }

    private fun render(marsAppState: MarsAppState) {
        when (marsAppState) {
            is MarsAppState.Error -> {/*TODO()*/}
            MarsAppState.Loading -> {/*TODO()*/}
            is MarsAppState.Success -> {
                val uri = marsAppState.pictureMarsResponseData.photos[0].img_src
                binding.image.load(uri)
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = MarsFragment()
    }
}