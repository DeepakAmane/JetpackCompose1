package com.example.compose1.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _employeeUiStateMutableStateFlow = MutableStateFlow<EmployeeUiState>(EmployeeUiState.Loading)
    val employeeUiState: StateFlow<EmployeeUiState> = _employeeUiStateMutableStateFlow


    init {
        //      Log.d("InstanceCheck", "EmployeeListViewModel hash: ${System.identityHashCode(this)}")
        //     Log.d("InstanceCheck", "EmployeeRepository hash in EmployeeListViewModel: ${System.identityHashCode(repository)}")
        fetchEmployees()
    }

    private fun fetchEmployees() {
        //  Log.d("EmployeeViewModel", "API call triggered")
        viewModelScope.launch {
            _employeeUiStateMutableStateFlow.value = EmployeeUiState.Loading
            try {
                val employees = withContext(Dispatchers.IO) {
                    //        Log.d("VM", "Coroutine started on DEEPS: thread: ${Thread.currentThread().name}")
                    repository.fetchEmployees()
                }

                _employeeUiStateMutableStateFlow.value = when {
                    employees.isNullOrEmpty() -> EmployeeUiState.Error("No employees found")
                    else -> EmployeeUiState.Success(employees)
                }

                //       Log.d("VM", "Coroutine resumed after delay: ${Thread.currentThread().name}")
            } catch (e: Exception) {
                _employeeUiStateMutableStateFlow.value = EmployeeUiState.Error(e.localizedMessage ?: "Unknown error")
                Log.e("EmployeeViewModel", "Exception: ${e.message}")
            }
        }
    }
}