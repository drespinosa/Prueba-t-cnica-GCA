package com.example.recyclerview.base_datos.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recyclerview.base_datos.tables.GiftTable

@Dao
interface GiftDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGift(gifts: List<GiftTable>)

    @Query("SELECT * FROM GIFT_TABLE WHERE MEMBER_ID =:giftId")
    fun getMembersById(giftId: Int): List<GiftTable>
}