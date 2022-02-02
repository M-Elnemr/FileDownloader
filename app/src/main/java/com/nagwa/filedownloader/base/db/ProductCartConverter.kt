package com.nagwa.filedownloader.base.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductCartConverter {
    private var gson = Gson()

    @TypeConverter
    fun productCartToJson(salesOrderDetails: SalesOrderDetail): String =
        gson.toJson(salesOrderDetails)

    @TypeConverter
    fun jsonToProductCart(json: String): SalesOrderDetail =
        gson.fromJson(json, SalesOrderDetail::class.java)

    @TypeConverter
    fun convertJsonToProduct(json: String): List<SalesOrderDetail> {
        val type = object : TypeToken<List<SalesOrderDetail>>() {}.type
        return gson.fromJson(json, type)
    }

}