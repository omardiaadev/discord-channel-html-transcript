package dev.skywolfxp.transcript;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static dev.skywolfxp.transcript.TranscriptMockUtil.*;

final class TranscriptTestUtil {
  private static final User AUTHOR_1 = mockAuthor("545902760453996546", "SkyWolfXP", AVATAR_URL_USER, false);
  private static final User AUTHOR_2 = mockAuthor("974748803305455627", "VORTEX", AVATAR_URL_BOT, true);
  
  @NotNull
  public static Guild createGuild() {
    GuildMockBuilder guildBuilder = new GuildMockBuilder().withJDA(mockJDA(AUTHOR_1));
    guildBuilder.withGuildChannel("420", mockTextChannel("discord-channel-html-transcript", guildBuilder.build()));
    guildBuilder.withRole("420", mockRole("Admin", 51200));
    
    return guildBuilder.build();
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
    
    Message message4 = new MessageMockBuilder(AUTHOR_2).withEmbeds(embeds).withActionRows(createActionRows()).build();
    messages.add(message4);
    
    Message message5 = new MessageMockBuilder(AUTHOR_1)
      .withAttachments(List.of(messageAttachmentImage, messageAttachmentFile))
      .withReactions(List.of(mockReactionUnicodeEmoji(), mockReactionCustomEmoji(), mockReactionRichCustomEmoji()))
      .withReference(message4)
      .build();
    messages.add(message5);
    
    Message message6 = new MessageMockBuilder(AUTHOR_2)
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
    messages.add(message6);
    
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
  public static List<ActionRow> createActionRows() {
    List<Button> buttonsEnabled = new ArrayList<>();
    buttonsEnabled.add(Button.of(ButtonStyle.PRIMARY, "-", "Primary", Emoji.fromUnicode("💠")));
    buttonsEnabled.add(Button.of(ButtonStyle.SECONDARY, "-", "Secondary", Emoji.fromUnicode("💠")));
    buttonsEnabled.add(Button.of(ButtonStyle.SUCCESS, "-", "Success", Emoji.fromUnicode("💠")));
    buttonsEnabled.add(Button.of(ButtonStyle.DANGER, "-", "Danger", Emoji.fromUnicode("💠")));
    buttonsEnabled.add(Button.of(ButtonStyle.LINK, "https://github.com/skywolfxp", "Link", Emoji.fromUnicode("🔗")));
    
    List<Button> buttonsDisabled = buttonsEnabled.stream().map(Button::asDisabled).toList();
    
    StringSelectMenu selectMenu = StringSelectMenu.create("-").addOption("Label", "Value").build();
    
    return List.of(ActionRow.of(buttonsEnabled), ActionRow.of(buttonsDisabled), ActionRow.of(selectMenu));
  }
}
