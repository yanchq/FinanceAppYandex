package com.example.core.data.storage.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionWithRelations(
    @Embedded val transaction: TransactionDbModel,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: CategoryDbModel,
    @Relation(
        parentColumn = "account_id",
        entityColumn = "id"
    )
    val account: AccountDbModel
)
