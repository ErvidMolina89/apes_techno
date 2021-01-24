package com.example.apes_techno.nuevo.di.module

import com.example.apes_techno.nuevo.data_access.api.ApiMovies
import com.example.apes_techno.nuevo.data_access.dbLocal.DbMovies
import com.example.apes_techno.nuevo.data_access.preferences.PreferenceMovies
import com.example.apes_techno.nuevo.data_access.repositories.RepoMovies
import com.example.apes_techno.nuevo.presenter.movies.ViewModelMovie
import com.example.apes_techno.nuevo.use_case.movies.GetApiListMoviesUseCase
import com.example.apes_techno.nuevo.use_case.movies.GetDbLocalListMoviesUseCase
import com.example.apes_techno.nuevo.use_case.movies.GetPreferenceListMoviesUseCase
import dagger.Module
import dagger.Provides

@Module
class MoviesModule {
    // DataSources
    @Provides
    fun provideApiMovies() = ApiMovies()
    @Provides
    fun provideDbMovies() = DbMovies()
    @Provides
    fun providePreferenceMovies() = PreferenceMovies()

    // Repositorio
    @Provides
    fun provideRepoMovies(apiMovies: ApiMovies,
                          dbMovies: DbMovies,
                          preferenceMovies: PreferenceMovies) = RepoMovies(apiMovies, dbMovies, preferenceMovies)

    // Case Uses
    @Provides
    fun provideApiListMovies(repoMovies: RepoMovies) = GetApiListMoviesUseCase(repoMovies)
    @Provides
    fun provideDbLocalListMovies(repoMovies: RepoMovies) = GetDbLocalListMoviesUseCase(repoMovies)
    @Provides
    fun providePreferenceListMovies(repoMovies: RepoMovies) = GetPreferenceListMoviesUseCase(repoMovies)

    // ViewModel o Presenter
    @Provides
    fun provideViewModelMovies(getApiListMoviesUseCase: GetApiListMoviesUseCase) = ViewModelMovie(getApiListMoviesUseCase)

}