@import net.dv8tion.jda.api.components.actionrow.ActionRow
@import net.dv8tion.jda.api.components.ActionComponent
@import net.dv8tion.jda.api.components.buttons.Button
@import net.dv8tion.jda.api.components.selections.SelectMenu

@param ActionRow actionRow

<div class="action-rows user-select-none">
  <div class="action-row">
    @for(ActionComponent component : actionRow.getActionComponents())
      @if(component instanceof Button button)
        @template.discord.component.button(button = button)
      @elseif(component instanceof SelectMenu)
        @template.discord.component.select-menu()
      @endif
    @endfor
  </div>
</div>
