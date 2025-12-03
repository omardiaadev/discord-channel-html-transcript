package dev.skywolfxp.transcript;

import gg.jte.html.escape.Escape;
import gg.jte.output.StringOutput;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranscriptUtils {
  private final static Pattern UNDERLINE = Pattern.compile("__(?!_)(.+?)__");
  private final static Pattern BOLD = Pattern.compile("\\*\\*(?!\\*)(.+?)\\*\\*");
  private final static Pattern ITALIC = Pattern.compile("[*_](?![*_])(.+?)[_*]");
  private final static Pattern STRIKE_THROUGH = Pattern.compile("~~(.+?)~~");
  private final static Pattern LINK = Pattern.compile("\\[(.*)]\\((\\S*)\\)");

  private final static Pattern CODE_BLOCK = Pattern.compile("```\\n(.*)\\n```", Pattern.DOTALL);
  private final static Pattern CODE_INLINE = Pattern.compile("`(?!`)(.*)`");

  private final static Pattern HEADER_1 = Pattern.compile("^#\\s(.*)", Pattern.MULTILINE);
  private final static Pattern HEADER_2 = Pattern.compile("^##\\s(.*)", Pattern.MULTILINE);
  private final static Pattern HEADER_3 = Pattern.compile("^###\\s(.*)", Pattern.MULTILINE);

  private final static Pattern SUBTEXT = Pattern.compile("^-# (.*)$", Pattern.MULTILINE);

  private final static Pattern MENTION_USER = Pattern.compile("&lt;@(\\d+)&gt;");
  private final static Pattern MENTION_ROLE = Pattern.compile("&lt;@&amp;(\\d+)&gt;");
  private final static Pattern MENTION_CHANNEL = Pattern.compile("&lt;#(\\d+)&gt;");

  private final static long KB = 1024;
  private final static long MB = KB * KB;
  private final static long GB = MB * KB;

  // TODO: Take a look at @NotNull on Guild parameter
  @NotNull
  public static String parseMarkup(Guild guild, @NotNull String message) {
    String escapedMessage = escapeMessage(message).replaceAll("(?<!```)\\n", "<br>\n");

    Matcher matcher = UNDERLINE.matcher(escapedMessage);
    while (matcher.find()) {
      escapedMessage = escapedMessage.replace(matcher.group(), "<u>%s</u>".formatted(matcher.group(1)));
    }

    matcher = BOLD.matcher(escapedMessage);
    while (matcher.find()) {
      escapedMessage = escapedMessage.replace(matcher.group(), "<strong>%s</strong>".formatted(matcher.group(1)));
    }

    matcher = ITALIC.matcher(escapedMessage);
    while (matcher.find()) {
      escapedMessage = escapedMessage.replace(matcher.group(), "<em>%s</em>".formatted(matcher.group(1)));
    }

    matcher = STRIKE_THROUGH.matcher(escapedMessage);
    while (matcher.find()) {
      escapedMessage = escapedMessage.replace(matcher.group(), "<s>%s</s>".formatted(matcher.group(1)));
    }

    matcher = LINK.matcher(escapedMessage);
    while (matcher.find()) {
      escapedMessage = escapedMessage.replace(
        matcher.group(), "<a href=\"%s\" class=\"markup__link\">%s</a>".formatted(matcher.group(2), matcher.group(1)));
    }

    matcher = HEADER_1.matcher(escapedMessage);
    while (matcher.find()) {
      escapedMessage =
        escapedMessage.replace(matcher.group(), "<h1 class=\"markup__header\">%s</h1>".formatted(matcher.group(1)));
    }

    matcher = HEADER_2.matcher(escapedMessage);
    while (matcher.find()) {
      escapedMessage =
        escapedMessage.replace(matcher.group(), "<h2 class=\"markup__header\">%s</h2>".formatted(matcher.group(1)));
    }

    matcher = HEADER_3.matcher(escapedMessage);
    while (matcher.find()) {
      escapedMessage =
        escapedMessage.replace(matcher.group(), "<h3 class=\"markup__header\">%s</h3>".formatted(matcher.group(1)));
    }

    matcher = SUBTEXT.matcher(escapedMessage);
    while (matcher.find()) {
      escapedMessage = escapedMessage.replace(
        matcher.group(), "<small class=\"markup__subtext\">%s</small>".formatted(matcher.group(1)));
    }

    matcher = CODE_BLOCK.matcher(escapedMessage);
    while (matcher.find()) {
      escapedMessage = escapedMessage.replace(
        matcher.group(), "<code class=\"markup__code-block\">%s</code>".formatted(matcher.group(1)));
    }

    matcher = CODE_INLINE.matcher(escapedMessage);
    while (matcher.find()) {
      escapedMessage = escapedMessage.replace(
        matcher.group(), "<code class=\"markup__code-inline\">%s</code>".formatted(matcher.group(1)));
    }

    matcher = MENTION_USER.matcher(escapedMessage);
    while (matcher.find()) {
      String userId = matcher.group(1);
      User user = guild.getJDA().getUserById(userId);

      if (user == null) {
        escapedMessage = escapedMessage.replace(
          matcher.group(), """
                           <span class="mention">
                             <@%s>
                           </span>
                           """.formatted(userId));

        continue;
      }

      escapedMessage = escapedMessage.replace(
        matcher.group(), """
                         <a href="https://discord.com/users/%s" class="mention">
                           @%s
                         </a>
                         """.formatted(userId, user.getEffectiveName()));
    }

    matcher = MENTION_ROLE.matcher(escapedMessage);
    while (matcher.find()) {
      String roleId = matcher.group(1);
      Role role = guild.getRoleById(roleId);

      if (role == null) {
        escapedMessage = escapedMessage.replace(
          matcher.group(), """
                           <span class="mention">
                             @unknown-role
                           </span>
                           """);

        continue;
      }

      escapedMessage = escapedMessage.replace(
        matcher.group(), """
                         <span class="mention" style="color: #%1$06X; background-color: #%1$06X10;" onmouseover="this.style.backgroundColor='#%1$06X30';" onmouseout="this.style.backgroundColor='#%1$06X10';">@%2$s</span>
                         """.formatted(role.getColorRaw(), role.getName()));
    }

    matcher = MENTION_CHANNEL.matcher(escapedMessage);
    while (matcher.find()) {
      String channelId = matcher.group(1);
      GuildChannel channel = guild.getGuildChannelById(channelId);

      if (channel == null) {
        escapedMessage = escapedMessage.replace(
          matcher.group(), """
                           <span class="mention">
                             <svg
                               xmlns="http://www.w3.org/2000/svg"
                               viewBox="0 0 24 24"
                               fill="currentColor"
                               class="mention__channel-icon"
                             >
                               <path
                                 fill-rule="evenodd"
                                 d="M10.99 3.16A1 1 0 1 0 9 2.84L8.15 8H4a1 1 0 0 0 0 2h3.82l-.67 4H3a1 1 0 1 0 0 2h3.82l-.8 4.84a1 1 0 0 0 1.97.32L8.85 16h4.97l-.8 4.84a1 1 0 0 0 1.97.32l.86-5.16H20a1 1 0 1 0 0-2h-3.82l.67-4H21a1 1 0 1 0 0-2h-3.82l.8-4.84a1 1 0 1 0-1.97-.32L15.15 8h-4.97l.8-4.84ZM14.15 14l.67-4H9.85l-.67 4h4.97Z"
                               />
                             </svg>
                           
                             <em>unknown</em>
                           </span>
                           """);

        continue;
      }

      escapedMessage = escapedMessage.replace(
        matcher.group(), """
                         <a href="https://discord.com/channels/%s/%s" class="mention">
                           <svg
                             xmlns="http://www.w3.org/2000/svg"
                             viewBox="0 0 24 24"
                             fill="currentColor"
                             class="mention__channel-icon"
                           >
                             <path
                               fill-rule="evenodd"
                               d="M10.99 3.16A1 1 0 1 0 9 2.84L8.15 8H4a1 1 0 0 0 0 2h3.82l-.67 4H3a1 1 0 1 0 0 2h3.82l-.8 4.84a1 1 0 0 0 1.97.32L8.85 16h4.97l-.8 4.84a1 1 0 0 0 1.97.32l.86-5.16H20a1 1 0 1 0 0-2h-3.82l.67-4H21a1 1 0 1 0 0-2h-3.82l.8-4.84a1 1 0 1 0-1.97-.32L15.15 8h-4.97l.8-4.84ZM14.15 14l.67-4H9.85l-.67 4h4.97Z"
                             />
                           </svg>
                         
                           <span class="mention__channel-name">%s</span>
                         </a>
                         """.formatted(guild.getId(), channelId, channel.getName()));
    }

    escapedMessage = escapedMessage.replaceAll("\n", "");

    return escapedMessage;
  }

  /**
   * @param bytes
   *   Number of bytes to parse
   *
   * @return {@link String} Parsed String in format: {@code 1024 byte/KB/MB/GB}
   */
  @NotNull
  public static String formatBytes(int bytes) {
    if (bytes < KB) {
      return "%s bytes".formatted(bytes);
    } else if (bytes < MB) {
      return "%.2f KB".formatted((double) bytes / KB);
    } else if (bytes < GB) {
      return "%.2f MB".formatted((double) bytes / MB);
    } else {
      return "%.2f GB".formatted((double) bytes / GB);
    }
  }

  /**
   * Escapes HTML Characters for XSS Protection
   *
   * @param messageContent
   *   The Message Content you want to escape
   *
   * @return {@link String}
   * <br>The Escaped Message Content
   */
  @NotNull
  private static String escapeMessage(@NotNull String messageContent) {
    StringOutput stringOutput = new StringOutput();
    Escape.htmlContent(messageContent, stringOutput);

    return stringOutput.toString();
  }
}