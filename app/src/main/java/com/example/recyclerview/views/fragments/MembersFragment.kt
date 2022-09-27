package com.example.recyclerview.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.data_base.MemberDatabase
import com.example.recyclerview.databinding.FragmentMembersBinding
import com.example.recyclerview.objects.Members
import com.example.recyclerview.repository.MembersRepository
import com.example.recyclerview.view_model.ViewModelFactory
import com.example.recyclerview.view_model.ViewModelMembers
import com.example.recyclerview.views.adapter.Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MembersFragment(val notifier: (memberId: Int) -> Unit) : Fragment() {

    private var _binding: FragmentMembersBinding? = null
    private val binding get() = _binding!!


    private lateinit var repository: MembersRepository
    private lateinit var membersViewModel: ViewModelMembers

    private var state: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMembersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val memberDatabase: MemberDatabase? =
            MemberDatabase.getDataInstance(this.requireActivity().application)

        repository = MembersRepository(memberDatabase)
        membersViewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(
            ViewModelMembers::class.java
        )

        loadData()
        binding.refresh.setOnClickListener {
            membersViewModel.validateData()
            loadData()
        }

        binding.organizeLastNameButton.visibility = View.VISIBLE

        binding.organizeLastNameButton.setOnClickListener {
            state = true
            membersViewModel.validateData()
            binding.organizeLastNameButton.visibility = View.GONE
            binding.organizeWorkButton.visibility = View.VISIBLE
            loadData()
        }

        binding.organizeWorkButton.setOnClickListener {
            state = false
            binding.organizeLastNameButton.visibility = View.VISIBLE
            binding.organizeWorkButton.visibility = View.GONE
            membersViewModel.validateData()
            loadData()
        }

    }

    /**
     * Inicializa el recyclerView
     */
    private fun initRecyclerView(membersList: List<Members>) {
        val manager = LinearLayoutManager(this.context)
        val decoration = DividerItemDecoration(this.context, manager.orientation)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = Adapter(membersList) { member -> notifier(member.id) }
        binding.recyclerView.addItemDecoration(decoration)
    }

    /**
     *  Si la lista de miembros está vacía se puede actualizar los datos
     */
    private fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            membersViewModel.getLocalMembers()?.let { members ->
                withContext(Dispatchers.Main) {
                    binding.refresh.isVisible = members.isEmpty()
                    initRecyclerView(members)
                    if (state) {
                        initRecyclerView(members.sortedBy { it.cargo })
                    } else {
                        initRecyclerView(members)
                    }
                }
            }
        }
    }

}