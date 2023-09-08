package com.git.falcone.di

import android.content.Context
import com.git.falcone.model.data.repository.Repository
import com.git.falcone.model.data.apiService.ApiService
import com.git.falcone.ui.FindFalconeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://findfalcone.geektrust.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository {
        return Repository(apiService)
    }

    @Provides
    @Singleton
    fun provideViewModel(repository: Repository): FindFalconeViewModel {
        return FindFalconeViewModel(repository)
    }
}