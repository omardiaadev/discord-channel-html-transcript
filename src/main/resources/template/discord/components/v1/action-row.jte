@import net.dv8tion.jda.api.components.actionrow.ActionRow
@import net.dv8tion.jda.api.components.ActionComponent
@import net.dv8tion.jda.api.components.buttons.Button
@import net.dv8tion.jda.api.components.selections.SelectMenu

@param ActionRow actionRow

<div class="action-row">
  @for(ActionComponent actionComponent : actionRow.getActionComponents())
    @if(actionComponent instanceof Button button)
      @template.discord.components.v1.button(button = button)
    @elseif(actionComponent instanceof SelectMenu)
      @template.discord.components.v1.select-menu()
    @endif
  @endfor
</div>
