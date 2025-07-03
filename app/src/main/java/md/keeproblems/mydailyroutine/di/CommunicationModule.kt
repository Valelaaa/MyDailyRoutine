package md.keeproblems.mydailyroutine.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import md.keeproblems.mydailyroutine.ui.bottomsheets.BottomSheetChannel
import md.keeproblems.mydailyroutine.ui.bottomsheets.BottomSheetChannelImpl
import md.keeproblems.mydailyroutine.ui.navigation.NavChannel
import md.keeproblems.mydailyroutine.ui.navigation.NavChannelImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommunicationModule {
    @Provides
    @Singleton
    fun provideNavigationChannel(): NavChannel = NavChannelImpl()

    @Provides
    @Singleton
    fun provideBottomSheetChannel(): BottomSheetChannel = BottomSheetChannelImpl()
}