package com.example.recyclerview.base_datos.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recyclerview.base_datos.tables.MembersTable

@Dao
interface MembersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMember(members: MembersTable)

    @Query("SELECT * FROM MEMBER")
    fun getMembers(): List<MembersTable>

    @Query("SELECT * FROM MEMBER WHERE ID =:memberId")
    fun getMemberById(memberId: Int): MembersTable

    @Query("SELECT COUNT(*) AS TOTAL FROM MEMBER")
    fun countItems(): Int

}