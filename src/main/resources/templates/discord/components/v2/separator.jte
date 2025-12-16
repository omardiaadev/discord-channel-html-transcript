@import net.dv8tion.jda.api.components.separator.Separator

@param Separator separator

<div
  aria-hidden="${!separator.isDivider()}"
  data-separator-spacing="${separator.getSpacing().name()}"
  class="separator user-select-none"
></div>
