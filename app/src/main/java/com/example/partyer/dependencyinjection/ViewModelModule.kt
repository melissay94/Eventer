package com.example.partyer.dependencyinjection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.partyer.viewmodel.*
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    /**
     * This method basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  ViewModel.class as key,
     * and a Provider that will build a ViewModel
     * object.
     *
     * */
    @Binds
    @IntoMap
    @ViewModelKey(LaunchViewModel::class)
    protected abstract fun launchViewModel(launchViewModel: LaunchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PartyListViewModel::class)
    protected abstract fun partyListViewModel(partyListViewModel: PartyListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CalendarSettingViewModel::class)
    protected abstract fun calendarSettingsViewModel(calendarSettingViewModel: CalendarSettingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CalendarPromptViewModel::class)
    protected abstract fun calendarPromptViewModel(calendarPromptViewModel: CalendarPromptViewModel): ViewModel
}