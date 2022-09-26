package com.example.recyclerview.base_datos.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MEMBER")
data class MembersTable(
    @ColumnInfo(name = "ID") @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "NAME") val name: String,
    @ColumnInfo(name = "LAST_NAME") val lastName: String,
    @ColumnInfo(name = "URL_PHOTO") val url: String,
    @ColumnInfo(name = "WORK") val work: String,
)