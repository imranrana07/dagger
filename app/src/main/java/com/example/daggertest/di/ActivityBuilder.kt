package com.evaly.mvrxsample.di

import com.example.daggertest.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [FragmentProvider::class])
    abstract fun provideMainActivity(): MainActivity?
}
