package com.example.recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter =
            Adapter(Fragment.superheroList, { listItem -> onItemSelected(listItem) })
        binding.recyclerView.addItemDecoration(decoration)
    }

    fun onItemSelected(ListItem: ListItem) {
        Toast.makeText(this, ListItem.superhero, Toast.LENGTH_SHORT).show()

    }
}