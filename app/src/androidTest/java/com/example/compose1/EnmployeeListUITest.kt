package com.example.compose1

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.compose1.model.Employee

import org.junit.Rule
import org.junit.Test

class EnmployeeListUITest {

    /*
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun employeeList_displaysAllEmployees() {
        val testEmployees = listOf(
            Employee(imageUrl = R.drawable.img_1, name = "Nitish", title = "Engineer"),
            Employee(imageUrl = R.drawable.bg, name = "Jack", title = "Designer")
        )

        composeTestRule.setContent {
            EmployeeList(employees = testEmployees)
        }

        Thread.sleep(5000)

        composeTestRule.onNodeWithText("Nitish").assertIsDisplayed()
        composeTestRule.onNodeWithText("Jack").assertIsDisplayed()
    } */
}