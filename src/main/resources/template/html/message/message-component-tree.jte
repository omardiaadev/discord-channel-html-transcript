@import net.dv8tion.jda.api.entities.Guild
@import net.dv8tion.jda.api.components.MessageTopLevelComponent
@import net.dv8tion.jda.api.components.tree.MessageComponentTree
@import net.dv8tion.jda.api.components.container.Container
@import net.dv8tion.jda.api.components.section.Section
@import net.dv8tion.jda.api.components.textdisplay.TextDisplay
@import net.dv8tion.jda.api.components.mediagallery.MediaGallery
@import net.dv8tion.jda.api.components.separator.Separator
@import net.dv8tion.jda.api.components.filedisplay.FileDisplay
@import net.dv8tion.jda.api.components.actionrow.ActionRow

@param Guild guild
@param MessageComponentTree componentTree

<div class="message-component-tree">
  @for(MessageTopLevelComponent component : componentTree.getComponents())
    @if(component instanceof Container container)
      @template.html.message.components.container(guild = guild, container = container)
    @elseif(component instanceof Section section)
      @template.html.message.components.section(guild = guild, section = section)
    @elseif(component instanceof TextDisplay textDisplay)
      @template.html.message.components.text-display(guild = guild, textDisplay = textDisplay)
    @elseif(component instanceof MediaGallery mediaGallery)
      @template.html.message.components.media-gallery(mediaGallery = mediaGallery)
    @elseif(component instanceof Separator separator)
      @template.html.message.components.separator(separator = separator)
    @elseif(component instanceof FileDisplay fileDisplay)

    @elseif(component instanceof ActionRow actionRow)
      @template.html.message.components.action-row(actionRow = actionRow)
    @endif
  @endfor
</div>