package com.ishwar.notesapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ishwar.notesapp.R
import com.ishwar.notesapp.database.NotesDatabase
import com.ishwar.notesapp.databinding.CreateNoteFragmentBinding
import com.ishwar.notesapp.entities.Notes
import com.ishwar.notesapp.util.Constant
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CreateNoteFragment : BaseFragment() {
    private lateinit var binding: CreateNoteFragmentBinding
    private var currentDate: String? = null
    var selectedColor= "#171C2C"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.create_note_fragment, container, false)

        return binding.root
    }


    companion object {


        @JvmStatic
        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        currentDate = sdf.format(Date())
        binding.dateTimeTv.text = currentDate

        binding.imgDon.setOnClickListener {
            saveNote()
        }

        binding.imgBack.setOnClickListener {
            replaceFragment(HomeFragment.newInstance(), false)
        }
    }

    private fun replaceFragment(fragment: Fragment, isTransition: Boolean) {
        //  Log.d(constant.tag, "replaceFragment: call function ")
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        if (isTransition) {
            fragmentTransition.setCustomAnimations(
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left
            )
        }
        fragmentTransition.replace(R.id.frame_layout, fragment)
            .addToBackStack(fragment.javaClass.simpleName).commit()
    }


    private fun saveNote() {
        when {
            binding.notesTitleEdt.text.isNullOrBlank() -> {
                context?.let { Constant.showToast("Title Required", it) }
            }
            binding.subTitleEdt.text.isNullOrBlank() -> {
                context?.let { Constant.showToast("Note Sub Title Required", it) }
            }
            binding.edtNoteDes.text.isNullOrBlank() -> {
                context?.let { Constant.showToast("Description  Required", it) }
            }
            else -> launch {
                val notes = Notes()
                notes.title = binding.notesTitleEdt.text.toString()
                notes.subTitle = binding.subTitleEdt.text.toString()
                notes.noteText = binding.edtNoteDes.text.toString()
                notes.dateTime = currentDate
                context?.let {
                    NotesDatabase.getDatabase(it).noteDao().insertNotes(notes)
                    binding.notesTitleEdt.setText("")
                    binding.subTitleEdt.setText("")
                    binding.edtNoteDes.setText("")
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
        }







    }

}