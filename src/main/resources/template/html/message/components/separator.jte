@import net.dv8tion.jda.api.components.separator.Separator

@param Separator separator

<div
  aria-hidden="${!separator.isDivider()}"
  class="separator user-select-none ${separator.getSpacing() == Separator.Spacing.LARGE ? "separator--large" : null}"
></div>