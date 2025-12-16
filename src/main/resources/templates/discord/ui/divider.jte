@import dev.omardiaa.transcript.TranscriberConstants
@import net.dv8tion.jda.api.entities.Message

@param Message message

<div class="divider user-select-none">
  <time class="divider__content">
    ${message.getTimeCreated().format(TranscriberConstants.DATE_LONG)}
  </time>
</div>
