package com.personal.rickpedia.di

import com.personal.rickpedia.data.domain.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//interface RepositoryModule {
//
//    @Singleton
//    @Binds
//    fun fetchCharactersRepository(charactersRepository: CharactersRepository)
//
//}