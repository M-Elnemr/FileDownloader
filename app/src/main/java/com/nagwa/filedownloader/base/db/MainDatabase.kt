package com.nagwa.filedownloader.base.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ProductEntity::class], version = 2)
@TypeConverters(ProductCartConverter::class)
abstract class MainDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao

}