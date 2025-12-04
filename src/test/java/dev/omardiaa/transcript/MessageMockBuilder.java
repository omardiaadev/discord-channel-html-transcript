package dev.omardiaa.transcript;

import net.dv8tion.jda.api.components.tree.MessageComponentTree;
import net.dv8tion.jda.api.entities.*;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;

import static dev.omardiaa.transcript.Constants.TIME_D2;
import static dev.omardiaa.transcript.TranscriptMockUtil.mockGuild;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

final class MessageMockBuilder {
  private final Message message = mock(Message.class);

  public MessageMockBuilder(@NotNull User author) {
    Guild guild = mockGuild();

    when(message.getGuild()).thenReturn(guild);
    when(message.getAuthor()).thenReturn(author);
    when(message.getContentRaw()).thenReturn("");
    when(message.getTimeCreated()).thenReturn(TIME_D2);
    when(message.getId()).thenReturn(String.valueOf(new Random().nextLong(100000000000000000L, 999999999999999999L)));
  }

  public MessageMockBuilder withContent(@NotNull String contentRaw) {
    when(message.getContentRaw()).thenReturn(contentRaw);
    return this;
  }

  public MessageMockBuilder withEmbeds(@NotNull List<MessageEmbed> embeds) {
    when(message.getEmbeds()).thenReturn(embeds);
    return this;
  }

  public MessageMockBuilder withAttachments(@NotNull List<Message.Attachment> attachments) {
    when(message.getAttachments()).thenReturn(attachments);
    return this;
  }

  public MessageMockBuilder withReactions(@NotNull List<MessageReaction> messageReactions) {
    when(message.getReactions()).thenReturn(messageReactions);
    return this;
  }

  public MessageMockBuilder withReference(@NotNull Message referencedMessage) {
    when(message.getReferencedMessage()).thenReturn(referencedMessage);
    return this;
  }

  public MessageMockBuilder withTimeCreated(@NotNull OffsetDateTime timeCreated) {
    when(message.getTimeCreated()).thenReturn(timeCreated);
    return this;
  }

  public MessageMockBuilder withComponents(@NotNull MessageComponentTree componentTree, boolean isUsingComponentsV2) {
    when(message.getComponentTree()).thenReturn(componentTree);
    when(message.getComponents()).thenReturn(componentTree.getComponents());
    when(message.isUsingComponentsV2()).thenReturn(isUsingComponentsV2);
    return this;
  }

  @SuppressWarnings("deprecation")
  public MessageMockBuilder withInteractionMetadata(@NotNull Message.Interaction interaction) {
    when(message.getInteraction()).thenReturn(interaction);
    return this;
  }

  public Message build() {
    return message;
  }
}
