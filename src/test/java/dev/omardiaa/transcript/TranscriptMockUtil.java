package dev.omardiaa.transcript;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.*;
import net.dv8tion.jda.api.interactions.InteractionType;
import org.jetbrains.annotations.NotNull;

import static dev.omardiaa.transcript.Constants.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TranscriptMockUtil {
  @NotNull
  public static JDA mockJDA(@NotNull User user) {
    JDA jda = mock(JDA.class);
    when(jda.getUserById(user.getId())).thenReturn(user);

    return jda;
  }

  @NotNull
  public static Guild mockGuild() {
    return new GuildMockBuilder().withJDA(mockJDA(AUTHOR_1)).withRole("420", mockRole("Admin", 51200)).build();
  }

  @NotNull
  public static TextChannel mockTextChannel(@NotNull String name) {
    TextChannel textChannel = mock(TextChannel.class);
    when(textChannel.getName()).thenReturn(name);

    Guild guild = mockGuild();
    when(textChannel.getGuild()).thenReturn(guild);

    return textChannel;
  }

  @NotNull
  public static User mockAuthor(String id, String name, String effectiveName, String effectiveAvatarUrl,
                                boolean isBot) {
    User user = mock(User.class);
    when(user.getId()).thenReturn(id);
    when(user.getName()).thenReturn(name);
    when(user.getEffectiveName()).thenReturn(effectiveName);
    when(user.getEffectiveAvatarUrl()).thenReturn(effectiveAvatarUrl);
    when(user.isBot()).thenReturn(isBot);

    return user;
  }

  @NotNull
  public static Role mockRole(String name, int colorRaw) {
    Role role = mock(Role.class);
    when(role.getName()).thenReturn(name);
    when(role.getColorRaw()).thenReturn(colorRaw);

    return role;
  }

  @NotNull
  public static Message.Attachment mockAttachment(boolean isImage) {
    Message.Attachment attachment = mock(Message.Attachment.class);
    when(attachment.isImage()).thenReturn(isImage);
    when(attachment.getUrl()).thenReturn(AVATAR_URL_USER);
    when(attachment.getSize()).thenReturn(420);
    when(attachment.getFileName()).thenReturn(isImage ? "image.png" : "file.txt");

    return attachment;
  }

  @NotNull
  public static MessageReaction mockReactionUnicodeEmoji() {
    EmojiUnion emojiUnion = mock(EmojiUnion.class);
    UnicodeEmoji unicodeEmoji = mock(UnicodeEmoji.class);
    MessageReaction reaction = mock(MessageReaction.class);

    when(reaction.getCount()).thenReturn(69);
    when(reaction.getEmoji()).thenReturn(emojiUnion);
    when(reaction.getEmoji().getType()).thenReturn(Emoji.Type.UNICODE);
    when(reaction.getEmoji().asUnicode()).thenReturn(unicodeEmoji);
    when(reaction.getEmoji().asUnicode().getAsCodepoints()).thenReturn("U+1F49A");

    return reaction;
  }

  @NotNull
  public static MessageReaction mockReactionCustomEmoji() {
    EmojiUnion emojiUnion = mock(EmojiUnion.class);
    CustomEmoji customEmoji = mock(CustomEmoji.class);
    MessageReaction reaction = mock(MessageReaction.class);

    when(reaction.getCount()).thenReturn(69);
    when(reaction.getEmoji()).thenReturn(emojiUnion);
    when(reaction.getEmoji().getType()).thenReturn(Emoji.Type.CUSTOM);
    when(reaction.getEmoji().asCustom()).thenReturn(customEmoji);
    when(reaction.getEmoji().asCustom().getImageUrl()).thenReturn(AVATAR_URL_BOT);

    return reaction;
  }

  @NotNull
  public static MessageReaction mockReactionRichCustomEmoji() {
    EmojiUnion emojiUnion = mock(EmojiUnion.class);
    RichCustomEmoji richCustomEmoji = mock(RichCustomEmoji.class);
    MessageReaction reaction = mock(MessageReaction.class);

    when(reaction.getCount()).thenReturn(69);
    when(reaction.getEmoji()).thenReturn(emojiUnion);
    when(reaction.getEmoji().getType()).thenReturn(Emoji.Type.CUSTOM);
    when(reaction.getEmoji().asCustom()).thenReturn(richCustomEmoji);
    when(reaction.getEmoji().asCustom().getImageUrl()).thenReturn(RICH_CUSTOM_EMOJI);

    return reaction;
  }

  @NotNull
  @SuppressWarnings("deprecation")
  public static Message.Interaction mockInteraction(@NotNull User user) {
    Message.Interaction interaction = mock(Message.Interaction.class);
    when(interaction.getUser()).thenReturn(user);
    when(interaction.getType()).thenReturn(InteractionType.COMMAND);
    when(interaction.getName()).thenReturn("show-attachments");

    return interaction;
  }
}
