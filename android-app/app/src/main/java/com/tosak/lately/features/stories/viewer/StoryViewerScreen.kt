package com.tosak.lately.features.stories.viewer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.tosak.lately.features.stories.MediaType
import com.tosak.lately.features.stories.StoryVisibility
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.Instant

private const val STORY_DURATION_MS = 10_000

@Composable
fun StoryViewerScreen(
  navController: NavController,
  storyId: String,
  viewModel: StoryViewerViewModel
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val stories = uiState.stories

  val initialIndex = stories.indexOfFirst { it.id == storyId }.takeIf { it >= 0 } ?: 0
  var currentIndex by remember { mutableIntStateOf(initialIndex) }
  val story = stories.getOrNull(currentIndex) ?: return

  var progress by remember(currentIndex) { mutableFloatStateOf(0f) }
  val animatedProgress by animateFloatAsState(
    targetValue   = progress,
    animationSpec = if (progress == 0f) tween(0) else tween(durationMillis = STORY_DURATION_MS, easing = LinearEasing),
    label         = "storyProgress"
  )

  LaunchedEffect(currentIndex) {
    progress = 0f
    delay(50)
    progress = 1f
  }

  fun goNext() {
    if (currentIndex < stories.lastIndex) currentIndex++ else navController.popBackStack()
  }

  fun goPrev() {
    if (currentIndex > 0) currentIndex-- else navController.popBackStack()
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Black)
  ) {

    // Media: image or video
    when (story.mediaType) {
      MediaType.IMAGE -> {
        AsyncImage(
          model              = story.mediaUrl,
          contentDescription = null,
          contentScale       = ContentScale.Crop,
          modifier           = Modifier.fillMaxSize()
        )
      }
      MediaType.VIDEO -> {
//        val context = LocalContext.current
//        val exoPlayer = remember(story.mediaUrl) {
//          ExoPlayer.Builder(context).build().apply {
//            setMediaItem(MediaItem.fromUri(story.mediaUrl))
//            repeatMode = Player.REPEAT_MODE_ONE
//            prepare()
//            playWhenReady = true
//          }
//        }
//        DisposableEffect(story.mediaUrl) {
//          onDispose { exoPlayer.release() }
//        }
//        AndroidView(
//          factory  = {
//            PlayerView(it).apply {
//              player         = exoPlayer
//              useController  = false
//              resizeMode     = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
//            }
//          },
//          modifier = Modifier.fillMaxSize()
//        )
      }
    }

    // Tap zones
    Row(modifier = Modifier.fillMaxSize()) {
      Box(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight()
          .pointerInput(currentIndex) { detectTapGestures(onTap = { goPrev() }) }
      )
      Box(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight()
          .pointerInput(currentIndex) { detectTapGestures(onTap = { goNext() }) }
      )
    }

    // Top scrim
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(
          Brush.verticalGradient(
            colors = listOf(Color.Black.copy(alpha = 0.65f), Color.Transparent)
          )
        )
    )

    // Bottom scrim
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(160.dp)
        .align(Alignment.BottomCenter)
        .background(
          Brush.verticalGradient(
            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.65f))
          )
        )
    )

    // Top UI
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding()
        .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
      // Progress bar
      LinearProgressIndicator(
        progress      = { animatedProgress },
        modifier      = Modifier
          .fillMaxWidth()
          .height(2.dp),
        color         = Color.White,
        trackColor    = Color.White.copy(alpha = 0.35f),
        strokeCap     = StrokeCap.Round
      )

      Spacer(Modifier.height(14.dp))

      Row(
        modifier              = Modifier.fillMaxWidth(),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Row(
          verticalAlignment     = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp),
          modifier              = Modifier.weight(1f)
        ) {
          story.authorUsername?.let {
            AsyncImage(
              model              = story.authorAvatarUrl,
              contentDescription = story.authorUsername,
              contentScale       = ContentScale.Crop,
              modifier           = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .border(1.5.dp, Color.White.copy(alpha = 0.6f), CircleShape)
            )
          }
          Column {
            Row(
              verticalAlignment     = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Text(
                text       = story.authorUsername ?: "",
                style      = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color      = Color.White
              )
              // Public badge
              if (story.visibility == StoryVisibility.PUBLIC) {
                Surface(
                  shape = RoundedCornerShape(4.dp),
                  color = Color.White.copy(alpha = 0.15f)
                ) {
                  Row(
                    verticalAlignment     = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    modifier              = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                  ) {
                    Icon(
                      imageVector        = Icons.Outlined.Public,
                      contentDescription = "Public",
                      tint               = Color.White.copy(alpha = 0.85f),
                      modifier           = Modifier.size(10.dp)
                    )
                    Text(
                      text  = "Public",
                      style = MaterialTheme.typography.labelSmall,
                      color = Color.White.copy(alpha = 0.85f)
                    )
                  }
                }
              }
            }
            Spacer(Modifier.height(2.dp))
            Row(
              verticalAlignment     = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              if (!story.placeName.isNullOrBlank()) {
                Icon(
                  imageVector        = Icons.Outlined.LocationOn,
                  contentDescription = null,
                  tint               = Color.White.copy(alpha = 0.7f),
                  modifier           = Modifier.size(11.dp)
                )
                Text(
                  text  = story.placeName,
                  style = MaterialTheme.typography.bodySmall,
                  color = Color.White.copy(alpha = 0.7f)
                )
              }
            }
          }
        }

        // Close button
        Surface(
          shape   = CircleShape,
          color   = Color.White.copy(alpha = 0.15f),
          onClick = { navController.popBackStack() }
        ) {
          Icon(
            imageVector        = Icons.Outlined.Close,
            contentDescription = "Close",
            tint               = Color.White,
            modifier           = Modifier.padding(8.dp)
          )
        }
      }
    }

    // Bottom: caption + meta
    Column(
      modifier = Modifier
        .align(Alignment.BottomStart)
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 16.dp)
        .navigationBarsPadding(),
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      if (!story.caption.isNullOrBlank()) {
        Text(
          text  = story.caption,
          style = MaterialTheme.typography.bodyMedium,
          color = Color.White.copy(alpha = 0.95f)
        )
      }

      Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment     = Alignment.CenterVertically
      ) {
        // View count
        Row(
          verticalAlignment     = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Icon(
            imageVector        = Icons.Outlined.Visibility,
            contentDescription = "Views",
            tint               = Color.White.copy(alpha = 0.7f),
            modifier           = Modifier.size(14.dp)
          )
          Text(
            text  = story.viewCount.toString(),
            style = MaterialTheme.typography.labelMedium,
            color = Color.White.copy(alpha = 0.7f)
          )
        }

        // Created at
        Text(
          text  = remember(story.createdAt) { story.createdAt.toRelativeTimeString() },
          style = MaterialTheme.typography.labelMedium,
          color = Color.White.copy(alpha = 0.6f)
        )
      }
    }
  }
}


fun Instant.toRelativeTimeString(): String {
  val now    = Instant.now()
  val diff   = Duration.between(this, now)
  return when {
    diff.toMinutes() < 1  -> "Just now"
    diff.toHours()   < 1  -> "${diff.toMinutes()}m ago"
    diff.toHours()   < 24 -> "${diff.toHours()}h ago"
    else                  -> "${diff.toDays()}d ago"
  }
}