package com.example.recyclerview.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.recyclerview.data_base.MemberDatabase
import com.example.recyclerview.databinding.FragmentDetailBinding
import com.example.recyclerview.repository.MembersRepository
import com.example.recyclerview.view_model.ViewModelFactory
import com.example.recyclerview.view_model.ViewModelMembers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailFragment(private val memberId: Int) : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


    private lateinit var repository: MembersRepository
    private lateinit var membersViewModel: ViewModelMembers

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
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

    }

    /**
     *  Carga los datos para el layout
     */
    private fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            membersViewModel.getLocalGiftById(memberId)?.let { member ->
                withContext(Dispatchers.Main) {
                    binding.nameTextView.text = member.nombre
                    binding.lastNameTextView.text = member.lastName
                    binding.workTextView.text = member.cargo
                    var gifts = ""
                    member.gifts?.forEach {
                        gifts += "\n » $it"
                    }
                    binding.giftList.text = gifts
                    Glide.with(binding.photoMemberImageView.context).load(member.urlFoto)
                        .into(binding.photoMemberImageView)
                }
            }
        }
    }

}