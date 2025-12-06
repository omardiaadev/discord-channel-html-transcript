@import dev.omardiaa.transcript.TranscriptUtils
@import net.dv8tion.jda.api.entities.Message

@param Message message

<div
  id="${message.getId()}"
  class="message markup"
>
  $unsafe{TranscriptUtils.parseMarkup(message.getGuild(), message.getContentRaw())}

  @template.discord.message.attachments(message = message)
</div>
