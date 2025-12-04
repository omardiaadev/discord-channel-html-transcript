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
      @template.discord.component.action-row(actionRow = actionRow)
    @elseif(component instanceof Section section)
      @template.discord.component.section(guild = guild, section = section)
    @elseif(component instanceof TextDisplay textDisplay)
      @template.discord.component.text-display(guild = guild, textDisplay = textDisplay)
    @elseif(component instanceof MediaGallery mediaGallery)
      @template.discord.component.media-gallery(mediaGallery = mediaGallery)
    @elseif(component instanceof Separator separator)
      @template.discord.component.separator(separator = separator)
    @elseif(component instanceof FileDisplay fileDisplay)

    @endif
  @endfor
</div>
