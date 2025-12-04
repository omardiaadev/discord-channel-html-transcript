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
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static dev.omardiaa.transcript.Constants.*;
import static dev.omardiaa.transcript.TranscriptMockUtil.*;

final class TranscriptTestUtil {
  @NotNull
  public static List<Message> createMessages() {
    List<MessageEmbed> embeds = createMessageEmbeds();

    Message message5 =
      new MessageMockBuilder(AUTHOR_2).withEmbeds(embeds).withComponents(createComponentsV1(), false).build();

    return List.of(
      new MessageMockBuilder(AUTHOR_1)
        .withTimeCreated(TIME_D1)
        .withContent(
          "**This** [Library](https://github.com/skywolfxp/discord-channel-html-transcript) __is__ *Awesome!*").build(),

      new MessageMockBuilder(AUTHOR_2)
        .withComponents(createComponentsV2(), true)
        .withTimeCreated(TIME_D2)
        .build(),

      message5,

      new MessageMockBuilder(AUTHOR_1)
        .withAttachments(List.of(mockAttachment(true), mockAttachment(false)))
        .withReactions(List.of(mockReactionUnicodeEmoji(), mockReactionCustomEmoji(), mockReactionRichCustomEmoji()))
        .withReference(message5)
        .build(),

      new MessageMockBuilder(AUTHOR_2)
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
        .build());
  }

  @NotNull
  public static List<MessageEmbed> createMessageEmbeds() {
    return List.of(
      new EmbedBuilder()
        .setAuthor("Author Name", AVATAR_URL_USER, AVATAR_URL_USER)
        .setTitle("Title")
        .setDescription("Description")
        .addField("#1 Field Name", "#1 Field Value", false)
        .addField("#2 Field Name", "#2 Field Value", false)
        .setImage(AVATAR_URL_USER)
        .setThumbnail(AVATAR_URL_USER)
        .setFooter("Footer", AVATAR_URL_USER)
        .setTimestamp(TIME_D2)
        .setColor(51200)
        .build());
  }

  @NotNull
  public static List<Button> createButtons() {
    return List.of(
      Button.of(ButtonStyle.PRIMARY, "-", "Primary"),
      Button.of(ButtonStyle.SECONDARY, "-", "Secondary"),
      Button.of(ButtonStyle.SUCCESS, "-", "Success"),
      Button.of(ButtonStyle.DANGER, "-", "Danger"),
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
          Button.link("https://github.com/omardiaadev/discord-channel-html-transcript", "Star The Project")
                .withEmoji(Emoji.fromFormatted("⭐")),
          TextDisplay.of("""
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

      Container.of(createMediaGallery(3)));
  }
}
