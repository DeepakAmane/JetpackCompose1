package com.example.compose1.network

import com.example.compose1.model.Employee
import com.example.compose1.model.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeApi {
    @GET("v3/qs/683a0ca98561e97a501d9c16")
    suspend fun getEmployees(): Response<EmployeeResponse>
}