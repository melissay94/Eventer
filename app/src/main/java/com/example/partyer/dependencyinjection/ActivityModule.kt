package com.example.partyer.dependencyinjection

import com.example.partyer.activity.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeLaunchActivity(): LaunchActivity

    @ContributesAndroidInjector
    abstract fun contributePartyListActivity(): PartyListActivity

    @ContributesAndroidInjector
    abstract fun contributeCalendarSettingsActivity(): CalendarSettingsActivity

    @ContributesAndroidInjector
    abstract fun contributeCalendarPromptActivity(): CalendarPromptActivity
}