@import net.dv8tion.jda.api.entities.Message
@import net.dv8tion.jda.api.components.actionrow.ActionRow

@param Message message

@if (!message.getComponents().isEmpty())
  @if(message.isUsingComponentsV2())
    @template.discord.message-component-tree(guild = message.getGuild(), componentTree = message.getComponentTree())
  @else
    @template.discord.embeds(guild = message.getGuild(), embeds = message.getEmbeds())

    @for(ActionRow actionRow : message.getComponentTree().findAll(ActionRow.class))
      @template.discord.components.v1.action-row(actionRow = actionRow)
    @endfor
  @endif
@endif
