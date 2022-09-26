package com.example.recyclerview.repository

import android.util.Log
import com.example.recyclerview.commons.toData
import com.example.recyclerview.commons.toLocal
import com.example.recyclerview.data_base.MemberDatabase
import com.example.recyclerview.objects.Members
import com.example.recyclerview.web_service.MembersApi

class MembersRepository(
    private val localData: MemberDatabase?
) {

    private val retrofitInstance = MembersApi.getRetrofitClient()

    /**
     * Obtiene los datos de la tabla MembersTable
     * Con los datos recolectados se construye el objeto de tipo Members
     */
    fun getMembers(): List<Members>? {
        val localMembers = localData?.memberDao?.getMembers()?.sortedBy { it.lastName }
        return localMembers?.map { memberTable ->
            memberTable.toLocal()
        }
    }

    /**
     * Obtiene el dato de los regalos de la tabla GiftTable dependiendo el ID de cada miembro del equipo
     * Dependiendo del miembro obtiene los datos correspondientes de la tabla MemberTable
     * Con los datos recolectados se construye el objeto de tipo Members
     */
    fun getDetailById(memberId: Int): Members? {
        val gifts = localData?.giftDao?.getMembersById(memberId)?.map { it.gift }
        val member = localData?.memberDao?.getMemberById(memberId)?.toLocal()
        member?.gifts = gifts
        return member
    }

    /**
     * Valida si la base de datos contiene datos, si es menor a 0 significa que está vacía
     * Si la tabla no contiene datos consume el servicio y guarda la información en las respectivas tablas
     */
    suspend fun validateData() {
        localData?.memberDao?.countItems()?.let { count ->
            if (count > 0) {
                return
            } else {
                val members = retrofitInstance.getMember()
                members.forEach {
                    val member = it.toData()
                    Log.i("Service", "$member")
                    localData.memberDao.insertMember(member)
                    val detail = retrofitInstance.getMemberDetail(member.id.toString())
                    localData.giftDao.insertGift(detail.toData())
                }
            }
        }
    }

}