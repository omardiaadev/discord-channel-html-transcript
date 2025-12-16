@import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel

@param GuildMessageChannel channel

<div class="header user-select-none">
  <div class="header__guild__container">
    <div class="header__guild">
      <img
        src="${channel.getGuild().getIconUrl()}"
        alt="${channel.getGuild().getName()}"
        height="20"
        width="20"
      />

      <span class="header__guild__name">${channel.getGuild().getName()}</span>
    </div>
  </div>

  <section class="header__channel">
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 24 24"
      height="20"
      width="20"
    >
      <path
        d="M10.99 3.16A1 1 0 1 0 9 2.84L8.15 8H4a1 1 0 0 0 0 2h3.82l-.67 4H3a1 1 0 1 0 0 2h3.82l-.8 4.84a1 1 0 0 0 1.97.32L8.85 16h4.97l-.8 4.84a1 1 0 0 0 1.97.32l.86-5.16H20a1 1 0 1 0 0-2h-3.82l.67-4H21a1 1 0 1 0 0-2h-3.82l.8-4.84a1 1 0 1 0-1.97-.32L15.15 8h-4.97l.8-4.84ZM14.15 14l.67-4H9.85l-.67 4h4.97Z"
        fill-rule="evenodd"
        fill="#82838b"
      />
    </svg>

    <h1 class="header__channel__title">${channel.getName()}</h1>
  </section>
</div>
