package dev.omardiaa.transcript;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TranscriberTest {
  AutoCloseable mocks;
  Transcriber transcriber;

  Path tempDir = Path.of(System.getProperty("java.io.tmpdir")).resolve("discord-channel-html-transcript");
  String devStyles = new File("src/test/resources/template/css/style.css").getAbsolutePath();

  @BeforeEach
  void setUp() throws IOException {
    mocks = MockitoAnnotations.openMocks(this);

    TemplateEngine templateEngine = TemplateEngine.create(new ResourceCodeResolver("templates"), ContentType.Html);
    templateEngine.setBinaryStaticContent(true);

    transcriber = new Transcriber(templateEngine);

    if (!Files.exists(tempDir)) {
      Files.createDirectories(tempDir);
    }
  }

  @AfterEach
  void tearDown() throws Exception {
    mocks.close();
  }

  @Test
  void generate() {
    transcriber.generate(TranscriberMockUtil.mockChannel(TranscriberTestUtil.createMessages()), devStyles)
               .thenAccept(transcript -> {
                 try {
                   transcript.toFile(tempDir.resolve("transcript.html").toFile());
                 } catch (IOException e) {
                   throw new RuntimeException(e);
                 }
               });
  }

  @Test
  void generateThrowsIfEmpty() {
    CompletableFuture<Transcript> future = transcriber.generate(
      TranscriberMockUtil.mockChannel(Collections.emptyList()), devStyles);

    assertInstanceOf(IllegalArgumentException.class, assertThrows(ExecutionException.class, future::get).getCause());
  }
}
