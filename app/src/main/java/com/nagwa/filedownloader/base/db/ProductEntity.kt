package com.nagwa.filedownloader.base.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "product")
    var salesOrderDetail: SalesOrderDetail,

    @ColumnInfo(name = "quantity")
    var quantity: Int
)
