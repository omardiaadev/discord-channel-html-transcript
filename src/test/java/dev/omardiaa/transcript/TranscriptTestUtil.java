package dev.omardiaa.transcript;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.components.container.Container;
import net.dv8tion.jda.api.components.mediagallery.MediaGallery;
import net.dv8tion.jda.api.components.mediagallery.MediaGalleryItem;
import net.dv8tion.jda.api.components.section.Section;
import net.dv8tion.jda.api.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.components.separator.Separator;
import net.dv8tion.jda.api.components.textdisplay.TextDisplay;
import net.dv8tion.jda.api.components.thumbnail.Thumbnail;
import net.dv8tion.jda.api.components.tree.MessageComponentTree;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static dev.omardiaa.transcript.TranscriptMockUtil.*;

final class TranscriptTestUtil {
  private static final User AUTHOR_1 = mockAuthor("974748803305455627", "Omar Diaa", AVATAR_URL_USER, false);
  private static final User AUTHOR_2 = mockAuthor("974748803305455627", "VORTEX", AVATAR_URL_BOT, true);

  @NotNull
  public static Guild createGuild() {
    GuildMockBuilder guildBuilder = new GuildMockBuilder().withJDA(mockJDA(AUTHOR_1));

    return guildBuilder
      .withGuildChannel("420", mockTextChannel("discord-channel-html-transcript", guildBuilder.build()))
      .withRole("420", mockRole("Admin", 51200))
      .build();
  }

  @NotNull
  public static List<Message> createMessages(@NotNull Guild guild) {
    Message.Attachment messageAttachmentImage = mockAttachment(true);
    Message.Attachment messageAttachmentFile = mockAttachment(false);

    List<MessageEmbed> embeds = new ArrayList<>();
    embeds.add(createMessageEmbed());

    List<Message> messages = new ArrayList<>();

    Message message1 = new MessageMockBuilder(AUTHOR_1).withContent("**This**").build();
    messages.add(message1);

    Message message2 = new MessageMockBuilder(AUTHOR_1)
      .withContent("[Library](https://github.com/skywolfxp/discord-channel-html-transcript)")
      .build();
    messages.add(message2);

    Message message3 = new MessageMockBuilder(AUTHOR_1).withContent("__is__ *Awesome!*").build();
    messages.add(message3);

    Message message4 =
      new MessageMockBuilder(AUTHOR_2).withEmbeds(embeds).withComponentsV2(createComponentsV2()).build();
    messages.add(message4);

    Message message5 =
      new MessageMockBuilder(AUTHOR_2).withEmbeds(embeds).withComponentsV1(createComponentsV1()).build();
    messages.add(message5);

    Message message6 = new MessageMockBuilder(AUTHOR_1)
      .withAttachments(List.of(messageAttachmentImage, messageAttachmentFile))
      .withReactions(List.of(mockReactionUnicodeEmoji(), mockReactionCustomEmoji(), mockReactionRichCustomEmoji()))
      .withReference(message5)
      .build();
    messages.add(message6);

    Message message7 = new MessageMockBuilder(AUTHOR_2)
      .withGuild(guild)
      .withContent("""
                   # Big Header
                   ## Medium Header
                   ### Small Header
                   
                   `System.out.println("discord-channel-html-transcript");`
                   ```
                   public static void main(String args[]) {
                            System.out.println("discord-channel-html-transcript");
                        }
                   }
                   ```
                   
                   **User Mentions:** <@545902760453996546> <@0>
                   **Role Mentions:** <@&420> <@&0>
                   **Channel Mentions:** <#420> <#0>
                   """)
      .withInteractionMetadata(mockInteraction(AUTHOR_1))
      .build();
    messages.add(message7);

    return messages;
  }

  @NotNull
  public static MessageEmbed createMessageEmbed() {
    return new EmbedBuilder()
      .setAuthor("Author Name", AVATAR_URL_USER, AVATAR_URL_USER)
      .setTitle("Title")
      .setDescription("Description")
      .addField("#1 Field Name", "#1 Field Value", false)
      .addField("#2 Field Name", "#2 Field Value", false)
      .setImage(AVATAR_URL_USER)
      .setThumbnail(AVATAR_URL_USER)
      .setFooter("Footer", AVATAR_URL_USER)
      .setTimestamp(TIME)
      .setColor(51200)
      .build();
  }

  @NotNull
  public static List<Button> createButtons() {
    return List.of(
      Button.of(ButtonStyle.PRIMARY, "-", "Primary", Emoji.fromUnicode("💠")),
      Button.of(ButtonStyle.SECONDARY, "-", "Secondary", Emoji.fromUnicode("💠")),
      Button.of(ButtonStyle.SUCCESS, "-", "Success", Emoji.fromUnicode("💠")),
      Button.of(ButtonStyle.DANGER, "-", "Danger", Emoji.fromUnicode("💠")),
      Button.of(ButtonStyle.LINK, "https://github.com/omardiaadev", "Link", Emoji.fromUnicode("🔗")));
  }

  @NotNull
  public static MediaGallery createMediaGallery(int mediaGalleryItemCount) {
    return MediaGallery.of(Collections.nCopies(
      mediaGalleryItemCount, MediaGalleryItem.fromUrl("https://avatars.githubusercontent.com/u/70555240")));
  }

  @NotNull
  public static MessageComponentTree createComponentsV1() {
    List<Button> buttons = createButtons();

    List<Button> buttonsDisabled = buttons.stream().map(Button::asDisabled).toList();

    StringSelectMenu selectMenu = StringSelectMenu.create("-").addOption("Label", "Value").build();

    return MessageComponentTree.of(
      List.of(ActionRow.of(buttons), ActionRow.of(buttonsDisabled), ActionRow.of(selectMenu)));
  }

  @NotNull
  public static MessageComponentTree createComponentsV2() {
    return MessageComponentTree.of(
      Container.of(
        TextDisplay.of("# ComponentsV2"),

        Separator.createDivider(Separator.Spacing.SMALL),

        Section.of(
          Button.link("https://github.com/omardiaadev", "Button"),
          TextDisplay.of("""
                         # Section With Button
                         Section
                         """)),

        Separator.createDivider(Separator.Spacing.SMALL),

        Section.of(
          Thumbnail.fromUrl("https://avatars.githubusercontent.com/u/70555240"),
          TextDisplay.of("""
                         # Section With Thumbnail
                         Section
                         """)),

        Separator.createDivider(Separator.Spacing.LARGE),

        ActionRow.of(createButtons())),

      Container.of(
        createMediaGallery(2),

        Separator.createDivider(Separator.Spacing.SMALL),

        createMediaGallery(3),

        Separator.createDivider(Separator.Spacing.SMALL),

        createMediaGallery(4),

        Separator.createDivider(Separator.Spacing.SMALL),

        createMediaGallery(5)));
  }
}
