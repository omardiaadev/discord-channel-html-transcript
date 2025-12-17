package dev.omardiaa.transcript;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.interactions.InteractionType;
import net.dv8tion.jda.api.requests.restaction.pagination.MessagePaginationAction;
import org.jspecify.annotations.NonNull;
import org.mockito.ArgumentMatchers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static dev.omardiaa.transcript.TranscriberTestConstants.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TranscriberMockUtil {
  static GuildMessageChannel channel = mock(GuildMessageChannel.class);

  @NonNull
  static JDA mockJDA() {
    JDA jda = mock(JDA.class);
    when(jda.getUserById(AUTHOR_1.getId())).thenReturn(AUTHOR_1);

    return jda;
  }

  @NonNull
  static Guild mockGuild() {
    Guild guild = mock(Guild.class);
    when(guild.getName()).thenReturn("Discord Channel HTML Transcript");
    when(guild.getIconUrl()).thenReturn(AVATAR_URL_BOT);
    when(guild.getGuildChannelById("420")).thenReturn(channel);

    JDA jda = mockJDA();
    when(guild.getJDA()).thenReturn(jda);

    Role role = mockRole();
    when(guild.getRoleById("420")).thenReturn(role);

    SelfMember selfMember = mock(SelfMember.class);
    when(selfMember.hasPermission(channel, Permission.MESSAGE_HISTORY)).thenReturn(true);
    when(guild.getSelfMember()).thenReturn(selfMember);

    return guild;
  }

  static @NonNull GuildMessageChannel mockChannel(@NonNull List<Message> messages) {
    when(channel.getId()).thenReturn("420");
    when(channel.getName()).thenReturn("discord-channel-html-transcript");

    Guild guild = mockGuild();
    when(channel.getGuild()).thenReturn(guild);

    MessagePaginationAction messagePaginationAction = mock(MessagePaginationAction.class);
    when(channel.getIterableHistory()).thenReturn(messagePaginationAction);
    when(channel.getIterableHistory().isEmpty()).thenReturn(messages.isEmpty());
    when(messagePaginationAction.takeWhileAsync(ArgumentMatchers.any()))
      .thenReturn(CompletableFuture.completedFuture(messages));

    return channel;
  }

  @NonNull
  static User mockAuthor(@NonNull String id, @NonNull String name, @NonNull String effectiveName,
                         @NonNull String effectiveAvatarUrl, boolean isBot) {
    User user = mock(User.class);
    when(user.getId()).thenReturn(id);
    when(user.getName()).thenReturn(name);
    when(user.getEffectiveName()).thenReturn(effectiveName);
    when(user.getEffectiveAvatarUrl()).thenReturn(effectiveAvatarUrl);
    when(user.isBot()).thenReturn(isBot);

    return user;
  }

  @NonNull
  static Role mockRole() {
    Role role = mock(Role.class);
    when(role.getName()).thenReturn("Cool Role");
    when(role.getColors()).thenReturn(new RoleColors(51200, 51200, 51200));

    return role;
  }

  static Message.@NonNull Attachment mockAttachment(boolean isImage) {
    Message.Attachment attachment = mock(Message.Attachment.class);
    when(attachment.getUrl()).thenReturn(AVATAR_URL_USER);
    when(attachment.getSize()).thenReturn(420);
    when(attachment.isImage()).thenReturn(isImage);
    when(attachment.getFileName()).thenReturn(isImage ? "image.png" : "file.txt");

    return attachment;
  }

  @SuppressWarnings("deprecation")
  static Message.@NonNull Interaction mockInteraction(@NonNull User user) {
    Message.Interaction interaction = mock(Message.Interaction.class);
    when(interaction.getUser()).thenReturn(user);
    when(interaction.getType()).thenReturn(InteractionType.COMMAND);
    when(interaction.getName()).thenReturn("attachments");

    return interaction;
  }
}
