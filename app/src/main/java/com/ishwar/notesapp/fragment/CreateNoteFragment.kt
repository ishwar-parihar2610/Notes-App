package com.ishwar.notesapp.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.ishwar.notesapp.R
import com.ishwar.notesapp.database.NotesDatabase
import com.ishwar.notesapp.databinding.CreateNoteFragmentBinding
import com.ishwar.notesapp.entities.Notes
import com.ishwar.notesapp.util.Constant
import com.ishwar.notesapp.util.NotesBottomSheetFragment
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest


class CreateNoteFragment : BaseFragment(), EasyPermissions.PermissionCallbacks,
    EasyPermissions.RationaleCallbacks {
    private lateinit var binding: CreateNoteFragmentBinding
    private var currentDate: String? = null
    private var READ_STORAGE_PERM = 123
    private var WRITE_STORAGE_PERM = 123
    var selectedColor = "#171C2C"

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
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            broadCastReceiver,
            IntentFilter("bottom_sheet_action")
        )

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        currentDate = sdf.format(Date())
        binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))
        binding.dateTimeTv.text = currentDate

        binding.imgDon.setOnClickListener {
            saveNote()
        }

        binding.imgBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
//            replaceFragment(HomeFragment.newInstance(), false)
        }

        binding.imgMore.setOnClickListener {
            var notesBottomSheetFragment = NotesBottomSheetFragment.newInstance()
            notesBottomSheetFragment.show(
                requireActivity().supportFragmentManager,
                "Notes Bottom sheet Fragment"
            )
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
                notes.color = selectedColor
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

    private val broadCastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var actionColor = intent!!.getStringExtra("selectedColor")

            when (actionColor!!) {
                "Blue" -> {
                    selectedColor = intent.getStringExtra("actionColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
                "Yellow" -> {
                    selectedColor = intent.getStringExtra("actionColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
                "purple" -> {
                    selectedColor = intent.getStringExtra("actionColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
                "green" -> {
                    selectedColor = intent.getStringExtra("actionColor")!!
                    Log.d("Create New Fragment ", "onReceive: $selectedColor ")
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
                "orange" -> {

                    selectedColor = intent.getStringExtra("actionColor")!!
                    Log.d("Create New Fragment ", "onReceive: $selectedColor ")
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
                "black" -> {
                    selectedColor = intent.getStringExtra("actionColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
                "image" -> {
                    readStorageTask()
                }
                else -> {

                }

            }
        }

    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadCastReceiver)
        super.onDestroy()
    }

    private fun hasReadStoragePerm():Boolean{
    return  EasyPermissions.hasPermissions(requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }
    private fun hasReadWritePerm():Boolean{
        return  EasyPermissions.hasPermissions(requireContext(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    private fun readStorageTask(){
        if(hasReadStoragePerm()){
            Log.d("permission ", "readStorageTask: true ")
        Constant.showToast("Permission Granted",requireContext())
        }else{
            Log.d("permission ", "readStorageTask: false ")
            EasyPermissions.requestPermissions(
                requireActivity(),
                getString(R.string.storage_permission_text),
                READ_STORAGE_PERM,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
      if(EasyPermissions.somePermissionPermanentlyDenied(requireActivity(),perms)){
          AppSettingsDialog.Builder(requireActivity()).build().show()
      }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,requireActivity())
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun onRationaleAccepted(requestCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onRationaleDenied(requestCode: Int) {

    }

}