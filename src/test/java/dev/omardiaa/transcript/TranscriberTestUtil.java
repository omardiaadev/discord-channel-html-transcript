package dev.omardiaa.transcript;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
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
import org.jspecify.annotations.NonNull;

import java.util.Collections;
import java.util.List;

import static dev.omardiaa.transcript.TranscriberTestConstants.*;

class TranscriberTestUtil {
  @NonNull
  static List<Message> createMessages() {
    Message message = new MessageMockBuilder(AUTHOR_1)
      .withComponents(createComponentsV2(), true)
      .withTimeCreated(TIME_D2)
      .withTimeEdited(TIME_D2)
      .build();

    return List.of(
      new MessageMockBuilder(AUTHOR_2)
        .withAttachments(List.of(TranscriberMockUtil.mockAttachment(true), TranscriberMockUtil.mockAttachment(false)))
        .withReactions(Emoji.fromUnicode("U+1F49A"), Emoji.fromCustom("catDance", 1364983213554008146L, true))
        .withInteractionMetadata(TranscriberMockUtil.mockInteraction(AUTHOR_1))
        .build(),

      new MessageMockBuilder(AUTHOR_2)
        .withMessageEmbeds(createMessageEmbed())
        .withComponents(createComponentsV1(), false)
        .withReferencedMessage(message)
        .build(),

      message);
  }

  @NonNull
  static MessageEmbed createMessageEmbed() {
    return new EmbedBuilder()
      .setAuthor("Author Name", AVATAR_URL_USER, AVATAR_URL_USER)
      .setTitle("Title", "https://github.com/omardiaadev")
      .setDescription("Description")
      .addField("#1 Field Name", "#1 Field Value", true)
      .addBlankField(true)
      .addField("#2 Field Name", "#2 Field Value", true)
      .setImage(AVATAR_URL_USER)
      .setThumbnail(AVATAR_URL_USER)
      .setFooter("Footer", AVATAR_URL_USER)
      .setTimestamp(TIME_D2)
      .setColor(21712)
      .build();
  }

  @NonNull
  static List<Button> createButtons() {
    return List.of(
      Button.primary("-", "All"),
      Button.secondary("-", "Button"),
      Button.success("-", "Variants"),
      Button.danger("-", "Displayed"),
      Button.link("https://github.com/omardiaadev/discord-channel-html-transcript", "Star The Project")
            .withEmoji(Emoji.fromUnicode("⭐")));
  }

  @NonNull
  static MessageComponentTree createComponentsV1() {
    List<Button> buttons = createButtons();
    List<Button> buttonsDisabled = buttons.stream().map(Button::asDisabled).toList();
    StringSelectMenu selectMenu = StringSelectMenu.create("-").addOption("Label", "Value").build();

    return MessageComponentTree.of(
      List.of(ActionRow.of(buttons), ActionRow.of(buttonsDisabled), ActionRow.of(selectMenu)));
  }

  @NonNull
  static MessageComponentTree createComponentsV2() {
    return MessageComponentTree.of(
      Container.of(
        TextDisplay.of("# ComponentsV2 Are Here"),

        Separator.createDivider(Separator.Spacing.LARGE),

        Section.of(
          Button.link("https://github.com/omardiaadev", "Link"),
          TextDisplay.of("""
                         # Big Header
                         ## Medium Header
                         ### Small Header
                         
                         `System.out.println("discord-channel-html-transcript");`
                         ```
                         public static void main(String args[]) {
                             System.out.println("Hello World!");
                           }
                         }
                         ```
                         
                         **Users:** <@974748803305455627> <@545902760453996546>
                         **Roles:** <@&420> <@&0>
                         **Channels:** <#420> <#0>
                         -# Subtext
                         """)),

        Separator.createDivider(Separator.Spacing.LARGE),

        Section.of(
          Thumbnail.fromUrl("https://avatars.githubusercontent.com/u/70555240"),
          TextDisplay.of(
            "**This** [Library](https://github.com/omardiaadev/discord-channel-html-transcript) __is__ *Awesome!*")),

        Separator.createDivider(Separator.Spacing.LARGE),

        ActionRow.of(createButtons())),

      Container.of(
        TextDisplay.of("# Dynamic Media Gallery"),

        Separator.createDivider(Separator.Spacing.SMALL),

        MediaGallery.of(
          Collections.nCopies(3, MediaGalleryItem.fromUrl("https://avatars.githubusercontent.com/u/70555240")))));
  }
}
