package com.example.recyclerview.data_base.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GIFT_TABLE")
data class GiftTable(
    @ColumnInfo(name = "ID") @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "GIFT") val gift: String,
    @ColumnInfo(name = "MEMBER_ID") val memberId: Int
)
