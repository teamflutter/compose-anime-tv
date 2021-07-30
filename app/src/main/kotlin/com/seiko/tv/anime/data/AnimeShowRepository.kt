package com.seiko.tv.anime.data

import com.seiko.tv.anime.http.YydmService
import com.seiko.tv.anime.model.AnimeNode
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeShowRepository @Inject constructor(
  private val service: YydmService
) {

  /**
   * 约等于：
   *   val list = new ArrayList()
   *   for (children in area) {
   *     for (element in children) {
   *       val bean = parse(element)
   *       list.add(bean)
   *     }
   *   }
   *   list
   */
  @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
  fun getAnimeList(path: String = ""): Flow<List<AnimeNode>> {
    return flow {
      val response = service.getHomeResponse(path)
      emit(response.animes)
    }
  }
}