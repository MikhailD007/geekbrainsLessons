package org.vimteam.weatherreport.main

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.experimental.builder.factoryBy
import org.vimteam.weatherreport.main.data.db.WeatherDB
import org.vimteam.weatherreport.main.data.models.CalcDataDB
import org.vimteam.weatherreport.main.data.repositories.CalcRepository
import org.vimteam.weatherreport.main.data.repositories.WeatherRepository
import org.vimteam.weatherreport.main.domain.contracts.*
import org.vimteam.weatherreport.main.domain.viewmodels.CalcViewModel
import org.vimteam.weatherreport.main.domain.viewmodels.MainViewModel
import org.vimteam.weatherreport.main.ui.providers.ResourcesProvider

object MainModule {
    fun get() = module {
        factoryBy<ResourcesProviderContract, ResourcesProvider>()

        factory { WeatherDB() }
        factoryBy<WeatherRepositoryContract, WeatherRepository>()
        viewModel<MainContract.ViewModel> { MainViewModel(get()) }

        single { CalcDataDB() }
        factoryBy<CalcRepositoryContract, CalcRepository>()
        viewModel<CalcContract.ViewModel> { CalcViewModel(get(), get()) }
    }
}