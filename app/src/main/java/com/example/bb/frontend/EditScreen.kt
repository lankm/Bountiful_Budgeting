package com.example.bb.frontend

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bb.backend.User
import com.example.bb.backend.budgets.ViewModel.BucketViewModel
import com.example.bb.backend.data.CategoryCard
import com.example.bb.frontend.Components.InputField
import com.example.bb.frontend.Components.InputFieldText


@Preview
@Composable
fun PreviewEditScreen(){
    //EditScreen(User.sample(), navController, bucketViewModel)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditScreen(u: User, navController: NavHostController, bucketViewModel: BucketViewModel, index: Int? ) {

    var category: CategoryCard? = null

    if (index != null) {
        if (index >= 0 && bucketViewModel.category.size > 0){
            //gets category saved in view model (shared view model)
            category = bucketViewModel.category[index]
        }
    }

    var categoryTitle = remember {
        if (category != null) {
            mutableStateOf(category.CategoryName)
        } else {
            mutableStateOf("")
        }
    }
    var categoryIncome = remember {
        if (category != null) {
            mutableStateOf(category.Income)
        } else {
            mutableStateOf("0.00")
        }

    }

    val keyboardController = LocalSoftwareKeyboardController.current

    val validState = remember(categoryTitle.value) {
        categoryTitle.value.trim().isNotEmpty()
    }




    Surface(
        modifier = Modifier.fillMaxWidth(),

        ) {
        Card(
            modifier = Modifier.height(300.dp),
            backgroundColor = Color.White
        ) {

            Column() {
                Text(text = "Budget Selected Name")


                Row() {
                    Card(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(50.dp),
                        backgroundColor = Color.Red,
                    ) {
                        ColorPickerPopUp(onValueChange = {}, onColorClicked = {})
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Column() {

                        //TEST


                        Row() {
                            Box(modifier = Modifier.width(200.dp)) {



                                AddCategoryNameA(categoryTitle, validState, keyboardController,) {
                                    categoryTitle = categoryTitle
                                }

                            }

                            AddCategoryNumA(
                                categoryIncome as MutableState<String>,
                                validState,
                                keyboardController,
                            ) {
                                categoryIncome = categoryIncome
                            }
                        }



                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = "Name of Expense ")
                        Text(text = "Expense")

                        Text(text = "Name of Expense ")
                        Text(text = "Expense")

                        Text(text = "Name of Expense ")
                        Text(text = "Expense")


                        Button(onClick = {
                            if (category != null) {
                                category.CategoryName = categoryTitle.value
                                category.Income = categoryIncome.value

                            } else {

                                bucketViewModel.category.add(
                                    CategoryCard(
                                        bucketViewModel.category.size,
                                        categoryTitle.value,
                                        categoryIncome.value
                                    )
                                )
                            }



                            com.example.bb.frontend.navController.popBackStack(
                                NavigationItem.Budget.route,
                                false
                            )
                        }) {
                            Text(text = "Create")
                        }
                    }


                }


            }
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddCategoryNumA( value: MutableState<String>, validState :Boolean, keyboardController: SoftwareKeyboardController?, onValChange: (String) -> Unit = {}){



    InputField(
        valueState = value ,
        labelId = "Category Cap", //Enter Amount
        enabled = true ,
        isSingleLine = false,
        onAction = KeyboardActions{
            if(!validState) return@KeyboardActions

            onValChange( value.value.trim() ) //why?
            keyboardController?.hide()



        }
    )




}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddCategoryNameA( value: MutableState<String>, validState :Boolean, keyboardController: SoftwareKeyboardController?, onValChange: (String) -> Unit = {}) {

    InputFieldText(
        valueState = value ,
        labelId = "Category Name", //Enter Amount
        enabled = true ,
        isSingleLine = true,
        onAction = KeyboardActions{
            if(!validState) return@KeyboardActions

            onValChange( value.value.trim() ) //why?
            keyboardController?.hide()



        }
    )



}






