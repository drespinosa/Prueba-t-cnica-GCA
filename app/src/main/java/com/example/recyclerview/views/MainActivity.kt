package com.example.recyclerview.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview.R
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.views.fragments.DetailFragment
import com.example.recyclerview.views.fragments.MembersFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val membersFragment = MembersFragment {
        val detailFragment = DetailFragment(it)
        val transactionFragment = supportFragmentManager.beginTransaction()
        transactionFragment.replace(R.id.fragmentContainer, detailFragment)
        transactionFragment.addToBackStack(null)
        transactionFragment.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, membersFragment)
        transaction.commit()
    }

}