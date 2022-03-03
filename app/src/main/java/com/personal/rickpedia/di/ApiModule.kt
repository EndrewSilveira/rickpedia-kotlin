package com.personal.rickpedia.di

import com.personal.rickpedia.data.remote.api.CharacterApiFactory
import com.personal.rickpedia.data.remote.apiHelper.CharacterApiHelper
import com.personal.rickpedia.data.remote.apiHelper.CharacterApiHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideApi(): CharacterApiFactory = CharacterApiFactory.newInstance("https://rickandmortyapi.com/api/")

    @Provides
    @Singleton
    fun provideCharacterApi(
        characterApi: CharacterApiFactory
    ): CharacterApiHelper = CharacterApiHelperImpl(characterApi)
}