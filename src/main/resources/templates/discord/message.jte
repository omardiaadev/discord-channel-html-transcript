@import dev.omardiaa.transcript.TranscriberUtils
@import net.dv8tion.jda.api.entities.Message

@param Message message

<div
  id="${message.getId()}"
  class="message markup"
>
  $unsafe{TranscriberUtils.parseMarkup(message.getGuild(), message.getContentRaw())}

  @template.discord.message.attachments(message = message)

  @template.discord.message.components(message = message)

  @template.discord.message.reactions(reactions = message.getReactions())

  @if(message.getTimeEdited() != null)
    <div class="message__edited user-select-none">
      <span>(edited)</span>

      @template.discord.ui.timestamp(offsetDateTime = message.getTimeEdited())
    </div>
  @endif
</div>
