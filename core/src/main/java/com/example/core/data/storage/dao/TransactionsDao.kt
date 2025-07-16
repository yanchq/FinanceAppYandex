package com.example.core.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.data.storage.entity.TransactionDbModel
import com.example.core.data.storage.entity.TransactionWithRelations

@Dao
interface TransactionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactionsList(transactionsList: List<TransactionDbModel>)

    @Update
    suspend fun editTransaction(editedTransaction: TransactionDbModel)

    @Query("SELECT * FROM transactions \n" +
            "WHERE transaction_date >= :startDate AND transaction_date <= :endDate \n" +
            "ORDER BY transaction_date DESC")
    suspend fun getTransactionsByPeriod(startDate: String, endDate: String): List<TransactionWithRelations>

    @Query("SELECT * FROM transactions WHERE id = :transactionId")
    suspend fun getTransactionById(transactionId: Int): TransactionWithRelations

    @Query("""
        SELECT * FROM transactions
        WHERE sync_status = "pending"
    """)
    suspend fun getPendingTransactions(): List<TransactionDbModel>

    @Query("""
        UPDATE transactions 
        SET sync_status = :status 
        WHERE id = :transactionId
    """)
    suspend fun changeTransactionSyncStatus(transactionId: Int, status: String = "synced")

    @Query("""
        UPDATE transactions 
        SET local_id = :localId 
        WHERE id = :transactionId
    """)
    suspend fun changeTransactionLocalId(transactionId: Int, localId: Int? = null)

    @Query("""
        UPDATE transactions 
        SET id = :newTransactionId 
        WHERE id = :oldTransactionId
    """)
    suspend fun changeTransactionId(oldTransactionId: Int, newTransactionId: Int)

    @Query("DELETE FROM transactions WHERE id = :transactionId")
    suspend fun deleteTransactionById(transactionId: Int)
}