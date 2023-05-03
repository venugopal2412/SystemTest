package io.newsystemtest.filtersapp.utils



import io.newsystemtest.filtersapp.ui.ViewModel.DashBoardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelModule = module {
    viewModel { DashBoardViewModel() }

}