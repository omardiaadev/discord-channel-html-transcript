@import net.dv8tion.jda.api.entities.Message
@import net.dv8tion.jda.api.components.MessageTopLevelComponent
@import net.dv8tion.jda.api.components.container.Container
@import net.dv8tion.jda.api.components.section.Section
@import net.dv8tion.jda.api.components.textdisplay.TextDisplay
@import net.dv8tion.jda.api.components.mediagallery.MediaGallery
@import net.dv8tion.jda.api.components.separator.Separator
@import net.dv8tion.jda.api.components.actionrow.ActionRow

@param Message message

@if (!message.getComponents().isEmpty())
  @if(message.isUsingComponentsV2())
    <div class="components">
      @for(MessageTopLevelComponent component : message.getComponents())
        @if(component instanceof Container container)
          @template.discord.components.v2.container(guild = message.getGuild(), container = container)
        @elseif(component instanceof Section section)
          @template.discord.components.v2.section(guild = message.getGuild(), section = section)
        @elseif(component instanceof TextDisplay textDisplay)
          @template.discord.components.v2.text-display(guild = message.getGuild(), textDisplay = textDisplay)
        @elseif(component instanceof MediaGallery mediaGallery)
          @template.discord.components.v2.media-gallery(mediaGallery = mediaGallery)
        @elseif(component instanceof Separator separator)
          @template.discord.components.v2.separator(separator = separator)
        @elseif(component instanceof ActionRow actionRow)
          @template.discord.components.v1.action-row(actionRow = actionRow)
        @endif
      @endfor
    </div>
  @else
    @template.discord.embeds(guild = message.getGuild(), embeds = message.getEmbeds())

    @for(ActionRow actionRow : message.getComponentTree().findAll(ActionRow.class))
      @template.discord.components.v1.action-row(actionRow = actionRow)
    @endfor
  @endif
@endif
