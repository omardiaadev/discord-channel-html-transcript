@import net.dv8tion.jda.api.entities.Guild
@import net.dv8tion.jda.api.components.buttons.Button
@import net.dv8tion.jda.api.components.section.Section
@import dev.omardiaa.transcript.TranscriptUtils

@param Guild guild
@param Section section

<div class="section">
  <div class="message__content markup">
    $unsafe{TranscriptUtils.parseMarkup(guild, section.getContentComponents().getFirst().asTextDisplay().getContent())}
  </div>

  <div class="section__accessory">
    @if(section.getAccessory() instanceof Button button)
      @template.html.message.components.button(button = button)
    @endif
  </div>
</div>
