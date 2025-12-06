@import net.dv8tion.jda.api.components.selections.SelectMenu

@param SelectMenu selectMenu

<div class="select-menu user-select-none">
  <span class="select-menu__placeholder">
    ${selectMenu.getPlaceholder() == null ? "Make a selection" : selectMenu.getPlaceholder()}
  </span>

  <svg
    xmlns="http://www.w3.org/2000/svg"
    viewBox="0 0 24 24"
    width="18"
    height="18"
    fill="white"
  >
    <path d="M5.3 9.3a1 1 0 0 1 1.4 0l5.3 5.29 5.3-5.3a1 1 0 1 1 1.4 1.42l-6 6a1 1 0 0 1-1.4 0l-6-6a1 1 0 0 1 0-1.42Z"/>
  </svg>
</div>
