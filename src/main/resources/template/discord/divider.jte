@import dev.omardiaa.transcript.TranscriptConstants
@import net.dv8tion.jda.api.entities.Message

@param Message message
@param boolean isDividerShown

@if(isDividerShown)
  <div class="divider user-select-none">
    <span class="divider__content">
      ${message.getTimeCreated().format(TranscriptConstants.DATE_LONG)}
    </span>
  </div>
@endif
