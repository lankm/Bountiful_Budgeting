package com.example.bb.frontend

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bb.backend.Budget
import com.example.bb.backend.Category
import com.example.bb.backend.Expense
import com.example.bb.backend.User


@Preview
@Composable
fun WhichUser(){
    var u = User.users()
    var userSelected = u[0]
    ShowBudgetFromUser(u[0])
    AddBudgetForUserSpec( userSelected, 0,"random" , 151.02 )
    Column() {

        Text(text = "${u[0].budgets[0].categories.last()}")
    }
}


@Composable
fun ShowBudgetFromUser(u: User){


}


@Composable
fun getCategoriesFromUser(){

}


@Composable
fun AddBudgetForUserSpec(u:User,budgetSelected: Int,nameCategory: String,capIncome: Double ){
    u.budgets[budgetSelected].categories.add(Category(nameCategory,capIncome))
    u.budgets[budgetSelected].categories.last()

    testAddExpenseForCategoryCreated(u.budgets[budgetSelected].categories.last(),CreateExpense("test", 51.6))
}


@Composable
fun AddExpensesForCategoryCreated(category: Category,expenseCreatedArray: ArrayList<Expense>){
    //category.addExpense()



}

@Composable
fun testAddExpenseForCategoryCreated(category: Category,expense: Expense){
    //category.addExpense()



}

fun CreateCategory(nameCategory: String, capIncome: Double):Category{
    return Category(nameCategory,capIncome)
}


fun CreateExpense(nameExpense: String, cost : Double): Expense{
   return Expense(nameExpense, cost)
}