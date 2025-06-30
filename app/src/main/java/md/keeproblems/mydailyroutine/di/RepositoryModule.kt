package md.keeproblems.mydailyroutine.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import md.keeproblems.mydailyroutine.data.repository.RoutineRepositoryImpl
import md.keeproblems.mydailyroutine.domain.repository.RoutineRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideRoutineRepository(impl: RoutineRepositoryImpl): RoutineRepository
}