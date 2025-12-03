package dev.skywolfxp.transcript;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.TemplateException;
import gg.jte.output.Utf8ByteOutput;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a generated Transcript in HTML of your Discord {@link TextChannel}.
 * <p>
 * Uses <a href="https://github.com/casid/jte/">Java Template Engine</a> for HTML generation.
 */
public final class Transcript {
  private final TemplateEngine templateEngine;
  private final Utf8ByteOutput utf8ByteOutput;
  private final HashMap<String, Object> params;

  /**
   * Constructs {@link Transcript} with {@link TemplateEngine} precompiled template configuration.
   */
  public Transcript() {
    this.templateEngine = TemplateEngine.createPrecompiled(ContentType.Html);
    this.utf8ByteOutput = new Utf8ByteOutput();
    this.params = new HashMap<>();
  }

  /**
   * @param textChannel
   *   The {@link TextChannel} for which to write the Transcript.
   *
   * @throws IllegalArgumentException
   *   If the specified {@code textChannel} contains no messages.
   */
  public void render(@NotNull TextChannel textChannel) throws IllegalArgumentException, TemplateException {
    List<Message> messages = textChannel
      .getIterableHistory().stream().sorted(Comparator.comparing(ISnowflake::getTimeCreated)).toList();

    if (messages.isEmpty()) {
      throw new IllegalArgumentException("TextChannel '%s' contains no messages".formatted(textChannel.getName()));
    }

    params.put("textChannel", textChannel);
    params.put("messages", messages);

    templateEngine.render("template.jte", params, utf8ByteOutput);
  }

  /**
   * Converts the output to {@link FileUpload} to directly send anywhere on Discord.
   * <p>
   * The {@code fileName} is appended with {@code .html} if it is not suffixed with it.
   *
   * @param fileName
   *   The file name to use for the generated {@link FileUpload}.
   *
   * @return A {@link FileUpload} of the generated Transcript.
   */
  @NotNull
  public FileUpload toFileUpload(@NotNull String fileName) {
    return FileUpload.fromData(
      utf8ByteOutput.toByteArray(), fileName.endsWith(".html") ? fileName : fileName + ".html");
  }

  /**
   * Writes the output to the specified {@link File}.
   *
   * @param file
   *   The {@link File} to which the transcript should be written.
   *
   * @throws IOException
   *   If an I/O error occurs during file writing.
   */
  public void writeToFile(@NotNull File file) throws IOException {
    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
      fileOutputStream.write(utf8ByteOutput.toByteArray());
    }
  }
}