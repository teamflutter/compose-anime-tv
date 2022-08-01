package com.seiko.tv.anime.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterNot

object ToastUtils {
  fun showToast(msg: String?) {
    if (msg == null) return
    mutableToast.tryEmit(msg)
  }
}

private val mutableToast = MutableSharedFlow<String?>(extraBufferCapacity = 1)
private val toast = mutableToast.asSharedFlow()

@Composable
fun BoxScope.ToastScreenComponent() {
  var isShown by remember { mutableStateOf(false) }
  var showMsg by remember { mutableStateOf("") }

  LaunchedEffect(Unit) {
    toast.filterNot { it.isNullOrEmpty() }.collect {
      showMsg = it!!
      isShown = true
    }
  }

  AnimatedVisibility(
    visible = isShown,
    modifier = Modifier.padding(bottom = 50.dp).align(Alignment.BottomCenter),
    enter = fadeIn(),
    exit = fadeOut()
  ) {
    Surface(shape = CircleShape, tonalElevation = 1.dp) {
      Text(showMsg, Modifier.padding(horizontal = 20.dp, vertical = 10.dp))
    }
  }

  if (isShown) {
    LaunchedEffect(Unit) {
      delay(1500)
      isShown = false
      delay(1500)
      showMsg = ""
    }
  }
}