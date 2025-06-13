package com.example.shmryandex.domain

import com.example.shmryandex.domain.entity.Account
import com.example.shmryandex.domain.entity.Category
import com.example.shmryandex.domain.entity.Expense
import com.example.shmryandex.domain.entity.Income

interface FinanceRepository {

    fun getExpensesList(): List<Expense>

    fun getIncomesList(): List<Income>

    fun getAccount(): Account

    fun getCategoriesList(): List<Category>
}