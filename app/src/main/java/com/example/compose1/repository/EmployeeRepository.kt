package com.example.compose1.repository

import android.util.Log
import com.example.compose1.model.Employee
import com.example.compose1.network.EmployeeApi
import com.example.compose1.network.RetrofitInstance
import kotlinx.coroutines.delay
import javax.inject.Inject

class EmployeeRepository @Inject constructor(private val api: EmployeeApi) {

    init {

        Log.d("InstanceCheck", "EmployeeApi hash in EmployeeRepository: ${System.identityHashCode(api)}")
        Log.d("InstanceCheck", "EmployeeRepository hash in EmployeeRepository: ${System.identityHashCode(this)}")
    }

    suspend fun fetchEmployees(): List<Employee>? {
        delay(2000)
        return try {
            val response = api.getEmployees()
            if (response.isSuccessful && response.body() != null) {
                response.body()?.record
            } else {
                Log.e("Repository", "API Error: ${response.code()}")
                null
            }

        } catch (e: Exception) {
            Log.e("Repository", "Exception: ${e.message}")
            null
        }
    }
}