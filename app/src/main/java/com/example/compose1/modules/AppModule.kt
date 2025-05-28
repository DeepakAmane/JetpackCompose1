package com.example.compose1.modules

import android.util.Log
import com.example.compose1.network.EmployeeApi
import com.example.compose1.repository.EmployeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.jsonbin.io/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        Log.d("InstanceCheck", "Retrofit hash in AppModule: ${System.identityHashCode(retrofit)}")
        return retrofit
    }

    @Provides
    @Singleton
    fun provideEmployeeApi(retrofit: Retrofit): EmployeeApi {
        val api = retrofit.create(EmployeeApi::class.java)
        Log.d("InstanceCheck", "EmployeeApi hash in AppModule: ${System.identityHashCode(api)}")
        return api
    }

    @Provides
    @Singleton
    fun provideEmployeeRepository(api: EmployeeApi): EmployeeRepository {
        val repository = EmployeeRepository(api)
        Log.d("InstanceCheck", "EmployeeRepository hash in AppModule: ${System.identityHashCode(repository)}")
        return repository
    }

}