import java.time.Instant

data class User(
    val id: String,
    val username: String,
    val displayName: String,
    val avatarUrl: String?,
    val bio: String?,

    // Location
    val location: UserLocation?,
    val isLocationVisible: Boolean = true,
    val lastSeenAt: Instant?,

    // Social
    val followerCount: Int = 0,
    val followingCount: Int = 0,
    val isFollowing: Boolean = false,
    val isFollowedBy: Boolean = false,

    // Account
    val isVerified: Boolean = false,
    val isCurrentUser: Boolean = false,
    val accountCreatedAt: Instant,
)

data class UserLocation(
    val latitude: Double,
    val longitude: Double,
    val geohash: String?,
    val lastUpdatedAt: Instant,
)