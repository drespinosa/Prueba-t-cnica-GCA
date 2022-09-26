package com.example.recyclerview.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerview.repository.MembersRepository

class ViewModelFactory(private val repo: MembersRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MembersRepository::class.java).newInstance(repo)
    }

}
