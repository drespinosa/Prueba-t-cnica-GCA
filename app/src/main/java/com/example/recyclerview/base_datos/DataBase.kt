package com.example.recyclerview.base_datos

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recyclerview.base_datos.daos.GiftDao
import com.example.recyclerview.base_datos.daos.MembersDao
import com.example.recyclerview.base_datos.tables.GiftTable
import com.example.recyclerview.base_datos.tables.MembersTable

@Database(
    entities = [MembersTable::class, GiftTable::class], version = 1
)
abstract class MembersDatabase : RoomDatabase() {

    abstract val membersDao: MembersDao
    abstract val giftDao: GiftDao

    companion object {
        private const val DATABASE_NAME = "MEMBERS_ROOM"

        @Volatile
        private var INSTANCE: MembersDatabase? = null

        /**
         * Construcción base de datos
         */
        fun getDataInstance(context: Application): MembersDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    MembersDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}
