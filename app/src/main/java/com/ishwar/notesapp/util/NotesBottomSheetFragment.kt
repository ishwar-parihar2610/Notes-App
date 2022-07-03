package com.ishwar.notesapp.util

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ishwar.notesapp.R
import com.ishwar.notesapp.databinding.FragmentNotesBottomSheetBinding

class NotesBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNotesBottomSheetBinding
    var selectedColor = "#171C2C"


    companion object{
        fun newInstance():NotesBottomSheetFragment{
            val args=Bundle()
            val fragment=NotesBottomSheetFragment()
            fragment.arguments=args
            return fragment
        }
    }
    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)

        val view = LayoutInflater.from(context).inflate(R.layout.fragment_notes_bottom_sheet, null)

        dialog.setContentView(view)

        val param = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = param.behavior
        if (behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    var state = ""
                    when (newState) {
                        BottomSheetBehavior.STATE_DRAGGING -> {
                            state = "DRAGGING"
                        }
                        BottomSheetBehavior.STATE_SETTLING -> {
                            state = "SETTLING"
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            state = "EXPANDED"
                        }
                        BottomSheetBehavior.STATE_HIDDEN -> {
                            state = "HIDDEN"
                            dismiss()
                            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }

            })
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_notes_bottom_sheet,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {


        binding.fNote1.setOnClickListener {
            binding.imgNote.setImageResource(R.drawable.ic_tick)
            binding.imgNote2.setImageResource(0)
            binding.imgNote4.setImageResource(0)
            binding.imgNote5.setImageResource(0)
            binding.imgNote6.setImageResource(0)
            binding.imgNote7.setImageResource(0)
            selectedColor = "#4e33ff"
            val intent = Intent("bottom_sheet_action")
            intent.putExtra("actionColor", selectedColor)
            intent.putExtra("selectedColor", "Blue")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            dismiss()


        }
        binding.fNote2.setOnClickListener {

            binding.imgNote.setImageResource(0)
            binding.imgNote2.setImageResource(R.drawable.ic_tick)
            binding.imgNote4.setImageResource(0)
            binding.imgNote5.setImageResource(0)
            binding.imgNote6.setImageResource(0)
            binding.imgNote7.setImageResource(0)
            selectedColor = "#ffd633"

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("actionColor", selectedColor)
            intent.putExtra("selectedColor", "Yellow")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            dismiss()


        }
        binding.fNote4.setOnClickListener {
            binding.imgNote.setImageResource(0)
            binding.imgNote2.setImageResource(0)
            binding.imgNote4.setImageResource(R.drawable.ic_tick)
            binding.imgNote5.setImageResource(0)
            binding.imgNote6.setImageResource(0)
            binding.imgNote7.setImageResource(0)
            selectedColor = "#ae3b70"
            val intent = Intent("bottom_sheet_action")
            intent.putExtra("actionColor", selectedColor)
            intent.putExtra("selectedColor", "purple")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            dismiss()
        }

        binding.fNote5.setOnClickListener {
            binding.imgNote.setImageResource(0)
            binding.imgNote2.setImageResource(0)
            binding.imgNote4.setImageResource(0)
            binding.imgNote5.setImageResource(R.drawable.ic_tick)
            binding.imgNote6.setImageResource(0)
            binding.imgNote7.setImageResource(0)
            selectedColor = "#0aebaf"
            val intent = Intent("bottom_sheet_action")
            intent.putExtra("actionColor", selectedColor)
            intent.putExtra("selectedColor", "green")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            dismiss()
        }
        binding.fNote6.setOnClickListener {
            binding.imgNote.setImageResource(0)
            binding.imgNote2.setImageResource(0)
            binding.imgNote4.setImageResource(0)
            binding.imgNote5.setImageResource(0)
            binding.imgNote6.setImageResource(R.drawable.ic_tick)
            binding.imgNote7.setImageResource(0)
            selectedColor = "#ff7746"
            val intent = Intent("bottom_sheet_action")
            intent.putExtra("actionColor", selectedColor)
            intent.putExtra("selectedColor", "orange")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            dismiss()
        }

        binding.fNote7.setOnClickListener {
            binding.imgNote.setImageResource(0)
            binding.imgNote2.setImageResource(0)
            binding.imgNote4.setImageResource(0)
            binding.imgNote5.setImageResource(0)
            binding.imgNote6.setImageResource(0)
            binding.imgNote7.setImageResource(R.drawable.ic_tick)
            selectedColor = "#202734"
            val intent = Intent("bottom_sheet_action")
            intent.putExtra("actionColor", selectedColor)
            intent.putExtra("selectedColor", "black")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            dismiss()
        }
        binding.layoutImage.setOnClickListener {
            val intent = Intent("bottom_sheet_action")
            intent.putExtra("selectedColor", "image")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            dismiss()
        }


    }
}