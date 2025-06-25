package com.example.compose1.modules

import com.example.compose1.modules.di.AuthOkHttp
import com.example.compose1.modules.di.AuthRetrofit
import com.example.compose1.modules.di.EmployeeOkHttp
import com.example.compose1.modules.di.EmployeeRetrofit
import com.example.compose1.network.AuthApi
import com.example.compose1.network.AuthInterceptor
import com.example.compose1.network.EmployeeApi
import com.example.compose1.repository.AuthRepository
import com.example.compose1.repository.EmployeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val EMPLOYEE_BASE_URL = "https://api.jsonbin.io/"
    private const val AUTH_BASE_URL = "https://dummyjson.com/"

    // Employee API
    @Provides
    @Singleton
    @EmployeeOkHttp
    fun provideEmployeeOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
    }

    @Provides
    @Singleton
    @AuthOkHttp
    fun provideAuthOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    @EmployeeRetrofit
    fun provideEmployeeRetrofit(
        @EmployeeOkHttp employeeClient: OkHttpClient
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(EMPLOYEE_BASE_URL)
            .client(employeeClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //    Log.d("InstanceCheck", "Retrofit hash in AppModule: ${System.identityHashCode(retrofit)}")
        return retrofit
    }

    // Retrofit for Auth API
    @Provides
    @Singleton
    @AuthRetrofit
    fun provideAuthRetrofit(@AuthOkHttp authClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AUTH_BASE_URL)
            .client(authClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideEmployeeApi(@EmployeeRetrofit employeeRetrofit: Retrofit): EmployeeApi {
        val api = employeeRetrofit.create(EmployeeApi::class.java)
        //     Log.d("InstanceCheck", "EmployeeApi hash in AppModule: ${System.identityHashCode(api)}")
        return api
    }


    @Provides
    @Singleton
    fun provideAuthApi(@AuthRetrofit authRetrofit: Retrofit): AuthApi {
        return authRetrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEmployeeRepository(api: EmployeeApi): EmployeeRepository {
        val repository = EmployeeRepository(api)
        //       Log.d("InstanceCheck", "EmployeeRepository hash in AppModule: ${System.identityHashCode(repository)}")
        return repository
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): AuthRepository {
        return AuthRepository(api)
    }

}