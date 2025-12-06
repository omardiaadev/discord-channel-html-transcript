@import net.dv8tion.jda.api.entities.Guild
@import net.dv8tion.jda.api.components.textdisplay.TextDisplay
@import dev.omardiaa.transcript.TranscriptUtils

@param Guild guild
@param TextDisplay textDisplay

<div class="text-display">
  <div class="message markup">
    $unsafe{TranscriptUtils.parseMarkup(guild, textDisplay.getContent())}
  </div>
</div>
