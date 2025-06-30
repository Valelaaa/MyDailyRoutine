package md.keeproblems.mydailyroutine.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import md.keeproblems.mydailyroutine.data.store.DataStore
import md.keeproblems.mydailyroutine.data.store.IRoutineStore
import md.keeproblems.mydailyroutine.data.store.impl.LiveSessionDataStore
import md.keeproblems.mydailyroutine.data.store.impl.RoutineStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {
    @Binds
    @Singleton
    abstract fun provideDataStore(impl: LiveSessionDataStore): DataStore

    @Binds
    @Singleton
    abstract fun provideRoutineDataStore(impl: RoutineStore): IRoutineStore
}