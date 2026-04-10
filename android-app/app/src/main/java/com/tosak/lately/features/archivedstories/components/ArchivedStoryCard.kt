package com.tosak.lately.features.archivedstories.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.tosak.lately.features.archivedstories.ArchivedStory
import com.tosak.lately.features.stories.viewer.toRelativeTimeString
import java.time.Instant

@Composable
fun ArchivedStoryCard(
  story: ArchivedStory,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .aspectRatio(0.72f)
      .clip(RoundedCornerShape(20.dp))
      .clickable(onClick = onClick)
  ) {
    StoryThumbnail(thumbnailUrl = story.mediaUrl, title = story.caption ?: "")

    StoryDateBadge(
      createdAt = story.createdAt,
      modifier = Modifier
        .align(Alignment.TopEnd)
        .padding(10.dp)
    )

    StoryInfo(
      title = story.caption ?: "",
      locationLabel = story.location.placeName,
      modifier = Modifier
        .align(Alignment.BottomStart)
        .padding(12.dp)
    )
  }
}

@Composable
private fun StoryThumbnail(
  thumbnailUrl: String?,
  title: String,
  modifier: Modifier = Modifier
) {
  AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
      .data(thumbnailUrl)
      .crossfade(true)
      .diskCachePolicy(CachePolicy.ENABLED)
      .memoryCachePolicy(CachePolicy.ENABLED)
      .build(),
    contentDescription = title,
    contentScale = ContentScale.Crop,
    modifier = modifier.fillMaxSize()
  )
}

@Composable
private fun StoryDateBadge(
  createdAt: Instant,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(50),
    color = Color.Black.copy(alpha = 0.45f),
    tonalElevation = 0.dp
  ) {
    Text(
      text = createdAt.toRelativeTimeString(),
      style = MaterialTheme.typography.labelSmall,
      color = Color.White.copy(alpha = 0.9f),
      modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    )
  }
}

@Composable
private fun StoryInfo(
  title: String,
  locationLabel: String,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    Text(
      text = title,
      style = MaterialTheme.typography.bodyMedium,
      fontWeight = FontWeight.Bold,
      color = Color.White,
      lineHeight = 20.sp,
      maxLines = 2,
      overflow = TextOverflow.Ellipsis
    )
    Spacer(Modifier.height(6.dp))
    StoryLocation(locationLabel = locationLabel)
  }
}

@Composable
private fun StoryLocation(
  locationLabel: String
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(3.dp)
  ) {
    Icon(
      imageVector = Icons.Outlined.LocationOn,
      contentDescription = null,
      modifier = Modifier.size(11.dp),
      tint = Color.White.copy(alpha = 0.75f)
    )
    Text(
      text = locationLabel,
      style = MaterialTheme.typography.labelSmall,
      color = Color.White.copy(alpha = 0.75f),
      maxLines = 1,
      overflow = TextOverflow.Ellipsis
    )
  }
}