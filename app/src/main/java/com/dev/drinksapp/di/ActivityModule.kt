package com.dev.drinksapp.di

import com.dev.drinksapp.data.DataSourceImpl
import com.dev.drinksapp.repository.DataSourceRepository
import com.dev.drinksapp.repository.DrinksRepository
import com.dev.drinksapp.repository.DrinksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindDrinkRepoImpl(repoDrinkImpl: DrinksRepositoryImpl): DrinksRepository

    @Binds
    abstract fun bindDatasourceImpl(datasourceImpl: DataSourceImpl): DataSourceRepository
}