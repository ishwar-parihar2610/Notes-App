package com.ishwar.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ishwar.notesapp.databinding.ActivityMainBinding
import com.ishwar.notesapp.fragment.HomeFragment
import com.ishwar.notesapp.util.Constant

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var constant: Constant
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_main)
        constant= Constant()
        replaceFragment(HomeFragment.newInstance(),true)

    }

    fun replaceFragment(fragment: Fragment,isTransition:Boolean){
        Log.d(constant.tag, "replaceFragment: call function ")
        val fragmentTransition=supportFragmentManager.beginTransaction()
        if(isTransition){
           fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransition.replace(R.id.frame_layout,fragment).addToBackStack(fragment.javaClass.simpleName).commit()
    }
}


