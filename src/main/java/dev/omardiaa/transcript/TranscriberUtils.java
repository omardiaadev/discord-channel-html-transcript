package dev.omardiaa.transcript;

import gg.jte.html.escape.Escape;
import gg.jte.output.StringOutput;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dev.omardiaa.transcript.TranscriberConstants.SVG_CHANNEL_ICON;

public final class TranscriberUtils {
  private TranscriberUtils() {}

  private final static Pattern BOLD = Pattern.compile("\\*\\*(?!\\*)(.+?)\\*\\*");
  private final static Pattern UNDERLINE = Pattern.compile("__(?!_)(.+?)__");
  private final static Pattern ITALIC = Pattern.compile("[*_](?![*_])(.+?)[_*]");
  private final static Pattern STRIKE_THROUGH = Pattern.compile("~~(.+?)~~");
  private final static Pattern LINK = Pattern.compile("\\[(.*)]\\((\\S*)\\)");

  private final static Pattern CODE_BLOCK = Pattern.compile("```\\n(.*)\\n```", Pattern.DOTALL);
  private final static Pattern CODE_INLINE = Pattern.compile("`(?!`)(.*)`");

  private final static Pattern HEADER_1 = Pattern.compile("^#\\s(.*)", Pattern.MULTILINE);
  private final static Pattern HEADER_2 = Pattern.compile("^##\\s(.*)", Pattern.MULTILINE);
  private final static Pattern HEADER_3 = Pattern.compile("^###\\s(.*)", Pattern.MULTILINE);
  private final static Pattern SUBTEXT = Pattern.compile("^-# (.*)$", Pattern.MULTILINE);

  private final static Pattern USER = Pattern.compile("&lt;@(\\d+)&gt;");
  private final static Pattern ROLE = Pattern.compile("&lt;@&amp;(\\d+)&gt;");
  private final static Pattern CHANNEL = Pattern.compile("&lt;#(\\d+)&gt;");

  private final static long KB = 1024;
  private final static long MB = KB * KB;
  private final static long GB = MB * KB;

  @NonNull
  public static String parseMarkup(@NonNull Guild guild, @NonNull String message) {
    String current = escapeMessage(message).replaceAll("(?<!```)\\n", "<br>\n");

    List<String> codeMasks = new ArrayList<>();

    current = replace(current, BOLD, m -> "<strong>%s</strong>".formatted(m.group(1)));
    current = replace(current, UNDERLINE, m -> "<u>%s</u>".formatted(m.group(1)));
    current = replace(current, ITALIC, m -> "<em>%s</em>".formatted(m.group(1)));
    current = replace(current, STRIKE_THROUGH, m -> "<s>%s</s>".formatted(m.group(1)));
    current = replace(current, LINK, m -> "<a href=\"%s\" class=\"markup\">%s</a>".formatted(m.group(2), m.group(1)));

    current = replace(
      current, CODE_BLOCK, m -> {
        codeMasks.add("<code class=\"markup\" data-code-style=\"block\">%s</code>".formatted(m.group(1)));
        return "{CODE_" + (codeMasks.size() - 1) + "}";
      });

    current = replace(
      current, CODE_INLINE, m -> {
        codeMasks.add("<code class=\"markup\" data-code-style=\"inline\">%s</code>".formatted(m.group(1)));
        return "{CODE_" + (codeMasks.size() - 1) + "}";
      });

    current = replace(current, HEADER_1, m -> "<h1 class=\"markup\">%s</h1>".formatted(m.group(1)));
    current = replace(current, HEADER_2, m -> "<h2 class=\"markup\">%s</h2>".formatted(m.group(1)));
    current = replace(current, HEADER_3, m -> "<h3 class=\"markup\">%s</h3>".formatted(m.group(1)));
    current = replace(current, SUBTEXT, m -> "<small class=\"markup\">%s</small>".formatted(m.group(1)));

    current = replace(
      current, USER, matcher -> {
        String userId = matcher.group(1);
        User user = guild.getJDA().getUserById(userId);

        if (user == null) {
          return "<span class=\"mention\">@%s</span>".formatted(userId);
        } else {
          return "<a href=\"https://discord.com/users/%s\" class=\"mention\">@%s</a>".formatted(
            userId, user.getEffectiveName());
        }
      });

    current = replace(
      current, ROLE, matcher -> {
        String roleId = matcher.group(1);
        Role role = guild.getRoleById(roleId);

        if (role == null) {
          return "<span class=\"mention\">@unknown-role</span>";
        } else {
          return "<span class=\"mention\" style=\"color: #%1$06X; background-color: #%1$06X10;\" onmouseover=\"this.style.backgroundColor='#%1$06X30';\" onmouseout=\"this.style.backgroundColor='#%1$06X10';\">@%2$s</span>\n".formatted(
            role.getColors().getPrimaryRaw(), role.getName());
        }
      });

    current = replace(
      current, CHANNEL, matcher -> {
        String channelId = matcher.group(1);
        GuildChannel channel = guild.getGuildChannelById(channelId);

        if (channel == null) {
          return "<span class=\"mention\">%s<em>unknown</em></span>\n".formatted(SVG_CHANNEL_ICON);
        } else {
          return "<a href=\"https://discord.com/channels/%s/%s\" class=\"mention\">%s<span class=\"mention__channel-name\">%s</span></a>\n".formatted(
            guild.getId(), channelId, SVG_CHANNEL_ICON, channel.getName());
        }
      });

    for (int i = 0; i < codeMasks.size(); i++) {
      current = current.replace("{CODE_" + i + "}", codeMasks.get(i));
    }

    return current.replaceAll("\n", "");
  }

  @NonNull
  private static String replace(@NonNull String input, @NonNull Pattern pattern,
                                @NonNull Function<Matcher, String> replacementFunc) {
    Matcher matcher = pattern.matcher(input);
    StringBuilder sb = new StringBuilder();

    while (matcher.find()) {
      String replacement = replacementFunc.apply(matcher);
      matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
    }

    matcher.appendTail(sb);
    return sb.toString();
  }

  /**
   * @param bytes
   *   Number of bytes to parse
   *
   * @return {@link String} Parsed String in format: {@code 1024 byte/KB/MB/GB}
   */
  @NonNull
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
   * @param message
   *   The Message Content you want to escape
   *
   * @return {@link String}
   * <br>The Escaped Message Content
   */
  @NonNull
  private static String escapeMessage(@NonNull String message) {
    StringOutput stringOutput = new StringOutput();
    Escape.htmlContent(message, stringOutput);

    return stringOutput.toString();
  }
}
