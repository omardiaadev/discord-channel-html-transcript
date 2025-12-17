package dev.omardiaa.transcript;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.output.Utf8ByteOutput;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Transcribes a {@link GuildMessageChannel} into a {@link Transcript} as HTML.
 * <p>
 * Uses <a href="https://github.com/casid/jte/">Java Template Engine</a> for HTML generation.
 */
public class Transcriber {
  private final TemplateEngine templateEngine;

  /**
   * Constructs {@link Transcriber} with a <a href="https://jte.gg/pre-compiling/">precompiled</a>
   * {@link TemplateEngine}.
   */
  public Transcriber() {
    this.templateEngine = TemplateEngine.createPrecompiled(ContentType.Html);
  }

  /**
   * Constructs {@link Transcriber} with the specified {@code templateEngine}.
   * <p>
   * This constructor is only used during testing.
   *
   * @param templateEngine
   *   The {@link TemplateEngine} used for transcription.
   */
  Transcriber(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  /**
   * @param channel
   *   The {@link GuildMessageChannel} to transcribe.
   *
   * @return A {@link CompletableFuture} of the transcribed {@link Transcript}.
   *
   * @throws IllegalArgumentException
   *   If {@code channel} contains no messages.
   */
  @NonNull
  public CompletableFuture<Transcript> transcribe(@NonNull GuildMessageChannel channel) {
    return transcribe(channel, null);
  }

  /**
   * @param channel
   *   The {@link GuildMessageChannel} to transcribe.
   * @param testStyle
   *   The path to the test {@code style.css}, only specified during testing.
   *
   * @return A {@link CompletableFuture} of the transcribed {@link Transcript}.
   *
   * @throws IllegalArgumentException
   *   If {@code channel} contains no messages.
   */
  @NonNull
  CompletableFuture<Transcript> transcribe(@NonNull GuildMessageChannel channel, @Nullable String testStyle) {
    return channel.getIterableHistory().takeWhileAsync(Objects::nonNull).thenApply(messages -> {
      if (messages.isEmpty()) {
        throw new IllegalArgumentException("'#%s' contains no messages.".formatted(channel.getName()));
      }

      Map<String, Object> params = new HashMap<>();
      params.put("channel", channel);
      params.put("messages", messages.reversed());
      params.put("testStyle", testStyle);

      Utf8ByteOutput output = new Utf8ByteOutput();
      templateEngine.render("transcript.jte", params, output);

      return new Transcript(output, channel.getName());
    });
  }
}
