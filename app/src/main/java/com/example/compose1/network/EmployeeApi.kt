package com.example.compose1.network

import com.example.compose1.model.Employee
import com.example.compose1.model.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeApi {
    @GET("v3/qs/6836f1cc8a456b7966a665fb")
    suspend fun getEmployees(): Response<EmployeeResponse>
}