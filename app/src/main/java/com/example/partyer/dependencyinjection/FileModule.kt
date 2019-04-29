package com.example.partyer.dependencyinjection

import com.example.partyer.data.ReadFile
import com.example.partyer.data.WriteFile
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FileModule {

    /**
     * Return GSON object
     */
    @Provides
    @Singleton
    internal fun provideGSON(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    /**
     * Return ReadFile object
     */
    @Provides
    @Singleton
    internal fun provideReadFile(): ReadFile = ReadFile()


    /**
     * Return WriteFile object
     */
    @Provides
    @Singleton
    internal fun provideWriteFile(): WriteFile =  WriteFile()

}