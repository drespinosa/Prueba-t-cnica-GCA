package com.example.recyclerview.view_model

import androidx.lifecycle.ViewModel
import com.example.recyclerview.objects.Members
import com.example.recyclerview.repository.MembersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelMembers(private val repository: MembersRepository) : ViewModel() {

    init {
        validateData()
    }

    /**
     *  Obtiene la lista de miembros del repositorio
     */
    fun getLocalMembers(): List<Members>? {
        return repository.getMembers()
    }

    /**
     *  Obtiene la lista de detalles filtrando por el id de cada miembro del repositorio
     */
    fun getLocalGiftById(memberId: Int): Members? {
        return repository.getDetailById(memberId)
    }

    /**
     *  Lanza una exepción si ocurre algún error en validateData
     */
    fun validateData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.validateData()
            } catch (e: Exception) {
            }
        }
    }

}
