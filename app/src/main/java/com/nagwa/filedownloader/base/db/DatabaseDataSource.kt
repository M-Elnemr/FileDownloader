package com.nagwa.filedownloader.base.db

import io.reactivex.Single
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class DatabaseDataSource @Inject constructor(private val dataDao: DataDao) {

    //TODO MVVM DESIGN PATTERN FOR LOCAL DATABASE

    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)

    fun insertItem(productEntity: ProductEntity) {
        executorService.execute(
            Runnable {
                dataDao.insertItem(productEntity)
            }
        )
    }

    fun deleteItem(productEntity: ProductEntity) {
        executorService.execute(
            Runnable {
                dataDao.deleteItem(productEntity)
            }
        )
    }

    fun emptyCart() {
        executorService.execute(
            Runnable {
                dataDao.deleteAllItems()
            }
        )
    }

    fun adjustQuantity(rowId: Int, quantity: Int) {
        executorService.execute(
            Runnable {
                dataDao.setItemQuantity(rowId, quantity)
            }
        )
    }

    fun fetchCart(): Single<List<ProductEntity>> {
        return dataDao.fetchAllCart()
    }
}