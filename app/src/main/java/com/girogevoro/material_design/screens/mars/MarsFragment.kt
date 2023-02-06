package com.girogevoro.material_design.screens.mars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.girogevoro.material_design.databinding.FragmentMarsBinding
import com.girogevoro.material_design.util.TIME_ANIMATION

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

        changeBounds()
    }

    private fun render(marsAppState: MarsAppState) {
        when (marsAppState) {
            is MarsAppState.Error -> {/*TODO()*/
            }
            MarsAppState.Loading -> {/*TODO()*/
            }
            is MarsAppState.Success -> {
                val uri = marsAppState.pictureMarsResponseData.photos[0].img_src
                binding.image.load(uri)
            }
        }
    }

    private var isFlagBounds = false
    private fun changeBounds() {
        binding.image.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                isFlagBounds = !isFlagBounds

                TransitionManager.beginDelayedTransition(binding.root, TransitionSet().apply {
                    ordering = TransitionSet.ORDERING_TOGETHER
                    addTransition(ChangeBounds())
                    addTransition(ChangeImageTransform())
                    duration = TIME_ANIMATION
                })

                val params = binding.image.layoutParams as ViewGroup.LayoutParams
                params.width = if (isFlagBounds) ViewGroup.LayoutParams.MATCH_PARENT else
                    ViewGroup.LayoutParams.WRAP_CONTENT
                binding.image.layoutParams = params



                binding.image.scaleType = if (isFlagBounds) ImageView.ScaleType.CENTER_CROP else
                    ImageView.ScaleType.FIT_CENTER
            }


        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = MarsFragment()
    }
}