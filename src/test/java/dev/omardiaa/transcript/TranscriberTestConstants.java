package dev.omardiaa.transcript;

import net.dv8tion.jda.api.entities.User;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static dev.omardiaa.transcript.TranscriberMockUtil.mockAuthor;

final class TranscriberTestConstants {
  private TranscriberTestConstants() {}

  static final String AVATAR_URL_USER = "https://avatars.githubusercontent.com/u/70555240";
  static final String AVATAR_URL_BOT =
    "https://cdn.discordapp.com/avatars/1093684128437764136/3b2cb4620d02fbcae500a447d0c14de9.png";

  static final OffsetDateTime TIME_D1 = OffsetDateTime.of(2030, 2, 20, 1, 0, 0, 0, ZoneOffset.UTC);
  static final OffsetDateTime TIME_D2 = OffsetDateTime.of(2030, 2, 21, 0, 0, 0, 0, ZoneOffset.UTC);

  static final User AUTHOR_1 = mockAuthor("974748803305455627", "omardiaadev", "Omar Diaa", AVATAR_URL_USER, false);
  static final User AUTHOR_2 = mockAuthor("974748803305455627", "VORTEX#0000", "VORTEX", AVATAR_URL_BOT, true);
}
