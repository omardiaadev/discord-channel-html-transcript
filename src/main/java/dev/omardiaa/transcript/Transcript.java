package dev.omardiaa.transcript;

import gg.jte.output.Utf8ByteOutput;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jspecify.annotations.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Represents a {@link GuildMessageChannel} in HTML.
 */
public final class Transcript {
  private final Utf8ByteOutput output;
  private final String channelName;

  /**
   * @param output
   *   The {@link Utf8ByteOutput} of the generated file.
   * @param channelName
   *   The {@link GuildMessageChannel} name, used as default name for the generated file
   */
  Transcript(@NonNull Utf8ByteOutput output, @NonNull String channelName) {
    this.output = output;
    this.channelName = channelName;
  }

  /**
   * Writes the output to a {@link FileUpload} with the channel's name to directly send anywhere on Discord.
   *
   * @return A {@link FileUpload} of the generated Transcript.
   */
  @NonNull
  public FileUpload toFileUpload() {
    return toFileUpload(channelName);
  }

  /**
   * Writes the output to a {@link FileUpload} to directly send anywhere on Discord.
   * <br/>
   * {@code .html} is appended to the {@code fileName} if not already suffixed with it.
   *
   * @param fileName
   *   The file name to use for the created {@link FileUpload}.
   *
   * @return A {@link FileUpload} of the generated Transcript.
   */
  @NonNull
  public FileUpload toFileUpload(@NonNull String fileName) {
    fileName = fileName.endsWith(".html") ? fileName : fileName.concat(".html");

    return FileUpload.fromData(getBytes(), fileName);
  }

  /**
   * Writes the output to the specified {@link File}.
   *
   * @param file
   *   The {@link File} which the transcript should write to.
   *
   * @throws IOException
   *   If an I/O error occurs during file writing.
   */
  public void toFile(@NonNull File file) throws IOException {
    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
      fileOutputStream.write(getBytes());
    }
  }

  /**
   * @return The {@code byte[]} of the generated {@link Transcript}.
   */
  public byte[] getBytes() {
    return output.toByteArray();
  }
}
