package com.dev.drinksapp.di

import android.content.Context
import androidx.room.Room
import com.dev.drinksapp.db.DrinkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) =
        Room.databaseBuilder(
            context.applicationContext,
            DrinkDatabase::class.java,
            "drinks_db.db"
        ).build()

    @Singleton
    @Provides
    fun provideDrinksDao(db: DrinkDatabase) = db.getDrinkDao()
}