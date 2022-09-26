package com.example.recyclerview.repository

import com.example.recyclerview.base_datos.MembersDatabase
import com.example.recyclerview.commons.toData
import com.example.recyclerview.objects.Members
import com.example.recyclerview.web_service.MembersApi

class MembersRepository(
    private val localData: MembersDatabase?
) {
    /**
     * Instancia de la contrucción de Retrofit
     */
    private val retrofitInstance = MembersApi.getRetrofitClient()

    /**
     * Obtiene los datos de la tabla MembersTable
     * Con los datos recolectados se construye el objeto de tipo Members
     */
    suspend fun getMembers(): List<Members>? {
        val localMembers = localData?.membersDao?.getMembers()?.sortedBy { it.lastName }
        return localMembers?.map { memberTable ->
            memberTable.toLocal()
        }
    }

    /**
     * Obtiene el dato de los regalos de la tabla GiftTable dependiendo el ID de cada miembro del equipo
     * Dependiendo del miembro obtiene los datos correspondientes de la tabla MemberTable
     * Con los datos recolectados se construye el objeto de tipo Members
     */
    suspend fun getDetailById(memberId: Int): Members? {
        val gifts = localData?.giftDao?.getMembersById(memberId)?.map { it.gift }
        val member = localData?.membersDao?.getMemberById(memberId)?.toLocal()
        member?.gifts = gifts
        return member
    }

    /**
     * Valida si la base de datos contiene datos, si es menor a 0 significa que está vacía
     * Si la tabla no contiene datos consume el servicio y guarda la información en las respectivas tablas
     */
    suspend fun validateData() {
        localData?.membersDao?.countItems()?.let { count ->
            if (count > 0) {
                count
            } else {
                val members = retrofitInstance.getMember()
                members.forEach {
                    val member = it.toData()
                    localData.membersDao.insertMember(member)
                    val detail = retrofitInstance.getMemberDetail(member.id.toString())
                    localData.giftDao.insertGift(detail.toData())
                }
            }
        }
    }

}