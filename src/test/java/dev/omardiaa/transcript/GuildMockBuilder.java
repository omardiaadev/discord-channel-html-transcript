package dev.omardiaa.transcript;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import org.jetbrains.annotations.NotNull;

import static dev.omardiaa.transcript.TranscriptMockUtil.AVATAR_URL_BOT;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

final class GuildMockBuilder {
  private final Guild guild = mock(Guild.class);

  public GuildMockBuilder() {
    when(guild.getName()).thenReturn("Discord Channel HTML Transcript");
    when(guild.getIconUrl()).thenReturn(AVATAR_URL_BOT);
  }

  public GuildMockBuilder withJDA(@NotNull JDA jda) {
    when(guild.getJDA()).thenReturn(jda);
    return this;
  }

  public GuildMockBuilder withGuildChannel(@NotNull String channelId, @NotNull GuildChannel guildChannel) {
    when(guild.getGuildChannelById(channelId)).thenReturn(guildChannel);
    return this;
  }

  public GuildMockBuilder withRole(@NotNull String roleId, @NotNull Role role) {
    when(guild.getRoleById(roleId)).thenReturn(role);
    return this;
  }

  public Guild build() {
    return guild;
  }
}
