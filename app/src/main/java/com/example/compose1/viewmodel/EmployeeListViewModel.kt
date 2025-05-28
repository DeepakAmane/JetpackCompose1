package com.example.compose1.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose1.model.Employee
import com.example.compose1.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(private val repository: EmployeeRepository) :
    ViewModel() {
    private val employeeListMutableFlow = MutableStateFlow<List<Employee>>(emptyList())
    val employeeList: StateFlow<List<Employee>> = employeeListMutableFlow

    private val isLoadingMutable = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = isLoadingMutable

    init {
        Log.d("InstanceCheck", "EmployeeListViewModel hash: ${System.identityHashCode(this)}")
        Log.d("InstanceCheck", "EmployeeRepository hash in EmployeeListViewModel: ${System.identityHashCode(repository)}")
        fetchEmployees()
    }

    private fun fetchEmployees() {
        Log.d("EmployeeViewModel", "API call triggered")
        viewModelScope.launch {
            try {
                val employees = withContext(Dispatchers.IO) {
                    Log.d("VM", "Coroutine started on DEEPS: thread: ${Thread.currentThread().name}")
                    repository.fetchEmployees()
                }
                employees?.let {
                    employeeListMutableFlow.value = it
                }
                Log.d("VM", "Coroutine resumed after delay: ${Thread.currentThread().name}")
            } catch (e: Exception) {
                Log.e("EmployeeViewModel", "Exception: ${e.message}")
            } finally {
                isLoadingMutable.value = false
            }
        }
    }
}