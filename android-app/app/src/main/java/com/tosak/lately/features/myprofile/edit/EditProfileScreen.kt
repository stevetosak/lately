package com.tosak.lately.features.myprofile.edit

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Notes
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.tosak.lately.R
import com.tosak.lately.core.ui.components.bars.AppTopBar
import com.tosak.lately.core.ui.components.avatar.AvatarRing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
  navController: NavController,
) {
  val viewModel: EditProfileViewModel = hiltViewModel()
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  var name by rememberSaveable { mutableStateOf(uiState.displayName) }
  var username by rememberSaveable { mutableStateOf(uiState.username) }
  var bio by rememberSaveable { mutableStateOf(uiState.bio) }
  var phone by rememberSaveable { mutableStateOf(uiState.phone) }

  var selectedAvatarUri by rememberSaveable { mutableStateOf<Uri?>(null) }

  val galleryLauncher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent()
  ) { uri: Uri? ->
    if (uri != null) selectedAvatarUri = uri
  }

  fun handleOnSave() {
    viewModel.updateDisplayName(name)
    viewModel.updateUsername(username)
    viewModel.updateBio(bio)
    viewModel.updatePhone(phone)

    if (selectedAvatarUri != null) {
      viewModel.updateAvatar(selectedAvatarUri.toString())
    }

    viewModel.saveProfile()
    navController.popBackStack()
  }

  Scaffold(
    topBar = {
      AppTopBar(
        title = "Profile info",
        navController = navController,
        actions = {
          TextButton(
            onClick = { handleOnSave() }
          ) {
            Text(
              "Save",
              fontWeight = FontWeight.SemiBold,
              color = MaterialTheme.colorScheme.primary
            )
          }
        }
      )
    },
    containerColor = MaterialTheme.colorScheme.background
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(innerPadding),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {

      Spacer(Modifier.height(24.dp))

      AvatarRing(
        size          = 108,
        onClick       = { galleryLauncher.launch("image/*") },
        showEditBadge = true
      ) {
        AsyncImage(
          model              = selectedAvatarUri ?: uiState.avatarUrl,
          contentDescription = "$name avatar",
          modifier           = Modifier
            .fillMaxSize()
            .clip(CircleShape),
          contentScale       = ContentScale.Crop,
          placeholder        = painterResource(R.drawable.ic_placeholder),
          error              = painterResource(R.drawable.ic_fallback)
        )
      }

      Spacer(Modifier.height(32.dp))

      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {

        FieldGroup(label = "Identity") {
          EditField(
            value         = name,
            onValueChange = { name = it },
            label         = "Display name",
            icon          = Icons.Outlined.Person
          )
          FieldDivider()
          EditField(
            value         = username,
            onValueChange = { username = it },
            label         = "Username",
            icon          = Icons.Outlined.AlternateEmail
          )
        }
        FieldGroup(label = "About") {
          EditField(
            value         = bio,
            onValueChange = { bio = it },
            label         = "Bio",
            icon          = Icons.AutoMirrored.Outlined.Notes
          )
        }
        FieldGroup(label = "Contact") {
          EditField(
            value         = phone,
            onValueChange = { phone = it },
            label         = "Phone number",
            icon          = Icons.Outlined.Phone
          )
        }
      }

      Spacer(Modifier.height(40.dp))
    }
  }
}


@Composable
private fun FieldGroup(
  label: String,
  content: @Composable ColumnScope.() -> Unit
) {
  Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
    Text(
      text          = label.uppercase(),
      style         = MaterialTheme.typography.labelSmall,
      color         = MaterialTheme.colorScheme.onSurfaceVariant,
      fontWeight    = FontWeight.SemiBold,
      letterSpacing = 1.2.sp
    )
    Surface(
      modifier       = Modifier.fillMaxWidth(),
      shape          = RoundedCornerShape(16.dp),
      color          = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
      tonalElevation = 0.dp
    ) {
      Column(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
        content  = content
      )
    }
  }
}

@Composable
private fun FieldDivider() {
  HorizontalDivider(
    modifier  = Modifier.padding(horizontal = 16.dp),
    thickness = 0.5.dp,
    color     = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
  )
}

// ── Edit Field ────────────────────────────────────────────────────────────────

@Composable
private fun EditField(
  value: String,
  onValueChange: (String) -> Unit,
  label: String,
  icon: ImageVector,
  keyboardType: KeyboardType = KeyboardType.Text
) {
  TextField(
    value           = value,
    onValueChange   = onValueChange,
    label           = { Text(label, fontSize = 12.sp) },
    leadingIcon     = {
      Icon(
        imageVector        = icon,
        contentDescription = null,
        modifier           = Modifier.size(20.dp),
        tint               = MaterialTheme.colorScheme.onSurfaceVariant
      )
    },
    modifier        = Modifier.fillMaxWidth(),
    singleLine      = true,
    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
    colors          = TextFieldDefaults.colors(
      focusedContainerColor   = Color.Transparent,
      unfocusedContainerColor = Color.Transparent,
      focusedIndicatorColor   = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent,
      disabledIndicatorColor  = Color.Transparent,
      focusedLabelColor       = MaterialTheme.colorScheme.primary,
      unfocusedLabelColor     = MaterialTheme.colorScheme.onSurfaceVariant
    )
  )
}
