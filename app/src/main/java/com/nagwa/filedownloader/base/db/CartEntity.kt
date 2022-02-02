package com.nagwa.filedownloader.base.db

import androidx.room.Entity

@Entity
data class CartEntity(
    var AdditionalTax: Int? = null,
    var CasherId: Int? = null,
    var CreatedBy: Int? = null,
    var CreationTime: String? = null,
    var CurrencyId: Int? = null,
    var DeliveryPrice: Int? = null,
    var Description: String? = null,
    var Discount: Double? = null,
    var InvoiceDate: String? = null,
    var InvoiceSerial: Int? = null,
    var IsCanceled: Boolean? = null,
    var IsSaleInInstallments: Boolean? = null,
    var ModificationTime: String? = null,
    var ModifiedBy: Int? = null,
    var ProfileId: Int? = null,
    var SaasProfileId: Int? = null,
    var SaleStatus: Int? = null,
    var SalesOrderDetails: List<SalesOrderDetail>? = null,
    var ShiftId: Int? = null,
    var StoreId: Int? = null,
    var Total: Int? = null,
    var TotalNet: Int? = null,
    var TranRef: String? = null,
    var TranStatus: String? = null
)

@Entity
data class SalesOrderDetail(
    ///////////
    var Name: String? = "",
    ///////////
    var CostCenterId: Int? = null,
    var Description: String? = null,
    var Discount: Double? = null,
    var Discountvarue: Int? = null,
    var ItemId: Int? = null,
    var ItemTypeId: Int? = null,
    var CategoryId: Int? = null,
    var Price: Double? = null,
    var PriceCost: Double? = null,
    var Quantity: Int? = null,
    var SoldItemPriceCost: Int? = null,
    var TaxId: Int? = null,
    var Taxvalue: Int? = null,
    var itemAttributeValue: List<ItemAttributeValue>? = null
)

@Entity
data class ItemAttributeValue(
    //////////////////
    var AttributeName: String? = null,
    //////////////////
    var AttributeId: Int? = null,
    var ItemAttributeValueID: Int? = null,
    var AttributeValue: String? = null,
    var ItemId: Int? = null
)