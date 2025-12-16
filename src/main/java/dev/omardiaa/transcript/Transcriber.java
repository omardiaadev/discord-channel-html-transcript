package dev.omardiaa.transcript;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.output.Utf8ByteOutput;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Transcribes a {@link GuildMessageChannel} into a {@link Transcript} in HTML.
 * <br/>
 * Uses <a href="https://github.com/casid/jte/">Java Template Engine</a> for HTML generation.
 */
public class Transcriber {
  private static final Logger LOGGER = LoggerFactory.getLogger(Transcriber.class);

  private final TemplateEngine templateEngine;

  /**
   * Constructs {@link Transcriber} with a <a href="https://jte.gg/pre-compiling/">precompiled</a>
   * {@link TemplateEngine}.
   */
  public Transcriber() {
    this.templateEngine = TemplateEngine.createPrecompiled(ContentType.Html);
  }

  Transcriber(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  /**
   * @param channel
   *   The {@link GuildMessageChannel} to generate the Transcript for.
   *
   * @return A {@link CompletableFuture} of the generated {@link Transcript}.
   *
   * @throws IllegalArgumentException
   *   If {@code channel} contains no messages.
   */
  @NonNull
  public CompletableFuture<Transcript> generate(@NonNull GuildMessageChannel channel) {
    return generate(channel, null);
  }

  @NonNull
  CompletableFuture<Transcript> generate(@NonNull GuildMessageChannel channel, @Nullable String devStyles) {
    return channel.getIterableHistory().takeWhileAsync(Objects::nonNull).thenApply(messages -> {
      if (messages.isEmpty()) {
        throw new IllegalArgumentException("'#%s' contains no messages.".formatted(channel.getName()));
      }

      LOGGER.debug("Found {} messages.", messages.size());

      Map<String, Object> params = new HashMap<>();
      params.put("channel", channel);
      params.put("messages", messages.reversed());
      params.put("devStyles", devStyles);

      Utf8ByteOutput output = new Utf8ByteOutput();
      templateEngine.render("transcript.jte", params, output);

      return new Transcript(output, channel.getName());
    });
  }
}
