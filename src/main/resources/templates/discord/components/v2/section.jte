@import dev.omardiaa.transcript.TranscriberUtils
@import net.dv8tion.jda.api.entities.Guild
@import net.dv8tion.jda.api.components.section.Section
@import net.dv8tion.jda.api.components.section.SectionContentComponentUnion
@import net.dv8tion.jda.api.components.buttons.Button
@import net.dv8tion.jda.api.components.thumbnail.Thumbnail

@param Guild guild
@param Section section

<div class="section">
  <div class="section__content">
    @for(SectionContentComponentUnion contentComponent : section.getContentComponents())
      <div class="message markup">
        $unsafe{TranscriberUtils.parseMarkup(guild, contentComponent.asTextDisplay().getContent())}
      </div>
    @endfor
  </div>

  <div class="section__accessory">
    @if(section.getAccessory() instanceof Button button)
      @template.discord.components.v1.button(button = button)
    @elseif(section.getAccessory() instanceof Thumbnail thumbnail)
      @template.util.image(src = thumbnail.getUrl(), alt = "Thumbnail")
    @endif
  </div>
</div>
