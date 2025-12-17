package dev.omardiaa.transcript;

import gg.jte.output.Utf8ByteOutput;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jspecify.annotations.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Represents a {@link GuildMessageChannel} as HTML.
 */
public final class Transcript {
  private final Utf8ByteOutput output;
  private final String channelName;

  /**
   * Constructs a new {@link Transcript} instance.
   *
   * @param output
   *   The {@link Utf8ByteOutput} of the transcribed channel.
   * @param channelName
   *   The {@link GuildMessageChannel} name.
   */
  Transcript(@NonNull Utf8ByteOutput output, @NonNull String channelName) {
    this.output = output;
    this.channelName = channelName;
  }

  /**
   * Creates a {@link FileUpload} of the transcribed channel to send directly via JDA.
   * <p>
   * The file name will default to the {@code channelName} provided during construction, suffixed with {@code .html}.
   *
   * @return A {@link FileUpload} of the transcribed channel.
   */
  @NonNull
  public FileUpload toFileUpload() {
    return toFileUpload(channelName);
  }

  /**
   * Creates a {@link FileUpload} of the transcribed channel to send directly via JDA.
   * <p>
   * If the provided {@code fileName} does not end with {@code .html}, the extension is automatically appended.
   *
   * @param fileName
   *   The file name to use for the created {@link FileUpload}.
   *
   * @return A {@link FileUpload} of the transcribed channel.
   */
  @NonNull
  public FileUpload toFileUpload(@NonNull String fileName) {
    fileName = fileName.endsWith(".html") ? fileName : fileName.concat(".html");

    return FileUpload.fromData(getByteArray(), fileName);
  }

  /**
   * Writes {@link Utf8ByteOutput#toByteArray()} to the specified {@code file}.
   *
   * @param file
   *   The {@link File} to write the output into.
   *
   * @throws IOException
   *   If an I/O error occurs while writing to the file.
   */
  public void toFile(@NonNull File file) throws IOException {
    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
      fileOutputStream.write(getByteArray());
    }
  }

  /**
   * Retrieves the raw byte output of the transcribed channel.
   *
   * @return A byte array containing the UTF-8 encoded HTML.
   */
  public byte[] getByteArray() {
    return output.toByteArray();
  }
}
