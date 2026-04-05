package com.tosak.lately.core.data.repository

import com.tosak.lately.features.chats.ChatPreview
import com.tosak.lately.features.chats.chat.ChatMessage
import com.tosak.lately.features.search.FriendshipStatus
import com.tosak.lately.features.search.SearchUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatsRepository @Inject constructor() {
    private val MOCK_FRIENDS = listOf(
        SearchUser("1", "Jordan Lee", "@jordanlee", "https://randomuser.me/api/portraits/men/7.jpg", FriendshipStatus.FRIENDS),
        SearchUser("2", "Maya Patel", "@mayapatel", "https://randomuser.me/api/portraits/women/10.jpg", FriendshipStatus.FRIENDS),
        SearchUser("3", "Chris Novak", "@chrisnovak", "https://randomuser.me/api/portraits/men/14.jpg", FriendshipStatus.FRIENDS),
        SearchUser("4", "Sofia Reyes", "@sofiareyes", "https://randomuser.me/api/portraits/women/2.jpg", FriendshipStatus.FRIENDS),
        SearchUser("5", "Ethan Brooks", "@ethanbrooks", "https://randomuser.me/api/portraits/men/15.jpg", FriendshipStatus.FRIENDS),
        SearchUser("6", "Priya Sharma", "@priyasharma", "https://randomuser.me/api/portraits/women/8.jpg", FriendshipStatus.FRIENDS),
        SearchUser("7", "Luca Ferraro", "@lucaferraro", "https://randomuser.me/api/portraits/men/13.jpg", FriendshipStatus.FRIENDS),
        SearchUser("8", "Nadia Wolff", "@nadiawolff", "https://randomuser.me/api/portraits/women/17.jpg", FriendshipStatus.FRIENDS),
        SearchUser("9", "Oliver Chen", "@oliverchen", "https://randomuser.me/api/portraits/men/21.jpg", FriendshipStatus.FRIENDS),
        SearchUser("10", "Amelia Rossi", "@ameliarossi", "https://randomuser.me/api/portraits/women/22.jpg", FriendshipStatus.FRIENDS),
    )

    private val MOCK_MESSAGES: Map<String, List<ChatMessage>> = mapOf(
        "1" to listOf(
            ChatMessage("m1", "Hey! Long time no see 👋", false, "09:10"),
            ChatMessage("m2", "Yeah! How have you been?", true, "09:12"),
            ChatMessage("m3", "Pretty good, just got back from a trip!", false, "09:13"),
        ),
        "2" to listOf(
            ChatMessage("m1", "Did you see the game last night?", false, "Yesterday"),
            ChatMessage("m2", "No, what happened??", true, "Yesterday"),
            ChatMessage("m3", "It was insane 🔥", false, "Yesterday"),
        ),
        "3" to listOf(
            ChatMessage("m1", "Can you send me those files?", false, "Mon"),
            ChatMessage("m2", "Sure, give me a sec", true, "Mon"),
        ),
        "4" to listOf(
            ChatMessage("m1", "Happy birthday!! 🎉🎂", false, "Sun"),
            ChatMessage("m2", "Thank you so much!! 🥹", true, "Sun"),
        ),
        "5" to listOf(
            ChatMessage("m1", "Are you coming to the party?", false, "Fri"),
            ChatMessage("m2", "Wouldn't miss it!", true, "Fri"),
            ChatMessage("m3", "Awesome, see you there 🙌", false, "Fri"),
        ),
        "6" to listOf(
            ChatMessage("m1", "Hey, do you have the notes from class?", false, "Thu"),
            ChatMessage("m2", "Yeah I'll send them over", true, "Thu"),
        ),
        "7" to listOf(
            ChatMessage("m1", "Let's grab coffee sometime ☕", false, "Wed"),
            ChatMessage("m2", "Sounds great, when works for you?", true, "Wed"),
        ),
        "8" to listOf(
            ChatMessage("m1", "Just saw your post, looks amazing!", false, "Tue"),
            ChatMessage("m2", "Aww thanks!! 😊", true, "Tue"),
        ),
        "9" to listOf(
            ChatMessage("m1", "Did you finish the project?", false, "Mon"),
            ChatMessage("m2", "Almost, just the last section", true, "Mon"),
            ChatMessage("m3", "You got this!", false, "Mon"),
        ),
        "10" to listOf(
            ChatMessage("m1", "Miss hanging out with you guys 🥺", false, "Last week"),
            ChatMessage("m2", "Same!! We need to plan something", true, "Last week"),
        ),
    )

    private val MOCK_AUTO_REPLIES = listOf(
        "Haha for real 😂",
        "That's actually wild",
        "No way!! 😱",
        "Okay but same though",
        "I was literally just thinking about that",
        "Ahh makes sense",
        "Wait really??",
        "Omg stop 😭",
        "Love that for you",
        "Okay yeah I get that",
        "Lowkey agree ngl",
        "That's so funny 💀",
        "Okay sending good vibes your way 🙏",
        "Let's talk more later!",
        "Miss you btw 🥺",
    )


    private val _chatPreviews = MutableStateFlow(buildPreviews())
    val chatPreviews: StateFlow<List<ChatPreview>> = _chatPreviews.asStateFlow()

    fun getUserById(id: String): SearchUser? = MOCK_FRIENDS.find { it.id == id }

    fun getMessagesForUser(userId: String): List<ChatMessage> =
        MOCK_MESSAGES[userId] ?: emptyList()

    fun generateReply(toMessage: String): String =
        MOCK_AUTO_REPLIES.random()

    private fun buildPreviews(): List<ChatPreview> {
        val times = listOf("09:13", "Yesterday", "Mon", "Sun", "Fri", "Thu", "Wed", "Tue", "Mon", "Last week")
        
        return MOCK_FRIENDS.mapIndexed { index, user ->
            val messages = MOCK_MESSAGES[user.id] ?: emptyList()
            val lastMessage = messages.lastOrNull()
            ChatPreview(
                user = user,
                lastMessage = lastMessage?.text ?: "Say hi 👋",
                lastMessageTime = times.getOrElse(index) { "" }
            )
        }
    }
}