package com.girogevoro.material_design.screens.system

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.girogevoro.material_design.databinding.FragmentSystemBinding
import java.util.function.BiConsumer


class SystemFragment : Fragment() {

    private var _binding: FragmentSystemBinding? = null
    private val binding: FragmentSystemBinding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[SystemViewModel::class.java]
    }
    private val recyclerAdapter by lazy {
        RecyclerAdapter(listOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSystemBinding.inflate(inflater)


        // FragmentManager.
        return binding.root
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recycler.adapter = recyclerAdapter

        viewModel.getLiveDataListDataUser().observe(viewLifecycleOwner) {
            recyclerAdapter.setList(it)
            recyclerAdapter.notifyDataSetChanged()
        }
        viewModel.refresh()

        recyclerAdapter.onConsumerActionNote =
            BiConsumer<Int, RecyclerAdapter.TypeActionNote> { position, type ->
                when (type) {
                    RecyclerAdapter.TypeActionNote.UP -> {
                        viewModel.moveData(position, position - 1)
                    }
                    RecyclerAdapter.TypeActionNote.DOWN -> {
                        viewModel.moveData(position, position + 1)
                    }
                    RecyclerAdapter.TypeActionNote.REMOVE -> {
                        viewModel.remove(position)
                    }
                    else -> {
                    }
                }
            }

        binding.add.setOnClickListener {
            EditDialogFragment.show("", parentFragmentManager, this) {
                viewModel.addData(1, DataUser.Note(0, "", it))
            }
        }

    }


    companion object {

        @JvmStatic
        fun newInstance() =
            SystemFragment()
    }
}