package dev.skywolfxp.transcript;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.TemplateException;
import gg.jte.output.Utf8ByteOutput;
import gg.jte.resolve.DirectoryCodeResolver;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

class TranscriptMockTest {
  AutoCloseable autoCloseable;
  
  Path outputDir = Path.of(System.getProperty("java.io.tmpdir")).resolve("discord-channel-html-transcript");
  
  private TemplateEngine templateEngine;
  private Utf8ByteOutput utf8ByteOutput;
  private HashMap<String, Object> params;
  
  @TempDir
  Path tempDir;
  
  @BeforeEach
  void setUp() throws IOException {
    autoCloseable = MockitoAnnotations.openMocks(this);
    
    templateEngine = TemplateEngine.create(
      new DirectoryCodeResolver(Path.of("src/main/resources/template")), ContentType.Html);
    utf8ByteOutput = new Utf8ByteOutput();
    params = new HashMap<>();
    
    if (!Files.exists(outputDir)) {
      Files.createDirectories(outputDir);
    }
  }
  
  @AfterEach
  void tearDown() throws Exception {
    autoCloseable.close();
  }
  
  @Test
  void render() throws IllegalArgumentException, TemplateException, IOException {
    Guild guild = TranscriptTestUtil.createGuild();
    
    params.put("textChannel", TranscriptMockUtil.mockTextChannel("discord-channel-html-transcript", guild));
    params.put("messages", TranscriptTestUtil.createMessages(guild));
    params.put("isDev", true);
    
    templateEngine.render("template.jte", params, utf8ByteOutput);
    
    writeToFile(tempDir.resolve("transcript-temp.html").toFile());
    
    Files.copy(
      tempDir.resolve("transcript-temp.html"), outputDir.resolve("transcript.html"),
      StandardCopyOption.REPLACE_EXISTING);
  }
  
  public void writeToFile(@NotNull File file) throws IOException {
    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
      fileOutputStream.write(utf8ByteOutput.toByteArray());
    }
  }
}
