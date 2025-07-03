package md.keeproblems.mydailyroutine.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import md.keeproblems.mydailyroutine.data.store.serializer.JsonSerializer
import md.keeproblems.mydailyroutine.domain.service.ThemeSelectorChannel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {
    @Provides
    @Singleton
    fun provideGsonSerializer(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRoutineJsonSerializer(gson: Gson): JsonSerializer =
        JsonSerializer(gson)

    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideThemeSelectorChannel(): ThemeSelectorChannel = ThemeSelectorChannel()
}