package dev.omardiaa.transcript;

import net.dv8tion.jda.api.components.tree.MessageComponentTree;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.entities.emoji.EmojiUnion;
import org.jspecify.annotations.NonNull;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static dev.omardiaa.transcript.TranscriberMockUtil.mockGuild;
import static dev.omardiaa.transcript.TranscriberTestConstants.TIME_D2;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MessageMockBuilder {
  private final Message message = mock(Message.class);

  MessageMockBuilder(@NonNull User author) {
    Guild guild = mockGuild();

    when(message.getGuild()).thenReturn(guild);
    when(message.getAuthor()).thenReturn(author);
    when(message.getContentRaw()).thenReturn("");
    when(message.getTimeCreated()).thenReturn(TIME_D2);
    when(message.getId()).thenReturn(String.valueOf(new Random().nextLong(100000000000000000L, 999999999999999999L)));
  }

  MessageMockBuilder withContent(@NonNull String contentRaw) {
    when(message.getContentRaw()).thenReturn(contentRaw);
    return this;
  }

  MessageMockBuilder withMessageEmbeds(@NonNull MessageEmbed... embeds) {
    when(message.getEmbeds()).thenReturn(List.of(embeds));
    return this;
  }

  MessageMockBuilder withAttachments(@NonNull List<Message.Attachment> attachments) {
    when(message.getAttachments()).thenReturn(attachments);
    return this;
  }

  MessageMockBuilder withReactions(@NonNull Emoji... emojis) {
    List<MessageReaction> reactions = new ArrayList<>();

    for (Emoji emoji : emojis) {
      MessageReaction reaction = mock(MessageReaction.class);
      when(reaction.getEmoji()).thenReturn((EmojiUnion) emoji);
      when(reaction.getCount()).thenReturn(69);

      reactions.add(reaction);
    }

    when(message.getReactions()).thenReturn(reactions);

    return this;
  }

  MessageMockBuilder withReferencedMessage(@NonNull Message referencedMessage) {
    when(message.getReferencedMessage()).thenReturn(referencedMessage);
    return this;
  }

  MessageMockBuilder withTimeCreated(@NonNull OffsetDateTime timeCreated) {
    when(message.getTimeCreated()).thenReturn(timeCreated);
    return this;
  }

  MessageMockBuilder withTimeEdited(@NonNull OffsetDateTime timeEdited) {
    when(message.getTimeEdited()).thenReturn(timeEdited);
    return this;
  }

  MessageMockBuilder withComponents(@NonNull MessageComponentTree componentTree, boolean isUsingComponentsV2) {
    when(message.getComponentTree()).thenReturn(componentTree);
    when(message.getComponents()).thenReturn(componentTree.getComponents());
    when(message.isUsingComponentsV2()).thenReturn(isUsingComponentsV2);
    return this;
  }

  @SuppressWarnings("deprecation")
  MessageMockBuilder withInteractionMetadata(Message.@NonNull Interaction interaction) {
    when(message.getInteraction()).thenReturn(interaction);
    return this;
  }

  Message build() {
    return message;
  }
}
