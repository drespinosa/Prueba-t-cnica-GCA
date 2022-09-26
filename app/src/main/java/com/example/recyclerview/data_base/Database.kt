package com.example.recyclerview.data_base

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recyclerview.data_base.daos.GiftDao
import com.example.recyclerview.data_base.daos.MembersDao
import com.example.recyclerview.data_base.tables.GiftTable
import com.example.recyclerview.data_base.tables.MembersTable

@Database(
    entities = [MembersTable::class, GiftTable::class], version = 1
)
abstract class MemberDatabase : RoomDatabase() {

    abstract val memberDao: MembersDao
    abstract val giftDao: GiftDao

    companion object {
        private const val DATABASE_NAME = "MEMBERS_ROOM"

        @Volatile
        private var INSTANCE: MemberDatabase? = null

        fun getDataInstance(context: Application): MemberDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    MemberDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }

}