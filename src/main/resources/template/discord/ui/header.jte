@import net.dv8tion.jda.api.entities.channel.concrete.TextChannel

@param TextChannel textChannel

<div class="header user-select-none">
  <div class="bar">
    <div class="bar__guild">
      <img
        src="${textChannel.getGuild().getIconUrl()}"
        alt="${textChannel.getGuild().getName()}"
        height="20"
        width="20"
      />

      <div class="bar__guild__name">
        ${textChannel.getGuild().getName()}
      </div>
    </div>
  </div>

  <section class="channel__header">
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 24 24"
      height="20"
      width="20"
    >
      <path
        fill="#82838b"
        fill-rule="evenodd"
        d="M10.99 3.16A1 1 0 1 0 9 2.84L8.15 8H4a1 1 0 0 0 0 2h3.82l-.67 4H3a1 1 0 1 0 0 2h3.82l-.8 4.84a1 1 0 0 0 1.97.32L8.85 16h4.97l-.8 4.84a1 1 0 0 0 1.97.32l.86-5.16H20a1 1 0 1 0 0-2h-3.82l.67-4H21a1 1 0 1 0 0-2h-3.82l.8-4.84a1 1 0 1 0-1.97-.32L15.15 8h-4.97l.8-4.84ZM14.15 14l.67-4H9.85l-.67 4h4.97Z"
      />
    </svg>

    <h1 class="channel__header__title">${textChannel.getName()}</h1>
  </section>
</div>
