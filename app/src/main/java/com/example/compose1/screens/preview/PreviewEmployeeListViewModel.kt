package com.example.compose1.screens.preview

import com.example.compose1.model.Employee
import com.example.compose1.repository.EmployeeRepository
import com.example.compose1.viewmodel.EmployeeListViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class PreviewEmployeeListViewModel {

    var employeeList = MutableStateFlow(
        listOf(
            Employee("John Doe", "Software Engineer", "https://example.com/john.jpg"),
            Employee("Jane Smith", "Product Manager", "https://example.com/jane.jpg")
        )
    )

    val isLoading = MutableStateFlow(false)
}