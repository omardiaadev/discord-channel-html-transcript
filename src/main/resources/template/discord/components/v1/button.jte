@import net.dv8tion.jda.api.components.buttons.Button
@import net.dv8tion.jda.api.components.buttons.ButtonStyle
@import net.dv8tion.jda.api.entities.emoji.CustomEmoji
@import net.dv8tion.jda.api.entities.emoji.UnicodeEmoji

@param Button button

<a
  href="${button.isDisabled() ? null : button.getUrl()}"
  role="button"
  aria-disabled="${button.isDisabled()}"
  class="button button--${button.getStyle().name().toLowerCase()} user-select-none"
>
  @if(button.getEmoji() != null)
    @if(button.getEmoji() instanceof CustomEmoji customEmoji)
      <img
        src="${customEmoji.getImageUrl()}"
        alt="${customEmoji.getName()}"
        class="button__emoji"
      />
    @elseif(button.getEmoji() instanceof UnicodeEmoji unicodeEmoji)
      <span class="button__emoji">
        &#x${unicodeEmoji.getAsCodepoints().replace("U+", "")};
      </span>
    @endif
  @endif

  <span>${button.getLabel()}</span>

  @if(button.getStyle() == ButtonStyle.LINK)
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 24 24"
      fill="currentColor"
      class="button--link__icon"
    >
      <path d="M15 2a1 1 0 0 1 1-1h6a1 1 0 0 1 1 1v6a1 1 0 1 1-2 0V4.41l-4.3 4.3a1 1 0 1 1-1.4-1.42L19.58 3H16a1 1 0 0 1-1-1Z"/>
      <path d="M5 2a3 3 0 0 0-3 3v14a3 3 0 0 0 3 3h14a3 3 0 0 0 3-3v-6a1 1 0 1 0-2 0v6a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V5a1 1 0 0 1 1-1h6a1 1 0 1 0 0-2H5Z"/>
    </svg>
  @endif
</a>
