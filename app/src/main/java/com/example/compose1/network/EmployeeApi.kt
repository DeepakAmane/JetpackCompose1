package com.example.compose1.network

import com.example.compose1.BuildConfig
import com.example.compose1.BuildConfig.JSONBIN_BIN_ID
import com.example.compose1.model.Employee
import com.example.compose1.model.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeApi {
    @GET("v3/b/${BuildConfig.JSONBIN_BIN_ID}")
    suspend fun getEmployees(): Response<EmployeeResponse>
}