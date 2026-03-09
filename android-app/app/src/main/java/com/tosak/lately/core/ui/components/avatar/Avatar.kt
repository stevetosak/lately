package com.tosak.lately.core.ui.components.avatar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.tosak.lately.R

@Composable
fun Avatar(
  name: String,
  avatarUrl: String?,
  modifier: Modifier = Modifier,
) {
  AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
      .data(avatarUrl)
      .crossfade(true)
      .diskCachePolicy(CachePolicy.ENABLED)
      .memoryCachePolicy(CachePolicy.ENABLED)
      .build(),
    contentDescription = "$name avatar",
    modifier = modifier
      .fillMaxSize()
      .clip(CircleShape),
    contentScale = ContentScale.Crop,
    placeholder = painterResource(R.drawable.ic_placeholder),
    error = painterResource(R.drawable.ic_fallback)
  )
}