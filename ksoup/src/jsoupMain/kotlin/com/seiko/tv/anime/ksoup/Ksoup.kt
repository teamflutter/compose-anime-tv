package com.seiko.tv.anime.ksoup

import org.jsoup.Jsoup

actual object Ksoup {
  actual fun parse(html: String): Document {
    return Document(Jsoup.parse(html))
  }
}