package com.ishwar.notesapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ishwar.notesapp.R
import com.ishwar.notesapp.adapter.NotesAdapter
import com.ishwar.notesapp.database.NotesDatabase
import com.ishwar.notesapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        manager.isSmoothScrolling

        binding.recycleView.isNestedScrollingEnabled = false
        binding.recycleView.layoutManager = manager

        launch {
            context?.let {
                var notes = NotesDatabase.getDatabase(it).noteDao().getAllNotes()
                binding.recycleView.adapter = NotesAdapter(notes)


            }
        }
        binding.createNoteBtn.setOnClickListener {
            replaceFragment(CreateNoteFragment.newInstance(), true)
        }

    }

    fun replaceFragment(fragment: Fragment, isTransition: Boolean) {
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
}