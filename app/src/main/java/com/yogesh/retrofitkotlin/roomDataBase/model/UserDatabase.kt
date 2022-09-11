package com.yogesh.retrofitkotlin.roomDataBase.model

import android.content.Context
import androidx.room.*

@Database(entities = [UserEntity::class], exportSchema = false, version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object{
        @Volatile
        private var INSTANCE:UserDatabase? = null
        val DB_NAME = "UserDatabase"

        fun getDBInstance(context: Context):UserDatabase{

            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,UserDatabase::class.java, DB_NAME).build()
                }
            }
            return INSTANCE!!
        }
    }


}