@import net.dv8tion.jda.api.components.container.ContainerChildComponent
@import net.dv8tion.jda.api.entities.Guild
@import net.dv8tion.jda.api.components.container.Container
@import net.dv8tion.jda.api.components.actionrow.ActionRow
@import net.dv8tion.jda.api.components.filedisplay.FileDisplay
@import net.dv8tion.jda.api.components.mediagallery.MediaGallery
@import net.dv8tion.jda.api.components.section.Section
@import net.dv8tion.jda.api.components.separator.Separator
@import net.dv8tion.jda.api.components.textdisplay.TextDisplay

@param Guild guild
@param Container container

<div class="container">
  @for(ContainerChildComponent component : container.getComponents())
    @if(component instanceof ActionRow actionRow)
      @template.html.message.components.action-row(actionRow = actionRow)
    @elseif(component instanceof Section section)
      @template.html.message.components.section(guild = guild, section = section)
    @elseif(component instanceof TextDisplay textDisplay)
      @template.html.message.components.text-display(guild = guild, textDisplay = textDisplay)
    @elseif(component instanceof MediaGallery mediaGallery)
      @template.html.message.components.media-gallery(mediaGallery = mediaGallery)
    @elseif(component instanceof Separator separator)
      @template.html.message.components.separator(separator = separator)
    @elseif(component instanceof FileDisplay fileDisplay)

    @endif
  @endfor
</div>