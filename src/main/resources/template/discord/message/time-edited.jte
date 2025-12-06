@import net.dv8tion.jda.api.entities.Message

@param Message message

@if(message.getTimeEdited() != null)
  <div class="message__edited user-select-none">
    <span>(edited)</span>

    @template.discord.ui.timestamp(offsetDateTime = message.getTimeEdited())
  </div>
@endif
