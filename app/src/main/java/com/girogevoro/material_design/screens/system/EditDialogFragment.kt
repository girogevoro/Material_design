package com.girogevoro.material_design.screens.system

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.core.util.Consumer
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.girogevoro.material_design.databinding.DialogFragmentEditBinding

class EditDialogFragment : DialogFragment() {

    companion object {
        private const val TAG = "EditDialogFragment"
        private const val ARG_EDIT = "EditText"
        private const val ARG_RESULT = "EditResult"

        @JvmStatic
        fun show(
            edit: String,
            fragmentManager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: Consumer<String>
        ): EditDialogFragment {
            fragmentManager.setFragmentResultListener(
                TAG, lifecycleOwner
            ) { key: String, bundleResult: Bundle ->
                if (key == TAG) {
                    listener.accept(bundleResult.getString(ARG_RESULT) ?: "")
                }
            }

            val args = bundleOf(ARG_EDIT to edit)
            return EditDialogFragment()
                .apply { arguments = args }
                .also {
                    it.show(fragmentManager, TAG)
                }
        }
    }


    private var _binding: DialogFragmentEditBinding? = null
    private val binding: DialogFragmentEditBinding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            setGravity(Gravity.TOP)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentEditBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewListener()
    }


    private fun setupViewListener() {
        binding.btnPositive.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                TAG,
                bundleOf(ARG_RESULT to (binding.input.editText?.text.toString() ?: ""))
            )
            dismiss()
        }
    }

    private fun setupView() {
        binding.input.editText?.setText(arguments?.getString(ARG_EDIT) ?: "")
    }


}