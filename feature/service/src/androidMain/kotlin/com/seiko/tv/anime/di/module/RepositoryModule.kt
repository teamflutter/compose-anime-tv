package com.seiko.tv.anime.di.module

import com.seiko.tv.anime.di.scope.ioDispatcher
import com.seiko.tv.anime.repository.AnimeRepository
import org.koin.dsl.module

val repositoryModule = module {
  single { AnimeRepository(get(), get(), get(ioDispatcher)) }
}
