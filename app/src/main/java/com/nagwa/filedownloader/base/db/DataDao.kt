package com.nagwa.filedownloader.base.db

import androidx.room.*
import io.reactivex.Single

@Dao
interface DataDao {

    @Insert()
    fun insertList(data: List<ProductEntity>)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert()
    fun insertItem(data: ProductEntity)

    @Query("SELECT * FROM cart")
    fun fetchAllCart(): Single<List<ProductEntity>>

    @Delete
    fun deleteItem(data: ProductEntity)

    @Query("DELETE FROM cart WHERE id = :rawId")
    fun deleteItemById(rawId: Int)

    @Query("UPDATE cart SET quantity = :quantity WHERE id = :rawId")
    fun setItemQuantity(rawId: Int, quantity: Int)

    @Query("DELETE FROM cart")
    fun deleteAllItems(): Int

}